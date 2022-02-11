package com.monster.business.controller;

import com.monster.business.dto.BusinessDto;
import com.monster.business.facade.BusinessFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "business")
@RestController
public class BusinessController {


    private static final String CREATORID = "creatorId";

    @Autowired
    private BusinessFacade businessFacade;

    @GetMapping("/business/creatorId/{creatorId}")
    @ApiOperation(value = "retrieve Business By creator id", authorizations = @Authorization("jwt"))
    public BusinessDto retrieveBusinessByCreator(@PathVariable(CREATORID) int creatorId) {
        return businessFacade.retrieveBusinessByCreator(creatorId);
    }

    @PostMapping("/business/save")
    @ApiOperation(value = "save Business informations", authorizations = @Authorization("jwt"))
    public BusinessDto saveBusiness(@RequestBody BusinessDto businessDto) {
        return businessFacade.saveBusiness(businessDto);
    }

    @PostMapping("/business/update")
    @ApiOperation(value = "update Business informations", authorizations = @Authorization("jwt"))
    public BusinessDto updateBusiness(@RequestBody BusinessDto businessDto) {
        return businessFacade.updateBusiness(businessDto);
    }

}
