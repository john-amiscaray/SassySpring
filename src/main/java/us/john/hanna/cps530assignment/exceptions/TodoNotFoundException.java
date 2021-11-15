package us.john.hanna.cps530assignment.exceptions;

import java.util.NoSuchElementException;

public class TodoNotFoundException extends NoSuchElementException {

    public TodoNotFoundException() {
        super("Todo not found");
    }

}
