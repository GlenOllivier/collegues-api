package dev.tpjava.colleguesapi.controller;

import dev.tpjava.colleguesapi.entity.Collegue;
import dev.tpjava.colleguesapi.service.exception.CollegueNonTrouveException;
import dev.tpjava.colleguesapi.util.Constantes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ){
        Collegue c = Constantes.COLLEGUE_SERVICE.rechercherParMatricule(matricule);
        return c;
    }
}
