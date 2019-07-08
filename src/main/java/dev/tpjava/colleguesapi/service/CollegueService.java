package dev.tpjava.colleguesapi.service;

import dev.tpjava.colleguesapi.controller.dto.CreerCollegueDTO;
import dev.tpjava.colleguesapi.exception.CollegueInvalideException;
import dev.tpjava.colleguesapi.entity.Collegue;
import dev.tpjava.colleguesapi.exception.CollegueNonTrouveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
public class CollegueService {

    public static final int LAST_NAME_MIN_SIZE = 2;
    public static final int FIRST_NAME_MIN_SIZE = 2;
    public static final int EMAIL_MIN_SIZE = 3;
    public static final int MIN_AGE = 18;

    @Autowired
    private CollegueRepository collegueRepository;


    public CollegueService() {

    }

    public List<Collegue> rechercheParNom(String nomRecherche) {
        return collegueRepository.findAllByLastName(nomRecherche);
    }

    public Collegue rechercherParMatricule(String matriculeRecherche) throws CollegueNonTrouveException {
        return collegueRepository.findById(matriculeRecherche).orElseThrow(CollegueNonTrouveException::new);

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
        collegueRepository.save(c);
        return c;
    }

    public Collegue updateEmail(String matricule, String email) {
        Collegue c = collegueRepository.findById(matricule).orElseThrow(CollegueNonTrouveException::new);
        if (email == null || email.length() < EMAIL_MIN_SIZE || !email.contains("@")) {
            throw new CollegueInvalideException();
        }
        c.setEmail(email);
        return  collegueRepository.save(c);
    }

    public Collegue updatePictureUrl(String matricule, String pictureUrl) {
        Collegue c = collegueRepository.findById(matricule).orElseThrow(CollegueNonTrouveException::new);
        if (pictureUrl == null || pictureUrl.length() < 4 || !"http".equals(pictureUrl.substring(0, 4))) {
            throw new CollegueInvalideException();
        }
        c.setPictureUrl(pictureUrl);

        return collegueRepository.save(c);

    }
}
