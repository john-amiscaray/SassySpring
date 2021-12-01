package us.john.hanna.cps530assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadAuthRequest extends Exception{

    public BadAuthRequest(String message) {
        super(message);
    }
}
