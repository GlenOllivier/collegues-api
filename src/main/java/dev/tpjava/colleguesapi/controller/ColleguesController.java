package dev.tpjava.colleguesapi.controller;

import dev.tpjava.colleguesapi.entity.Collegue;
import dev.tpjava.colleguesapi.util.Constantes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
