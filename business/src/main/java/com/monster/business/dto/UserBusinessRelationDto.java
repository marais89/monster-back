package com.monster.business.dto;

import com.monster.business.entity.UserBusinessRole;
import com.monster.business.entity.UserBusinessStatus;

public class UserBusinessRelationDto {

    public int id;

    public int individuId;

    public BusinessDto business;

    public BusinessGroupDto businessGroup;

    public UserBusinessRole role;

    public UserBusinessStatus status;

}
