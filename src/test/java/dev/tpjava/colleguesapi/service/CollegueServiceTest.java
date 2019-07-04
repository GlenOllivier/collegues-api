package dev.tpjava.colleguesapi.service;

import dev.tpjava.colleguesapi.controller.exception.CollegueInvalideException;
import dev.tpjava.colleguesapi.entity.Collegue;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CollegueServiceTest {

    @Test
    void ajouterCollegue() {
        String goodName = "Bob";
        String badName = "M";

        String goodEmail1 = "bob@yahoo.fr";
        String goodEmail2 = "@ba";
        String badEmail1 = "e@";
        String badEmail2 = "bop.vagmail.com";

        String goodPicture = "httptruc";
        String badPicture = "ahttp://www.truc.fr";

        LocalDate goodDate = LocalDate.of(2001, 7, 2);
        LocalDate badDate1 = LocalDate.of(2002, 7, 2);
        LocalDate badDate2 = LocalDate.of(2040, 7, 2);

        Collegue c;
        CollegueService collegueService = new CollegueService();

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
            c = new Collegue(null, goodName, goodName, goodEmail2, badPicture, goodDate);
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
}