package hello.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;

@ApiModel
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {

  public String login;
  public String password;
  public String enabled;
}
