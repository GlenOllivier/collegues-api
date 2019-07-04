package dev.tpjava.colleguesapi.service;

import dev.tpjava.colleguesapi.controller.exception.CollegueInvalideException;
import dev.tpjava.colleguesapi.controller.exception.CollegueNonTrouveException;
import dev.tpjava.colleguesapi.entity.Collegue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CollegueServiceTest {

    String goodName = "Bob";
    String badName = "M";

    String goodEmail1 = "bob@yahoo.fr";
    String goodEmail2 = "@ba";
    String badEmail1 = "e@";
    String badEmail2 = "bop.vagmail.com";

    String goodPicture = "httptruc";
    String badPicture1 = "ahttp://www.truc.fr";
    String badPicture2 = "htt";

    LocalDate goodDate = LocalDate.of(2001, 7, 2);
    LocalDate badDate1 = LocalDate.of(2002, 7, 2);
    LocalDate badDate2 = LocalDate.of(2040, 7, 2);

    Collegue c;
    CollegueService collegueService = new CollegueService();

    @Test
    void ajouterCollegue() {

        try {
            c = new Collegue(null, goodName, goodName, goodEmail1, goodPicture, goodDate);
            collegueService.ajouterCollegue(c);
        }catch (CollegueInvalideException e) {
            fail();
        }

        try {
            c = new Collegue(null, goodName, goodName, goodEmail2, goodPicture, goodDate);
            collegueService.ajouterCollegue(c);
        }catch (CollegueInvalideException e) {
            fail();
        }

        try {
            c = new Collegue(null, badName, goodName, goodEmail2, goodPicture, goodDate);
            collegueService.ajouterCollegue(c);
            fail();
        }catch (CollegueInvalideException e) {
        }

        try {
            c = new Collegue(null, goodName, badName, goodEmail2, goodPicture, goodDate);
            collegueService.ajouterCollegue(c);
            fail();
        }catch (CollegueInvalideException e) {
        }

        try {
            c = new Collegue(null, goodName, goodName, badEmail1, goodPicture, goodDate);
            collegueService.ajouterCollegue(c);
            fail();
        }catch (CollegueInvalideException e) {
        }

        try {
            c = new Collegue(null, goodName, goodName, badEmail2, goodPicture, goodDate);
            collegueService.ajouterCollegue(c);
            fail();
        }catch (CollegueInvalideException e) {
        }

        try {
            c = new Collegue(null, goodName, goodName, goodEmail2, badPicture1, goodDate);
            collegueService.ajouterCollegue(c);
            fail();
        }catch (CollegueInvalideException e) {
        }

        try {
            c = new Collegue(null, goodName, goodName, goodEmail2, badPicture2, goodDate);
            collegueService.ajouterCollegue(c);
            fail();
        }catch (CollegueInvalideException e) {
        }

        try {
            c = new Collegue(null, goodName, goodName, goodEmail2, goodPicture, badDate1);
            collegueService.ajouterCollegue(c);
            fail();
        }catch (CollegueInvalideException e) {
        }

        try {
            c = new Collegue(null, goodName, goodName, goodEmail2, goodPicture, badDate2);
            collegueService.ajouterCollegue(c);
            fail();
        }catch (CollegueInvalideException e) {
        }

        try {
            c = new Collegue(null, null, goodName, goodEmail1, goodPicture, goodDate);
            collegueService.ajouterCollegue(c);
            fail();
        }catch (CollegueInvalideException e) {
        }

        try {
            c = new Collegue(null, goodName, goodName, null, goodPicture, goodDate);
            collegueService.ajouterCollegue(c);
            fail();
        }catch (CollegueInvalideException e) {
        }

        try {
            c = new Collegue(null, goodName, goodName, goodEmail1, goodPicture, null);
            collegueService.ajouterCollegue(c);
            fail();
        }catch (CollegueInvalideException e) {
        }

    }

    @Test
    void updateEmail() {
        c = new Collegue(null, goodName, goodName, goodEmail1, goodPicture, goodDate);
        collegueService.ajouterCollegue(c);
        String matricule = c.getMatricule();

        try {
            collegueService.updateEmail(matricule, goodEmail2);
        } catch (CollegueInvalideException | CollegueNonTrouveException e) {
            fail();
        }

        try {
            collegueService.updateEmail("M1", goodEmail2);
            fail();
        } catch (CollegueInvalideException e) {
            fail();
        } catch ( CollegueNonTrouveException e) {
        }

        try {
            collegueService.updateEmail(matricule, badEmail1);
            fail();
        }catch ( CollegueNonTrouveException e) {
            fail();
        } catch (CollegueInvalideException e) {
        }

        try {
            collegueService.updateEmail(matricule, badEmail2);
            fail();
        } catch ( CollegueNonTrouveException e) {
            fail();
        } catch (CollegueInvalideException e) {
        }


    }

    @Test
    void updatePictureUrl() {
        c = new Collegue("M2", goodName, goodName, goodEmail1, goodPicture, goodDate);
        collegueService.ajouterCollegue(c);
        String matricule = c.getMatricule();

        try {
            collegueService.updatePictureUrl(matricule, goodPicture);
        } catch (CollegueInvalideException | CollegueNonTrouveException e) {
            fail();
        }

        try {
            collegueService.updatePictureUrl("M1", goodPicture);
            fail();
        } catch (CollegueInvalideException e) {
            fail();
        } catch ( CollegueNonTrouveException e) {
        }

        try {
            collegueService.updatePictureUrl(matricule, badPicture1);
            fail();
        }catch ( CollegueNonTrouveException e) {
            fail();
        } catch (CollegueInvalideException e) {
        }

        try {
            collegueService.updatePictureUrl(matricule, badPicture2);
            fail();
        }catch ( CollegueNonTrouveException e) {
            fail();
        } catch (CollegueInvalideException e) {
        }
    }
}