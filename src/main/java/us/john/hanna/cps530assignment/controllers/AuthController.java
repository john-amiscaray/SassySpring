package us.john.hanna.cps530assignment.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final ResponseEntity<Void> notImplemented;

    public AuthController(@Qualifier("notImplemented") ResponseEntity<Void> notImplemented){

        this.notImplemented = notImplemented;

    }

    @PostMapping("/auth/signUp")
    public ResponseEntity<?> signUp(){

        return notImplemented;

    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(){

        return notImplemented;

    }

}
