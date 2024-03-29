package dev.tpjava.colleguesapi.controller;

import dev.tpjava.colleguesapi.controller.dto.AccountDto;
import dev.tpjava.colleguesapi.controller.dto.CreerCollegueDTO;
import dev.tpjava.colleguesapi.controller.dto.PhotoDTO;
import dev.tpjava.colleguesapi.controller.dto.UpdateCollegueDTO;
import dev.tpjava.colleguesapi.entity.Collegue;
import dev.tpjava.colleguesapi.service.CollegueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(allowCredentials = "true")
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
        return collegueService.rechercheParNom(nom.toUpperCase())
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

    @Secured("ROLE_ADMIN")
    @RequestMapping(
            method = RequestMethod.POST
    )
    public Collegue ajouterCollegue(
            @RequestBody CreerCollegueDTO c
    ){
        return collegueService.ajouterCollegue(c);
    }

    @Secured("ROLE_ADMIN")
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

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/photos"
    )
    public List<PhotoDTO> getPictures() {
        return collegueService.getAllPictures();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/verifier-doublons"
    )
    public boolean verifEmail(@RequestParam String email) {

        return collegueService.verifEmail(email.toLowerCase());
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/accounts"
    )
    public List<AccountDto> getAccounts() {
        return collegueService.getAccounts();
    }
}
