package com.bookstoreapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MinimumPriceForOrderException extends RuntimeException {

    public MinimumPriceForOrderException() {
        super();
    }

    public MinimumPriceForOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public MinimumPriceForOrderException(String message) {
        super(message);
    }

    public MinimumPriceForOrderException(Throwable cause) {
        super(cause);
    }
}
