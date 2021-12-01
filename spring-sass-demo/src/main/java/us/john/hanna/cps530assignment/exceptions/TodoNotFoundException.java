package us.john.hanna.cps530assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TodoNotFoundException extends NoSuchElementException {

    public TodoNotFoundException() {
        super("Todo not found");
    }

}
