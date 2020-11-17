package hello.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;

@ApiModel
public class UserDto {

  public String username;
  public String password;
  public boolean enabled;

  public UserDto() {
  }
}
