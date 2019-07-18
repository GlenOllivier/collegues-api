package dev.tpjava.colleguesapi.controller.dto;

import dev.tpjava.colleguesapi.entity.Collegue;

import java.time.LocalDate;

public class UpdateCollegueDTO {
    private String email;
    private String pictureUrl;

    public UpdateCollegueDTO() {
    }

    public UpdateCollegueDTO(  String email, String pictureUrl) {
        this.email = email.toLowerCase();
        this.pictureUrl = pictureUrl;
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
}
