package dev.tpjava.colleguesapi.service;

import dev.tpjava.colleguesapi.entity.Collegue;
import dev.tpjava.colleguesapi.service.exception.CollegueNonTrouveException;
import dev.tpjava.colleguesapi.util.Constantes;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CollegueService {

    private static final int NB_COLLEGUE = 1000;
    private Map<String, Collegue> data = new HashMap<>();

    public CollegueService() {

        Random r = new Random();
        for(int i = 0; i < NB_COLLEGUE; i++){

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
        List<Collegue> list = data.entrySet().stream()
                .map(e -> e.getValue())
                .filter(c -> Objects.equals(c.getLastName(), nomRecherche))
                .collect(Collectors.toList());

        return list;
    }

    public Collegue rechercherParMatricule(String matriculeRecherche) throws CollegueNonTrouveException {
        if (!data.containsKey(matriculeRecherche)) {
            throw new CollegueNonTrouveException();
        }
        return data.get(matriculeRecherche);
    }
}
