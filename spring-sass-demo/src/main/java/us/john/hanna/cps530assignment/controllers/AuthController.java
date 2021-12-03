package us.john.hanna.cps530assignment.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

// Ignore this controller for the API docs
@Api
@RestController
// Prefix all the endpoints here with /api/auth
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("signup")
    // ApiOperation is for specifying info for the API docs
    @ApiOperation(value="Sign up", notes="Saves a new user to the database.")
    public ResponseEntity<String> signUp(@RequestBody SignupRequest request) throws BadAuthRequest {

        authService.signUp(request);
        return ResponseEntity.ok("signed up successfully");

    }

    @PostMapping("login")
    @ApiOperation(value="Log in to the app", notes="Gives back a JWT token to authorize future requests. " +
            "Put the token in the Authorization header for authenticated requests like so: 'Bearer *token*' where *token* is the JWT.")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) throws BadAuthRequest {

        return ResponseEntity.ok(authService.login(request));

    }

}
