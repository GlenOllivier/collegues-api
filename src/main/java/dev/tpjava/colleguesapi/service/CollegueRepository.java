package dev.tpjava.colleguesapi.service;

import dev.tpjava.colleguesapi.entity.Collegue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CollegueRepository extends JpaRepository<Collegue, String> {
    public List<Collegue> findAllByLastName(String lastName);
}
