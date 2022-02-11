package com.monster.business.service;

import com.monster.business.dto.BusinessDto;


public interface BusinessService {

    BusinessDto getBusinessByCreatorId(long userName);

    BusinessDto saveBusiness(BusinessDto businessDto);
}
