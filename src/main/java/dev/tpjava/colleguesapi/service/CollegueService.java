package dev.tpjava.colleguesapi.service;

import dev.tpjava.colleguesapi.controller.dto.CreerCollegueDTO;
import dev.tpjava.colleguesapi.exception.CollegueInvalideException;
import dev.tpjava.colleguesapi.entity.Collegue;
import dev.tpjava.colleguesapi.exception.CollegueNonTrouveException;
import dev.tpjava.colleguesapi.util.Constantes;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class CollegueService {

    public static final int LAST_NAME_MIN_SIZE = 2;
    public static final int FIRST_NAME_MIN_SIZE = 2;
    public static final int EMAIL_MIN_SIZE = 3;
    public static final int MIN_AGE = 18;
    private Map<String, Collegue> data = new HashMap<>();

    public CollegueService() {

    }

    public CollegueService(int nbCollegue) {

        Random r = new Random();
        for(int i = 0; i < nbCollegue; i++){

            String id = UUID.randomUUID().toString();
            String nom = Constantes.NOMS.get(r.nextInt(Constantes.NOMS.size()));
            String prenom = Constantes.PRENOMS.get(r.nextInt(Constantes.PRENOMS.size()));
            String mail = prenom + "." + nom + "@gmail.com";
            LocalDate birthdate = LocalDate.of(r.nextInt(30)+ 1970, r.nextInt(12) + 1, r.nextInt(28) + 1);
            data.put(id,
                    new Collegue(id, nom, prenom, mail, null, birthdate));
        }
    }

    public List<Collegue> rechercheParNom(String nomRecherche) {
        return data.values().stream()
                .filter(c -> Objects.equals(c.getLastName(), nomRecherche))
                .collect(Collectors.toList());
    }

    public Collegue rechercherParMatricule(String matriculeRecherche) throws CollegueNonTrouveException {
        if (!data.containsKey(matriculeRecherche)) {
            throw new CollegueNonTrouveException();
        }
        return data.get(matriculeRecherche);
    }

    public Collegue ajouterCollegue(CreerCollegueDTO collegueAAjouter) {

        if (collegueAAjouter.getFirstName() == null || collegueAAjouter.getFirstName().length() < LAST_NAME_MIN_SIZE
                || collegueAAjouter.getLastName() == null || collegueAAjouter.getLastName().length() < FIRST_NAME_MIN_SIZE
                || collegueAAjouter.getEmail() == null || collegueAAjouter.getEmail().length() < EMAIL_MIN_SIZE
                || !collegueAAjouter.getEmail().contains("@") || collegueAAjouter.getPictureUrl() == null
                || collegueAAjouter.getPictureUrl().length() < "http".length() || !"http".equals(collegueAAjouter.getPictureUrl().substring(0, 4))
                || collegueAAjouter.getBirthDate() == null || Period.between(collegueAAjouter.getBirthDate(), LocalDate.now()).getYears() < MIN_AGE
        ) {
            throw new CollegueInvalideException();
        }
        Collegue c = collegueAAjouter.toCollegue();
        c.setMatricule(UUID.randomUUID().toString());
        data.put(c.getMatricule(), c);
        return c;
    }

    public Collegue updateEmail(String matricule, String email) {
        if (!data.containsKey(matricule)) {
            throw new CollegueNonTrouveException();
        }
        if (email == null || email.length() < 3 || !email.contains("@")) {
            throw new CollegueInvalideException();
        }
        data.get(matricule).setEmail(email);
        return data.get(matricule);
    }

    public Collegue updatePictureUrl(String matricule, String pictureUrl) {
        if (!data.containsKey(matricule)) {
            throw new CollegueNonTrouveException();
        }
        if (pictureUrl == null || pictureUrl.length() < 4 || !"http".equals(pictureUrl.substring(0, 4))) {
            throw new CollegueInvalideException();
        }
        data.get(matricule).setPictureUrl(pictureUrl);
        return data.get(matricule);

    }
}
