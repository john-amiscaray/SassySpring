package us.john.hanna.cps530assignment.domain;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignupRequest {

    private String username;
    private String password;
    private String confirmPassword;

}
