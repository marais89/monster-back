package com.monster.business.dto;

import java.time.LocalDateTime;

public class BusinessDto {


    public int id;

    public String name;

    public String descreption;

    public int creatorId;

    public byte[] logo;

    public LocalDateTime creationDate;

    public String physicalAddress;

    public BusinessStatus status;
}
