package dev.tpjava.colleguesapi.service;

import dev.tpjava.colleguesapi.controller.dto.CreerCollegueDTO;
import dev.tpjava.colleguesapi.exception.CollegueInvalideException;
import dev.tpjava.colleguesapi.exception.CollegueNonTrouveException;
import dev.tpjava.colleguesapi.entity.Collegue;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest()

//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

class CollegueServiceTest {

    private final String goodName = "Bob";
    private final String badName = "M";

    private final String goodEmail = "bob@yahoo.fr";
    private final String goodEmail2 = "fred@gmail.com";
    private final String badEmail1 = "e@";
    private final String badEmail2 = "bop.vagmail.com";

    private final String goodPicture = "httptruc";
    private final String goodPicture2 = "httpmachin";
    private final String badPicture1 = "htt";
    private final String badPicture2 = "ahttp://www.truc.fr";

    private final LocalDate goodDate = LocalDate.of(LocalDate.now().getYear() - 19, 7, 2);
    private final LocalDate badDate1 = LocalDate.of(LocalDate.now().getYear() - 17, 7, 2);
    private final LocalDate badDate2 = LocalDate.of(LocalDate.now().getYear() + 21, 7, 2);

    @Autowired
    private CollegueService collegueService;

    /*
     * Tests Create
     */

    @Test
    void test_ajouter_collegue_valide() {

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
    void test_ajouter_collegue_nom_null() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(null, goodName, goodEmail, goodPicture, goodDate);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    void test_ajouter_collegue_nom_court() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(badName, goodName, goodEmail, goodPicture, goodDate);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    void test_ajouter_collegue_prenom_null() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, null, goodEmail, goodPicture, goodDate);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    void test_ajouter_collegue_prenom_court() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, badName, goodEmail, goodPicture, goodDate);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    void test_ajouter_collegue_email_null() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, null, goodPicture, goodDate);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    void test_ajouter_collegue_email_court() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, badEmail1, goodPicture, goodDate);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    void test_ajouter_collegue_email_invalide() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, badEmail2, goodPicture, goodDate);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    void test_ajouter_collegue_photo_null() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, null, goodDate);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    void test_ajouter_collegue_photo_court() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, badPicture1, goodDate);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    void test_ajouter_collegue_photo_invalide() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, badPicture2, goodDate);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    void test_ajouter_collegue_date_null() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, null);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    void test_ajouter_collegue_date_mineur_avant() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, badDate1);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    @Test
    void test_ajouter_collegue_date_mineur_apres() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, badDate2);
        assertThrows(CollegueInvalideException.class, () ->collegueService.ajouterCollegue(collegueDTO));
    }

    /*
     * Tests Update
     */

    @Test
    void test_update_collegue_email_valide() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, goodDate);
        Collegue collegue = collegueService.ajouterCollegue(collegueDTO);

        Collegue collegueModifie = collegueService.updateEmail(collegue.getMatricule(), goodEmail2);
        assertEquals(goodEmail2, collegueModifie.getEmail());
    }


    @Test
    void test_update_collegue_email_matricule_inconnu() {

        assertThrows(CollegueNonTrouveException.class, () -> collegueService.updateEmail("M012", goodEmail));
    }


    @Test
    void test_update_collegue_email_null() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, goodDate);
        Collegue collegue = collegueService.ajouterCollegue(collegueDTO);

        assertThrows(CollegueInvalideException.class, () ->collegueService.updateEmail(collegue.getMatricule(), null));
    }

    @Test
    void test_update_collegue_email_court() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, goodDate);
        Collegue collegue = collegueService.ajouterCollegue(collegueDTO);

        assertThrows(CollegueInvalideException.class, () ->collegueService.updateEmail(collegue.getMatricule(), badEmail1));
    }

    @Test
    void test_update_collegue_email_invalide() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, goodDate);
        Collegue collegue = collegueService.ajouterCollegue(collegueDTO);

        assertThrows(CollegueInvalideException.class, () ->collegueService.updateEmail(collegue.getMatricule(), badEmail2));
    }

    @Test
    void test_update_collegue_photo_valide() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, goodDate);
        Collegue collegue = collegueService.ajouterCollegue(collegueDTO);

        Collegue collegueModifie = collegueService.updatePictureUrl(collegue.getMatricule(), goodPicture2);
        assertEquals(goodPicture2, collegueModifie.getPictureUrl());
    }


    @Test
    void test_update_collegue_photo_matricule_inconnu() {

        assertThrows(CollegueNonTrouveException.class, () -> collegueService.updatePictureUrl("M012", goodPicture));
    }


    @Test
    void test_update_collegue_photo_null() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, goodDate);
        Collegue collegue = collegueService.ajouterCollegue(collegueDTO);

        assertThrows(CollegueInvalideException.class, () ->collegueService.updatePictureUrl(collegue.getMatricule(), null));
    }

    @Test
    void test_update_collegue_photo_court() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, goodDate);
        Collegue collegue = collegueService.ajouterCollegue(collegueDTO);

        assertThrows(CollegueInvalideException.class, () ->collegueService.updatePictureUrl(collegue.getMatricule(), badPicture1));
    }

    @Test
    void test_update_collegue_photo_invalide() {

        CreerCollegueDTO collegueDTO = new CreerCollegueDTO(goodName, goodName, goodEmail, goodPicture, goodDate);
        Collegue collegue = collegueService.ajouterCollegue(collegueDTO);

        assertThrows(CollegueInvalideException.class, () ->collegueService.updatePictureUrl(collegue.getMatricule(), badPicture2));
    }
}