package com.bookstoreapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class WrongRequestParametersException extends RuntimeException {

    public WrongRequestParametersException() {
        super();
    }

    public WrongRequestParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongRequestParametersException(String message) {
        super(message);
    }

    public WrongRequestParametersException(Throwable cause) {
        super(cause);
    }

}