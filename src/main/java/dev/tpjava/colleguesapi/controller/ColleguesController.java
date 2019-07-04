package dev.tpjava.colleguesapi.controller;

import dev.tpjava.colleguesapi.controller.exception.CollegueInvalideException;
import dev.tpjava.colleguesapi.entity.Collegue;
import dev.tpjava.colleguesapi.util.Constantes;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(
        path = "/collegues"
)
public class ColleguesController {

    @RequestMapping(
            method = RequestMethod.GET
    )
    public List<String> getCollegues(
            @RequestParam String nom
    ) {
        return Constantes.COLLEGUE_SERVICE.rechercheParNom(nom)
                .stream()
                .map(c -> c.getMatricule())
                .collect(Collectors.toList());
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/{matricule}"
    )
    public Collegue getCollegue(
            @PathVariable String matricule
    ) {
        Collegue c = Constantes.COLLEGUE_SERVICE.rechercherParMatricule(matricule);
        return c;
    }

    @RequestMapping(
            method = RequestMethod.POST
    )
    public Collegue ajouterCollegue(
            @RequestBody Collegue c
    ){
        return Constantes.COLLEGUE_SERVICE.ajouterCollegue(c);
    }

    @RequestMapping(
            method = RequestMethod.PATCH,
            path = "/{matricule}"
    )
    public Collegue updateCollegue(
            @PathVariable String matricule,
            @RequestBody Collegue c
    ) {
        if (c.getPictureUrl() != null ) {
            Constantes.COLLEGUE_SERVICE.updatePictureUrl(matricule, c.getPictureUrl());
        }
        if (c.getEmail() != null) {
            Constantes.COLLEGUE_SERVICE.updateEmail(matricule, c.getEmail());
        }
        return Constantes.COLLEGUE_SERVICE.rechercherParMatricule(matricule);
    }
}
