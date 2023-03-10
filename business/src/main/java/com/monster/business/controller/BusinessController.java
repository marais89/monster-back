package com.monster.business.controller;

import java.util.List;

import com.monster.business.dto.BusinessDto;
import com.monster.business.dto.BusinessGroupDto;
import com.monster.business.dto.UserBusinessRelationDto;
import com.monster.business.entity.UserBusinessStatus;
import com.monster.business.facade.BusinessFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "business")
@RestController
public class BusinessController {


    private static final String CREATORID = "creatorId";
    private static final String BUSINESS_GROUP_ID = "businessGroupId";
    public static final String BUSINESS_ID = "businessId";
    public static final String USER_ID = "userId";
    public static final String GROUP_ID = "groupId";

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


    @PostMapping("/business/businessGroup/save")
    @ApiOperation(value = "save Business group informations", authorizations = @Authorization("jwt"))
    public BusinessGroupDto saveBusinessGroup(@RequestBody BusinessGroupDto businessGroupDto) {
        return businessFacade.saveBusinessGroup(businessGroupDto);
    }

    @GetMapping("/business/{businessId}/businessGroups")
    @ApiOperation(value = "retrieve all Business groups of a business", authorizations = @Authorization("jwt"))
    public List<BusinessGroupDto> findAllBusinessGroup(@PathVariable(BUSINESS_ID) int businessId) {
        return businessFacade.findAllBusinessGroup(businessId);
    }

    @PostMapping("/business/businessGroup/businessGroupId/{businessGroupId}")
    @ApiOperation(value = "desable Business group by id", authorizations = @Authorization("jwt"))
    public BusinessGroupDto desableBusinessGroup(@PathVariable(BUSINESS_GROUP_ID) int idBusinessGroup) throws NotFoundException {
        return businessFacade.desableBusinessGroup(idBusinessGroup);
    }

    @PostMapping("/business/userBusinessRelation/save")
    @ApiOperation(value = "save user business relation", authorizations = @Authorization("jwt"))
    public UserBusinessRelationDto saveUserBusinessRelation(UserBusinessRelationDto userBusinessRelationDto) {
        return businessFacade.saveUserBusinessRelation(userBusinessRelationDto);
    }

    @PostMapping("/business/userBusinessRelation/userId/{userId}")
    @ApiOperation(value = "find user business relation by user id", authorizations = @Authorization("jwt"))
    public List<UserBusinessRelationDto> findUserBusinessRelationByUserId(@PathVariable(USER_ID) int userId) {
        return businessFacade.findUserBusinessRelationByUserId(userId);
    }

    @PostMapping("/business/userBusinessRelation/businessId/{businessId}")
    @ApiOperation(value = "find user business relation by business id", authorizations = @Authorization("jwt"))
    public List<UserBusinessRelationDto> findUserBusinessRelationByBusinessId(@PathVariable(BUSINESS_ID) int businessId) {
        return businessFacade.findUserBusinessRelationByBusinessId(businessId);
    }

    @PostMapping("/business/userBusinessRelation/userId/{groupId}")
    @ApiOperation(value = "find user business relation by group id", authorizations = @Authorization("jwt"))
    public List<UserBusinessRelationDto> findUserBusinessRelationByGroupId(@PathVariable(GROUP_ID) int groupId) {
        return businessFacade.findUserBusinessRelationByGroupId(groupId);
    }

    @PostMapping("/business/userBusinessRelation/{userBusinessRelationId}/suspend")
    @ApiOperation(value = "suspend user business relation", authorizations = @Authorization("jwt"))
    public UserBusinessRelationDto suspendUserBusinessRelation(@PathVariable("userBusinessRelationId") int userBusinessRelationId) throws NotFoundException {
        return businessFacade.updateStatusUserBusinessRelation(userBusinessRelationId, UserBusinessStatus.SUSPENDED);
    }

    @PostMapping("/business/userBusinessRelation/{userBusinessRelationId}/disable")
    @ApiOperation(value = "disable user business relation", authorizations = @Authorization("jwt"))
    public UserBusinessRelationDto disableUserBusinessRelation(@PathVariable("userBusinessRelationId") int userBusinessRelationId) throws NotFoundException {
        return businessFacade.updateStatusUserBusinessRelation(userBusinessRelationId, UserBusinessStatus.DISABLE);
    }

    @PostMapping("/business/userBusinessRelation/{userBusinessRelationId}/reactivate")
    @ApiOperation(value = "reactivate business relation", authorizations = @Authorization("jwt"))
    public UserBusinessRelationDto reactivateUserBusinessRelation(@PathVariable("userBusinessRelationId") int userBusinessRelationId) throws NotFoundException {
        return businessFacade.updateStatusUserBusinessRelation(userBusinessRelationId, UserBusinessStatus.ACTIF);
    }


}
