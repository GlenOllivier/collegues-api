package dev.tpjava.colleguesapi.service;

import dev.tpjava.colleguesapi.controller.dto.CreerCollegueDTO;
import dev.tpjava.colleguesapi.controller.dto.PhotoDTO;
import dev.tpjava.colleguesapi.exception.CollegueInvalideException;
import dev.tpjava.colleguesapi.entity.Collegue;
import dev.tpjava.colleguesapi.exception.CollegueNonTrouveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CollegueService {

    private static final int LAST_NAME_MIN_SIZE = 2;
    private static final int FIRST_NAME_MIN_SIZE = 2;
    private static final int EMAIL_MIN_SIZE = 3;
    private static final int MIN_AGE = 18;

    private static final String LAST_NAME_INVALID_MESSAGE = "Le nom doit comporter au moins deux caractères";
    private static final String FIRST_NAME_INVALID_MESSAGE = "Le prénom doit comporter au moins deux caractères";
    private static final String EMAIL_MIN_INVALID_MESSAGE = "L'email doit comporter au moins deux caractères plus un @";
    private static final String PICTURE_URL_INVALID_MESSAGE = "L'url de l'image doit commencer par http";
    private static final String BIRTH_DATE_INVALID_MESSAGE = "L'age doit être d'au moins 18 ans";

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

        Map<String, String> causes = new HashMap<>();
        if (collegueAAjouter.getFirstName() == null || collegueAAjouter.getFirstName().length() < LAST_NAME_MIN_SIZE) {
            causes.put("firstName", LAST_NAME_INVALID_MESSAGE);
        }
        if (collegueAAjouter.getLastName() == null || collegueAAjouter.getLastName().length() < FIRST_NAME_MIN_SIZE) {
            causes.put("lastName", FIRST_NAME_INVALID_MESSAGE);

        }
        if (collegueAAjouter.getEmail() == null || collegueAAjouter.getEmail().length() < EMAIL_MIN_SIZE || !collegueAAjouter.getEmail().contains("@")) {
            causes.put("email", EMAIL_MIN_INVALID_MESSAGE);
        }
        if ( collegueAAjouter.getPictureUrl() == null || collegueAAjouter.getPictureUrl().length() < "http".length()
                || !"http".equals(collegueAAjouter.getPictureUrl().substring(0, 4))) {
            causes.put("pictureUrl", PICTURE_URL_INVALID_MESSAGE);
        }
        if ( collegueAAjouter.getBirthDate() == null || Period.between(collegueAAjouter.getBirthDate(), LocalDate.now()).getYears() < MIN_AGE) {
            causes.put("birthDate", BIRTH_DATE_INVALID_MESSAGE);
        }
        if(causes.size() > 0) {
            throw new CollegueInvalideException(causes);
        }
        Collegue c = collegueAAjouter.toCollegue();
        c.setMatricule(UUID.randomUUID().toString());
        collegueRepository.save(c);
        return c;
    }

    public Collegue updateEmail(String matricule, String email) {
        Collegue c = collegueRepository.findById(matricule).orElseThrow(CollegueNonTrouveException::new);
        if (email == null || email.length() < EMAIL_MIN_SIZE || !email.contains("@")) {
            Map <String, String> causes = new HashMap<>();
            causes.put("email", EMAIL_MIN_INVALID_MESSAGE);
            throw new CollegueInvalideException(causes);
        }
        c.setEmail(email);
        return  collegueRepository.save(c);
    }

    public Collegue updatePictureUrl(String matricule, String pictureUrl) {
        Collegue c = collegueRepository.findById(matricule).orElseThrow(CollegueNonTrouveException::new);
        if ( pictureUrl == null || pictureUrl.length() < "http".length()
                || !"http".equals(pictureUrl.substring(0, 4))) {
            Map <String, String> causes = new HashMap<>();
            causes.put("pictureUrl", PICTURE_URL_INVALID_MESSAGE);
            throw new CollegueInvalideException(causes);
        }
        c.setPictureUrl(pictureUrl);

        return collegueRepository.save(c);

    }

    public List<PhotoDTO> getAllPictures() {
        List<Collegue> collegues = collegueRepository.findAll();
        return collegues.stream()
                .map(collegue -> new PhotoDTO(collegue.getMatricule(), collegue.getPictureUrl()))
                .collect(Collectors.toList());
    }

    public boolean verifEmail(String email) {
        List<Collegue> collegues = collegueRepository.findAll();
        return collegues.stream()
                .anyMatch(c -> email.equals(c.getEmail()));
    }
}
