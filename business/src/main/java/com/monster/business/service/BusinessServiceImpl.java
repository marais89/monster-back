package com.monster.business.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.monster.business.dto.BusinessDto;
import com.monster.business.dto.BusinessGroupDto;
import com.monster.business.dto.BusinessStatus;
import com.monster.business.dto.UserBusinessRelationDto;
import com.monster.business.entity.Business;
import com.monster.business.entity.BusinessGroup;
import com.monster.business.entity.UserBusinessRelation;
import com.monster.business.entity.UserBusinessStatus;
import com.monster.business.mapper.BusinessMapper;
import com.monster.business.repository.BusinessGroupRepository;
import com.monster.business.repository.BusinessRepository;
import com.monster.business.repository.UserBusinessRelationRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private BusinessGroupRepository businessGroupRepository;

    @Autowired
    private UserBusinessRelationRepository userBusinessRelationRepository;

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

    @Override
    public BusinessGroupDto saveBusinessGroup(BusinessGroupDto businessGroupDto) {
        return businessMapper.mapToDto(businessGroupRepository.save(businessMapper.mapToEntity(businessGroupDto)));
    }

    @Override
    public List<BusinessGroupDto> findBusinessGroupByBusinessId(int businessId) {

        return businessGroupRepository.findBusinessGroupByBusinessId(businessId)
                .stream()
                .map(bg -> businessMapper.mapToDto(bg))
                .collect(Collectors.toList());
    }

    @Override
    public BusinessGroupDto desableBusinessGroup(int idBusinessGroup) throws NotFoundException {

        Optional<BusinessGroup> businessGroup = businessGroupRepository.findById(idBusinessGroup);
        if (businessGroup.isPresent()) {
            businessGroup.get().setActive(false);
            return businessMapper.mapToDto(businessGroupRepository.save(businessGroup.get()));
        }
        throw new NotFoundException("Business Group not found !");
    }

    @Override
    public UserBusinessRelationDto saveUserBusinessRelation(UserBusinessRelationDto userBusinessRelationDto) {
        return businessMapper.mapToDto(userBusinessRelationRepository.save(businessMapper.mapToEntity(userBusinessRelationDto)));
    }

    @Override
    public List<UserBusinessRelationDto> findUserBusinessRelationByUserId(int userId) {
        return userBusinessRelationRepository.findUserBusinessRelationsByIndividuId(userId)
                .stream()
                .map(ubr -> businessMapper.mapToDto(ubr))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserBusinessRelationDto> findUserBusinessRelationByBusinessId(int businessId) {
        return userBusinessRelationRepository.findUserBusinessRelationsByIndividuId(businessId)
                .stream()
                .map(ubr -> businessMapper.mapToDto(ubr))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserBusinessRelationDto> findUserBusinessRelationByGroupId(int groupId) {
        return userBusinessRelationRepository.findUserBusinessRelationsByIndividuId(groupId)
                .stream()
                .map(ubr -> businessMapper.mapToDto(ubr))
                .collect(Collectors.toList());
    }

    @Override
    public UserBusinessRelationDto updateStatusUserBusinessRelation(int userBusinessRelationId, UserBusinessStatus status) throws NotFoundException {
        Optional<UserBusinessRelation> ubr = userBusinessRelationRepository.findById(userBusinessRelationId);
        if (ubr.isPresent()) {
            ubr.get().setStatus(status);
            return businessMapper.mapToDto(userBusinessRelationRepository.save(ubr.get()));
        }
        throw new NotFoundException("user business relation not found  !");
    }

    @Override
    public List<UserBusinessRelationDto> findUserBusinessRelationByBusinessIdAndUserId(int businessId, int userId) {
        return userBusinessRelationRepository.findUserBusinessRelationByBusinessIdAndIndividuId(businessId, userId)
                .stream()
                .map(ubr -> businessMapper.mapToDto(ubr))
                .collect(Collectors.toList());
    }


}
