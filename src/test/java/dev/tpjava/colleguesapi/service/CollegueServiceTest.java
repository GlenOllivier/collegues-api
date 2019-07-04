package dev.tpjava.colleguesapi.service;

import dev.tpjava.colleguesapi.controller.dto.CreerCollegueDTO;
import dev.tpjava.colleguesapi.exception.CollegueInvalideException;
import dev.tpjava.colleguesapi.exception.CollegueNonTrouveException;
import dev.tpjava.colleguesapi.entity.Collegue;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CollegueServiceTest {

    String goodName = "Bob";
    String badName = "M";

    String goodEmail = "bob@yahoo.fr";
    String goodEmail2 = "fred@gmail.com";
    String badEmail1 = "e@";
    String badEmail2 = "bop.vagmail.com";

    String goodPicture = "httptruc";
    String goodPicture2 = "httpmachin";
    String badPicture1 = "htt";
    String badPicture2 = "ahttp://www.truc.fr";

    LocalDate goodDate = LocalDate.of(LocalDate.now().getYear() - 19, 7, 2);
    LocalDate badDate1 = LocalDate.of(LocalDate.now().getYear() - 17, 7, 2);
    LocalDate badDate2 = LocalDate.of(LocalDate.now().getYear() + 21, 7, 2);

    CollegueService collegueService = new CollegueService();

    /*
     * Tests Create
     */

    @Test
    public void test_ajouter_collegue_valide() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, goodDate);
        Collegue collegue = collegueService.ajouterCollegue(collegueDTO);

        assertEquals(collegue.getLastName(), collegueDTO.getLastName());
        assertEquals(collegue.getFirstName(), collegueDTO.getFirstName());
        assertEquals(collegue.getEmail(), collegueDTO.getEmail());
        assertEquals(collegue.getPictureUrl(), collegueDTO.getPictureUrl());
        assertEquals(collegue.getBirthDate(), collegueDTO.getBirthDate());

        assertNotNull(collegue.getMatricule());
    }

    @Test
    public void test_ajouter_collegue() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, goodDate);
        Collegue collegue = collegueService.ajouterCollegue(collegueDTO);
    }

    @Test
    public void test_ajouter_collegue_nom_null() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(null, goodName, goodEmail, goodPicture, goodDate);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    public void test_ajouter_collegue_nom_court() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(badName, goodName, goodEmail, goodPicture, goodDate);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    public void test_ajouter_collegue_prenom_null() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, null, goodEmail, goodPicture, goodDate);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    public void test_ajouter_collegue_prenom_court() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, badName, goodEmail, goodPicture, goodDate);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    public void test_ajouter_collegue_email_null() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, null, goodPicture, goodDate);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    public void test_ajouter_collegue_email_court() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, badEmail1, goodPicture, goodDate);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    public void test_ajouter_collegue_email_invalide() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, badEmail2, goodPicture, goodDate);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    public void test_ajouter_collegue_photo_null() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, null, goodDate);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    public void test_ajouter_collegue_photo_court() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, badPicture1, goodDate);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    public void test_ajouter_collegue_photo_invalide() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, badPicture2, goodDate);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    public void test_ajouter_collegue_date_null() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, null);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    public void test_ajouter_collegue_date_mineur_avant() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, badDate1);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    public void test_ajouter_collegue_date_mineur_apres() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, badDate2);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    /*
     * Tests Update
     */

    @Test
    public void test_update_collegue_email_valide() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, goodDate);
        Collegue collegue = collegueService.ajouterCollegue(collegueDTO);

        collegueService.updateEmail(collegue.getMatricule(), goodEmail2);
        assertEquals(goodEmail2, collegue.getEmail());
    }


    @Test
    public void test_update_collegue_email_matricule_inconnu() {

        assertThrows(CollegueNonTrouveException.class, () -> collegueService.updateEmail("M012", goodEmail));
    }


    @Test
    public void test_update_collegue_email_null() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, goodDate);
        Collegue collegue = collegueService.ajouterCollegue(collegueDTO);

        assertThrows(CollegueInvalideException.class, () ->collegueService.updateEmail(collegue.getMatricule(), null));
    }

    @Test
    public void test_update_collegue_email_court() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, goodDate);
        Collegue collegue = collegueService.ajouterCollegue(collegueDTO);

        assertThrows(CollegueInvalideException.class, () ->collegueService.updateEmail(collegue.getMatricule(), badEmail1));
    }

    @Test
    public void test_update_collegue_email_invalide() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, goodDate);
        Collegue collegue = collegueService.ajouterCollegue(collegueDTO);

        assertThrows(CollegueInvalideException.class, () ->collegueService.updateEmail(collegue.getMatricule(), badEmail2));
    }

    @Test
    public void test_update_collegue_photo_valide() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, goodDate);
        Collegue collegue = collegueService.ajouterCollegue(collegueDTO);

        collegueService.updatePictureUrl(collegue.getMatricule(), goodPicture2);
        assertEquals(goodPicture2, collegue.getPictureUrl());
    }


    @Test
    public void test_update_collegue_photo_matricule_inconnu() {

        assertThrows(CollegueNonTrouveException.class, () -> collegueService.updatePictureUrl("M012", goodPicture));
    }


    @Test
    public void test_update_collegue_photo_null() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, goodDate);
        Collegue collegue = collegueService.ajouterCollegue(collegueDTO);

        assertThrows(CollegueInvalideException.class, () ->collegueService.updatePictureUrl(collegue.getMatricule(), null));
    }

    @Test
    public void test_update_collegue_photo_court() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, goodDate);
        Collegue collegue = collegueService.ajouterCollegue(collegueDTO);

        assertThrows(CollegueInvalideException.class, () ->collegueService.updatePictureUrl(collegue.getMatricule(), badPicture1));
    }

    @Test
    public void test_update_collegue_photo_invalide() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, goodDate);
        Collegue collegue = collegueService.ajouterCollegue(collegueDTO);

        assertThrows(CollegueInvalideException.class, () ->collegueService.updatePictureUrl(collegue.getMatricule(), badPicture2));
    }
}