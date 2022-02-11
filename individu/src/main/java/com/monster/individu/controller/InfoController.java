package com.monster.individu.controller;

import com.monster.individu.dto.*;
import com.monster.individu.facade.AdressFacade;
import com.monster.individu.facade.IndividuFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

@Valid
@Api(tags = "info Individus")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class InfoController {

    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String NUMERO = "numero";
    public static final String ID = "id";
    public static final String GOUVERNORAT = "gouvernorat";

    @Autowired
    private IndividuFacade individuFacade;

    @Autowired
    private AdressFacade adressFacade;

    @RequestMapping(value = "/individus", method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "liste d'individu", authorizations = @Authorization("jwt"))
    public List<IndividuDto> findAll() {
        return individuFacade.findAll();
    }

    @RequestMapping(path = "/individus/username/{username}", method = RequestMethod.GET)
    @ApiOperation(value = "recupére les informations d'individu par son username", authorizations = @Authorization("jwt"))
    public IndividuGlobalInfosDto getByUsername(@PathVariable(USERNAME) String username) {
        return individuFacade.findByUsername(username);
    }

    @RequestMapping(path = "/individus/checkEmail", method = RequestMethod.POST)
    @ApiOperation(value = "checker l'utilisateur par son email")
    public CheckUserDto getByEmail(@RequestBody UpdateStatusRequest updateStatusRequest) {
        return individuFacade.checkUserByEmail(updateStatusRequest);
    }

    @RequestMapping(path = "/individus/numeroTel/{numero}", method = RequestMethod.GET)
    @ApiOperation(value = "recupere individu par nemero téléphone", authorizations = @Authorization("jwt"))
    public List<IndividuDto> getByNumeroTel(@PathVariable(NUMERO) String numero) {
        return individuFacade.findByNumeroTel(numero);
    }

    @RequestMapping(path = "/individus/create", method = RequestMethod.POST)
    @ApiOperation(value = "ajout d'un individuDto")
    public SavingResponseDto save(@RequestBody IndividuRequest individuRequest) {
        return individuFacade.save(individuRequest);
    }

    @RequestMapping(path = "/individus/update", method = RequestMethod.POST)
    @ApiOperation(value = "met a jour d'un individuDto", authorizations = @Authorization("jwt"))
    public IndividuDto update(@RequestBody IndividuRequest individuRequest) {
        return individuFacade.update(individuRequest);
    }

    @RequestMapping(path = "/individus/delete/id/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "supprimer un individu par id", authorizations = @Authorization("jwt"))
    public void delete(@PathVariable(ID) Long id) {
        individuFacade.delete(id);
    }

    @RequestMapping(path = "/individus/suspend/username/{username}", method = RequestMethod.POST)
    @ApiOperation(value = "bloquer un individu par username", authorizations = @Authorization("jwt"))
    public List<IndividuDto> suspend(@PathVariable(USERNAME) String username, @RequestBody UpdateStatusRequest updateStatusRequest) {
        return individuFacade.updateIndividuStatus(username, IndividuStatus.BLOQUE, updateStatusRequest);
    }

    @RequestMapping(path = "/individus/resume/username/{username}", method = RequestMethod.POST)
    @ApiOperation(value = "reactiver un individu par username", authorizations = @Authorization("jwt"))
    public List<IndividuDto> resume(@PathVariable(USERNAME) String username, @RequestBody UpdateStatusRequest updateStatusRequest) {
        return individuFacade.updateIndividuStatus(username, IndividuStatus.ACTIVE, updateStatusRequest);
    }

    @RequestMapping(path = "/individus/deactivate/username/{username}", method = RequestMethod.POST)
    @ApiOperation(value = "résilier un individu par username", authorizations = @Authorization("jwt"))
    public List<IndividuDto> deactivate(@PathVariable(USERNAME) String username, @RequestBody UpdateStatusRequest updateStatusRequest) {
        return individuFacade.updateIndividuStatus(username, IndividuStatus.RESILIE, updateStatusRequest);
    }

    @RequestMapping(path = "/individus/username/{username}/key/validate", method = RequestMethod.POST)
    @ApiOperation(value = "validation keys", authorizations = @Authorization("jwt"))
    public Boolean validateKey(@PathVariable(USERNAME) String username, @RequestBody ValidateKeyRequest validateKeyRequest) {
        return individuFacade.validateKey(username, validateKeyRequest);
    }

    //Verify endpoint security
    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () -> new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }

    @RequestMapping(path = "individus/adress/gouvernorat/{gouvernorat}", method = RequestMethod.GET)
    @ApiOperation(value = "get adress", authorizations = @Authorization("jwt"))
    public List<AdressDto> findAdressByGouvernorat(@PathVariable(GOUVERNORAT) int gouvernoratId) {
        return adressFacade.findAdressByGouvernorat(gouvernoratId);
    }

    @RequestMapping(path = "individus/allTown", method = RequestMethod.GET)
    @ApiOperation(value = "get all town", authorizations = @Authorization("jwt"))
    public List<TownDto> findAllTown() {
        return adressFacade.findAllTown();
    }

}
