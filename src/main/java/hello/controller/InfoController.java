package hello.controller;

import hello.dto.Individu;
import hello.facade.IndividuFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Valid
@Api(tags = "info Individus")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class InfoController {

    public static final String EMAIL = "email";
    public static final String NUMERO = "numero";

    @Autowired
    private IndividuFacade individuFacade;



    @RequestMapping(value = "/individus", method = RequestMethod.GET)
    @ApiOperation(value = "liste d'individu")
    public List<Individu> findAll() {
        return individuFacade.findAll();
    }

    @RequestMapping(path = "/individus/email/{email}", method = RequestMethod.GET)
    @ApiOperation(value = "recupere individu par adresse email")
    public List<Individu> getByEmail(@PathVariable(EMAIL) String email) {
        return individuFacade.getByEmail(email);
    }

    @RequestMapping(path = "/individus/numeroTel/{numero}", method = RequestMethod.GET)
    @ApiOperation(value = "recupere individu par nemero telephone")
    public List<Individu> getByNumeroTel(@PathVariable(NUMERO) Integer numero) {
        return individuFacade.getByNumeroTel(numero);
    }

    @RequestMapping(path = "/individus" , method = RequestMethod.POST)
    @ApiOperation(value = "ajout d'un individu")
    public void save(@RequestBody Individu individu){
        individuFacade.save(individu);
    }

}