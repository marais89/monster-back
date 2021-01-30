package individu.facade;

import individu.dto.AdressDto;
import individu.dto.TownDto;
import individu.service.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdressFacade {

    @Autowired
    private AdressService adressService;

    public List<AdressDto> findAdressByGouvernorat(int gouvernoratId) {
        return adressService.findAdressByGouvernorat(gouvernoratId);
    }

    public List<TownDto> findAllTown(){
        return adressService.findAllTown();
    }
}
