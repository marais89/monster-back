package com.monster.business.service;

import java.time.LocalDateTime;
import java.util.List;

import com.monster.business.dto.BusinessDto;
import com.monster.business.dto.BusinessStatus;
import com.monster.business.entity.Business;
import com.monster.business.mapper.BusinessMapper;
import com.monster.business.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public BusinessDto getBusinessByCreatorId(long creatorId) {
        List<Business> businessList = businessRepository.findBusinessByCreatorId((int) creatorId);
        return businessList.stream().findFirst().map(businessMapper::mapToDto).orElse(null);
    }

    @Override
    public BusinessDto saveBusiness(BusinessDto businessDto) {
        businessDto.status = BusinessStatus.WAITING;
        businessDto.creationDate = LocalDateTime.now();
        return businessMapper.mapToDto(businessRepository.save(businessMapper.mapToEntity(businessDto)));
    }
}
