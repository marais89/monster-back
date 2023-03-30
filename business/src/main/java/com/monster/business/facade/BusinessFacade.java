package com.monster.business.facade;

import java.util.List;

import com.monster.business.dto.BusinessDto;
import com.monster.business.dto.BusinessGroupDto;
import com.monster.business.dto.UserBusinessRelationDto;
import com.monster.business.entity.UserBusinessStatus;
import com.monster.business.service.BusinessService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessFacade {

    @Autowired
    private BusinessService businessService;

    public BusinessDto retrieveBusinessByCreator(int creatorId) {
        return businessService.getBusinessByCreatorId(creatorId);
    }

    public BusinessDto saveBusiness(BusinessDto businessDto) {
        return businessService.saveBusiness(businessDto);
    }

    public BusinessDto updateBusiness(BusinessDto businessDto) {
        return businessService.saveBusiness(businessDto);
    }

    public BusinessGroupDto saveBusinessGroup(BusinessGroupDto businessGroupDto) {
        return businessService.saveBusinessGroup(businessGroupDto);
    }

    public List<BusinessGroupDto> findAllBusinessGroup(int businessId) {
        return businessService.findBusinessGroupByBusinessId(businessId);
    }

    public BusinessGroupDto desableBusinessGroup(int idBusinessGroup) throws NotFoundException {
        return businessService.desableBusinessGroup(idBusinessGroup);
    }

    public UserBusinessRelationDto saveUserBusinessRelation(UserBusinessRelationDto userBusinessRelationDto) {
        return businessService.saveUserBusinessRelation(userBusinessRelationDto);
    }

    public List<UserBusinessRelationDto> findUserBusinessRelationByUserId(int userId) {
        return businessService.findUserBusinessRelationByUserId(userId);
    }

    public List<UserBusinessRelationDto> findUserBusinessRelationByBusinessId(int businessId) {
        return businessService.findUserBusinessRelationByBusinessId(businessId);
    }

    public List<UserBusinessRelationDto> findUserBusinessRelationByGroupId(int groupId) {
        return businessService.findUserBusinessRelationByGroupId(groupId);
    }

    public UserBusinessRelationDto updateStatusUserBusinessRelation(int userBusinessRelationId, UserBusinessStatus status) throws NotFoundException {
        return businessService.updateStatusUserBusinessRelation(userBusinessRelationId, status);
    }

    public List<UserBusinessRelationDto> findUserBusinessRelationByBusinessIdAndUserId(int businessId, int userId) {
        return businessService.findUserBusinessRelationByBusinessIdAndUserId(businessId, userId);
    }
}
