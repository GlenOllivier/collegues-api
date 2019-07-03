package dev.tpjava.colleguesapi.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Collegue {
    private String matricule;
    private String lastName;
    private String firstName;
    private String email;
    private String pictureUrl;
    private LocalDate birthDate;

    public Collegue() {
    }

    public Collegue(String matricule, String lastName, String firstName, String email, String pictureUrl, LocalDate birthDate) {
        this.matricule = matricule;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.pictureUrl = pictureUrl;
        this.birthDate = birthDate;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collegue collegue = (Collegue) o;
        return Objects.equals(matricule, collegue.matricule) &&
                Objects.equals(lastName, collegue.lastName) &&
                Objects.equals(firstName, collegue.firstName) &&
                Objects.equals(email, collegue.email) &&
                Objects.equals(pictureUrl, collegue.pictureUrl) &&
                Objects.equals(birthDate, collegue.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricule, lastName, firstName, email, pictureUrl, birthDate);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Collegue{");
        sb.append("matricule='").append(matricule).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", pictureUrl='").append(pictureUrl).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append('}');
        return sb.toString();
    }
}
