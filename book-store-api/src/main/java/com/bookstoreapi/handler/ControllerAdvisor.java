package com.bookstoreapi.handler;

import com.bookstoreapi.dao.ErrorResponse;
import com.bookstoreapi.enums.MessageTypeEnums;
import com.bookstoreapi.exception.BookISBNAlreadyExistsException;
import com.bookstoreapi.exception.BookNotFoundException;
import com.bookstoreapi.exception.InsufficientStockException;
import com.bookstoreapi.exception.MinimumPriceForOrderException;
import com.bookstoreapi.exception.UnAuthorizedException;
import com.bookstoreapi.exception.UserAlreadyExistsException;
import com.bookstoreapi.exception.UserNotFoundException;
import com.bookstoreapi.exception.WrongRequestParametersException;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> handleUnAuthorizedException(UnAuthorizedException e) {
        log.error("UnAuthorizedException: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().errorCode(MessageTypeEnums.UNAUTHORIZED_USER.name())
                .description(e.getMessage()).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        log.error("UserAlreadyExistsException: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().errorCode(MessageTypeEnums.USER_ALREADY_EXISTS.name())
                .description(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        log.error("UserNotFoundException: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().errorCode(MessageTypeEnums.USER_NOT_FOUND.name())
                .description(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookISBNAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleBookISBNAlreadyExistsException(BookISBNAlreadyExistsException e) {
        log.error("BookISBNAlreadyExistsException: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().errorCode(MessageTypeEnums.BOOK_ISBN_ALREADY_EXISTS.name())
                .description(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleBookNotFoundException(BookNotFoundException e) {
        log.error("BookNotFoundException: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().errorCode(MessageTypeEnums.BOOK_NOT_FOUND.name())
                .description(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientStockException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleInsufficientStockException(InsufficientStockException e) {
        log.error("InsufficientStockException: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().errorCode(MessageTypeEnums.INSUFFICIENT_STOCK.name())
                .description(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MinimumPriceForOrderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMinimumPriceForOrderException(MinimumPriceForOrderException e) {
        log.error("MinimumPriceForOrderException: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().errorCode(MessageTypeEnums.MINIMUM_PRICE_FOR_ORDER.name())
                .description(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongRequestParametersException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleWrongRequestParametersException(WrongRequestParametersException e) {
        log.error("MissingRequestException: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().errorCode(MessageTypeEnums.WRONG_REQUEST_PARAMETERS.name())
                .description(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({Exception.class, RuntimeException.class, AuthException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Exception: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().errorCode(MessageTypeEnums.SYSTEM_ERROR.name())
                .description(MessageTypeEnums.SYSTEM_ERROR.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}