package com.bookstoreapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BookISBNAlreadyExistsException extends RuntimeException {

    public BookISBNAlreadyExistsException() {
        super();
    }

    public BookISBNAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookISBNAlreadyExistsException(String message) {
        super(message);
    }

    public BookISBNAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
