package dev.tpjava.colleguesapi.controller.dto;

public class PhotoDTO {
    private String matricule;
    private String pictureUrl;

    public PhotoDTO() {
    }

    public PhotoDTO(String matricule, String pictureUrl) {
        this.matricule = matricule;
        this.pictureUrl = pictureUrl;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
