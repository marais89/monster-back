package hello.controller;

import hello.dto.IndividuDto;
import hello.dto.UserDto;
import hello.facade.IndividuFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IndividuFacade individuFacade;

    @RequestMapping(value = "/individus", method = RequestMethod.GET)
    @ApiOperation(value = "liste d'individu")
    public List<IndividuDto> findAll() {
        return individuFacade.findAll();
    }

    @RequestMapping(path = "/individus/email/{email}", method = RequestMethod.GET)
    @ApiOperation(value = "recupere individu par adresse email")
    public IndividuDto getByEmail(@PathVariable(EMAIL) String email) {
        return individuFacade.getByEmail(email);
    }

    @RequestMapping(path = "/individus/numeroTel/{numero}", method = RequestMethod.GET)
    @ApiOperation(value = "recupere individu par nemero telephone")
    public List<IndividuDto> getByNumeroTel(@PathVariable(NUMERO) String numero) {
        return individuFacade.getByNumeroTel(numero);
    }

    @RequestMapping(path = "/individus/create", method = RequestMethod.POST)
    @ApiOperation(value = "ajout d'un individuDto")
    public void save(@RequestBody IndividuDto individuDto) {
        individuFacade.save(individuDto);
    }

    @RequestMapping(path = "/individus/update", method = RequestMethod.POST)
    @ApiOperation(value = "met a jour d'un individuDto")
    public void update(@RequestBody IndividuDto individuDto) {
        individuFacade.update(individuDto);
    }

    @RequestMapping(path = "/individus/delete/id/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "supprimer un individu par id")
    public void delete(@PathVariable(ID) Long id) {
        individuFacade.delete(id);
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