package hello.service;

import hello.dto.AdressDto;

import java.util.List;

public interface AdressService {

    public List<AdressDto> findAdress(List<String> keys);
}
