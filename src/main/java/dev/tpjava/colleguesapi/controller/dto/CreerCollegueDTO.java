package dev.tpjava.colleguesapi.controller.dto;

import dev.tpjava.colleguesapi.entity.Collegue;

import java.time.LocalDate;

public class CreerCollegueDTO {
    private String lastName;
    private String firstName;
    private String email;
    private String pictureUrl;
    private LocalDate birthDate;

    public CreerCollegueDTO() {
    }

    public CreerCollegueDTO( String lastName, String firstName, String email, String pictureUrl, LocalDate birthDate) {
        this.lastName = lastName.toUpperCase();
        this.firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
        this.email = email.toLowerCase();
        this.pictureUrl = pictureUrl;
        this.birthDate = birthDate;
    }

    public Collegue toCollegue() {
        return new Collegue(null, lastName, firstName, email, pictureUrl, birthDate);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.toUpperCase();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

}
