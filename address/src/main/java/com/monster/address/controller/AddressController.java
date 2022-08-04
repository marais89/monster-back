package com.monster.address.controller;

import com.monster.address.dto.AddressDetailsDto;
import com.monster.address.dto.CountryDto;
import com.monster.address.dto.GovernorateDto;
import com.monster.address.facade.AddressFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Valid
@Api(tags = "Address infos")
@RestController
public class AddressController {

    public static final String GOUVERNORAT = "gouvernorat";

    @Autowired
    private AddressFacade addressFacade;


    @RequestMapping(path = "/address/gouvernorat/{gouvernorat}", method = RequestMethod.GET)
    @ApiOperation(value = "get address by gouvernorat id", authorizations = @Authorization("jwt"))
    public List<AddressDetailsDto> findAdressByGouvernorat(@PathVariable(GOUVERNORAT) Integer gouvernoratId) {
        return addressFacade.findAdressByGouvernorat(gouvernoratId);
    }

    @RequestMapping(path = "/governorates/country/{country}", method = RequestMethod.GET)
    @ApiOperation(value = "get town by country id", authorizations = @Authorization("jwt"))
    public List<GovernorateDto> findGovernorateByCountryId(@PathVariable("country") Integer country) {
        return addressFacade.findGovernorateByCountryId(country);
    }

    @RequestMapping(path = "/countries", method = RequestMethod.GET)
    @ApiOperation(value = "get all countries", authorizations = @Authorization("jwt"))
    public List<CountryDto> retrieveAllCountries() {
        return addressFacade.retrieveAllCountries();
    }
}
