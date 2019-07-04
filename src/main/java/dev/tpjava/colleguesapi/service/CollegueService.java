package dev.tpjava.colleguesapi.service;

import dev.tpjava.colleguesapi.controller.exception.CollegueInvalideException;
import dev.tpjava.colleguesapi.entity.Collegue;
import dev.tpjava.colleguesapi.controller.exception.CollegueNonTrouveException;
import dev.tpjava.colleguesapi.util.Constantes;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class CollegueService {

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

    public Collegue ajouterCollegue(Collegue collegueAAjouter) {

        if (collegueAAjouter.getFirstName() == null || collegueAAjouter.getFirstName().length() < 2
                || collegueAAjouter.getLastName() == null || collegueAAjouter.getLastName().length() < 2
                || collegueAAjouter.getEmail() == null || collegueAAjouter.getEmail().length() < 3
                || !collegueAAjouter.getEmail().contains("@") || collegueAAjouter.getPictureUrl() == null
                || collegueAAjouter.getPictureUrl().length() < 4 || !"http".equals(collegueAAjouter.getPictureUrl().substring(0, 4))
                || collegueAAjouter.getBirthDate() == null || Period.between(collegueAAjouter.getBirthDate(), LocalDate.now()).getYears() < 18
        ) {
            throw new CollegueInvalideException();
        }
        collegueAAjouter.setMatricule(UUID.randomUUID().toString());
        data.put(collegueAAjouter.getMatricule(), collegueAAjouter);
        return collegueAAjouter;
    }
}
