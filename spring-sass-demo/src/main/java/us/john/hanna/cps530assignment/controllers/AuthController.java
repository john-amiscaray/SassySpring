package us.john.hanna.cps530assignment.controllers;

import lombok.AllArgsConstructor;
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
// Prefix all the endpoints here with /api/auth
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

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
