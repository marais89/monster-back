package individu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class IndividuGlobalInfosDto {

    @ApiModelProperty(name = "informations de l'individu")
    public IndividuDto individu;

    @ApiModelProperty(name = "role de l'individu")
    public UserRole role;

    public IndividuGlobalInfosDto(IndividuDto individuDto, UserRole userRole) {
        this.individu = individuDto;
        this.role = userRole;
    }

}
