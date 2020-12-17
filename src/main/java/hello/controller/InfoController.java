package hello.controller;

import hello.dto.*;
import hello.facade.IndividuFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    public static final String EMAIL = "email";
    public static final String NUMERO = "numero";
    public static final String ID = "id";
    public static final String LOGIN = "login";

    @Autowired
    private IndividuFacade individuFacade;

    @RequestMapping(value = "/individus", method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "liste d'individu")
    public List<IndividuDto> findAll() {
        return individuFacade.findAll();
    }

    @RequestMapping(path = "/individus/email/{email}", method = RequestMethod.GET)
    @ApiOperation(value = "recupére les informations d'individu par son adresse email")
    public IndividuGlobalInfosDto getByEmail(@PathVariable(EMAIL) String email) {
        return individuFacade.getByEmail(email);
    }

    @RequestMapping(path = "/individus/numeroTel/{numero}", method = RequestMethod.GET)
    @ApiOperation(value = "recupere individu par nemero téléphone")
    public List<IndividuDto> getByNumeroTel(@PathVariable(NUMERO) String numero) {
        return individuFacade.getByNumeroTel(numero);
    }

    @RequestMapping(path = "/individus/create", method = RequestMethod.POST)
    @ApiOperation(value = "ajout d'un individuDto")
    public SavingResponseDto save(@RequestBody IndividuDto individuDto) {
        return individuFacade.save(individuDto);
    }

    @RequestMapping(path = "/individus/update", method = RequestMethod.POST)
    @ApiOperation(value = "met a jour d'un individuDto")
    public IndividuDto update(@RequestBody IndividuDto individuDto) {
        return individuFacade.update(individuDto);
    }

    @RequestMapping(path = "/individus/delete/id/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "supprimer un individu par id")
    public void delete(@PathVariable(ID) Long id) {
        individuFacade.delete(id);
    }

    @RequestMapping(path = "/individus/suspend/login/{login}", method = RequestMethod.POST)
    @ApiOperation(value = "bloquer un individu par login")
    public List<IndividuDto> suspend(@PathVariable(LOGIN) String login) {
        return individuFacade.updateIndividuStatus(login, IndividuStatus.BLOQUE);
    }

    @RequestMapping(path = "/individus/resume/login/{login}", method = RequestMethod.POST)
    @ApiOperation(value = "reactiver un individu par login")
    public List<IndividuDto> resume(@PathVariable(LOGIN) String login) {
        return individuFacade.updateIndividuStatus(login, IndividuStatus.ACTIVE);
    }

    @RequestMapping(path = "/individus/deactivate/login/{login}", method = RequestMethod.POST)
    @ApiOperation(value = "résilier un individu par login")
    public List<IndividuDto> deactivate(@PathVariable(LOGIN) String login) {
        return individuFacade.updateIndividuStatus(login, IndividuStatus.RESILIE);
    }

    @RequestMapping("/loggedUser")
    public UserDto findLoggedUser(@RequestBody String login) {
        return individuFacade.findUser(login);
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () -> new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }
}
