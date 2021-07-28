package com.challenge.prodig.exception;

public class PersonNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 7784349461635079971L;
    public static final String PERSON_NOT_FOUND = "Person not found";

    public PersonNotFoundException() {
        super(PERSON_NOT_FOUND);
    }
}
