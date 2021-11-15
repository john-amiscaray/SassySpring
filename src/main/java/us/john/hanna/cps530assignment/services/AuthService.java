package us.john.hanna.cps530assignment.services;

import us.john.hanna.cps530assignment.domain.LoginRequest;
import us.john.hanna.cps530assignment.domain.SignupRequest;

public interface AuthService {

    void signUp(SignupRequest request);

    String login(LoginRequest request);

}
