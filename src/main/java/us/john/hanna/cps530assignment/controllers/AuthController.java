package us.john.hanna.cps530assignment.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.john.hanna.cps530assignment.domain.LoginRequest;
import us.john.hanna.cps530assignment.domain.SignupRequest;
import us.john.hanna.cps530assignment.exceptions.BadAuthRequest;
import us.john.hanna.cps530assignment.services.AuthService;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final ResponseEntity<Void> notImplemented;
    private final AuthService authService;

    public AuthController(@Qualifier("notImplemented") ResponseEntity<Void> notImplemented, AuthService authService){

        this.notImplemented = notImplemented;
        this.authService = authService;

    }

    @PostMapping("signup")
    public ResponseEntity<String> signUp(@RequestBody SignupRequest request) throws BadAuthRequest {

        authService.signUp(request);
        return ResponseEntity.ok("signed up successfully");

    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) throws BadAuthRequest {

        return ResponseEntity.ok(authService.login(request));

    }

}
