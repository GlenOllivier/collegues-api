package dev.tpjava.colleguesapi.controller;

import dev.tpjava.colleguesapi.controller.dto.CreerCollegueDTO;
import dev.tpjava.colleguesapi.controller.dto.UpdateCollegueDTO;
import dev.tpjava.colleguesapi.entity.Collegue;
import dev.tpjava.colleguesapi.service.CollegueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(
        path = "/collegues"
)
public class ColleguesController {

    @Autowired
    private CollegueService collegueService;


    @RequestMapping(
            method = RequestMethod.GET
    )
    public List<String> getCollegues(
            @RequestParam String nom
    ) {
        return collegueService.rechercheParNom(nom)
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
        Collegue c = collegueService.rechercherParMatricule(matricule);
        return c;
    }

    @RequestMapping(
            method = RequestMethod.POST
    )
    public Collegue ajouterCollegue(
            @RequestBody CreerCollegueDTO c
    ){
        return collegueService.ajouterCollegue(c);
    }

    @RequestMapping(
            method = RequestMethod.PATCH,
            path = "/{matricule}"
    )
    public Collegue updateCollegue(
            @PathVariable String matricule,
            @RequestBody UpdateCollegueDTO c
    ) {
        if (c.getPictureUrl() != null ) {
            collegueService.updatePictureUrl(matricule, c.getPictureUrl());
        }
        if (c.getEmail() != null) {
            collegueService.updateEmail(matricule, c.getEmail());
        }
        return collegueService.rechercherParMatricule(matricule);
    }
}
