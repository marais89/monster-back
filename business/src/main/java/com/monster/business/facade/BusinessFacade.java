package com.monster.business.facade;

import com.monster.business.dto.BusinessDto;
import com.monster.business.service.BusinessService;
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
}
