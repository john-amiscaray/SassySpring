package us.john.hanna.cps530assignment.services;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import us.john.hanna.cps530assignment.domain.LoginRequest;
import us.john.hanna.cps530assignment.domain.SignupRequest;
import us.john.hanna.cps530assignment.entities.User;
import us.john.hanna.cps530assignment.exceptions.BadAuthRequest;

public interface AuthService {

    void signUp(SignupRequest request) throws BadAuthRequest;

    String login(LoginRequest request) throws BadAuthRequest;

    User getCurrentlySignedInUser() throws BadAuthRequest;

    UsernamePasswordAuthenticationToken verifyToken(String token) throws BadCredentialsException;

}
