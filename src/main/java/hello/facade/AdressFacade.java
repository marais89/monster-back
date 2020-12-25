package hello.facade;

import hello.dto.AdressDto;
import hello.service.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdressFacade {

    @Autowired
    private AdressService adressService;

    public List<AdressDto> findAdress(List<String> keys) {
        return adressService.findAdress(keys);
    }
}
