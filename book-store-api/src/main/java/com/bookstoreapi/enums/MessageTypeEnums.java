package com.bookstoreapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MessageTypeEnums {
    SUCCESS("The Operation Is Successful."),
    BOOK_NOT_FOUND("The Book You Are Looking For Could Not Be Found."),
    INSUFFICIENT_STOCK("The Book Is Not In Stock."),
    MINIMUM_PRICE_FOR_ORDER("Your Order Must Be Worth At Least 25 Cents."),
    BOOK_ISBN_ALREADY_EXISTS("There is a book as same ISBN."),
    SYSTEM_ERROR("A System Error Has Occurred."),
    UNAUTHORIZED_USER("The User Is Unauthorized."),
    USER_ALREADY_EXISTS("There is a user with the same information."),
    USER_NOT_FOUND("There is not a user."),
    WRONG_REQUEST_PARAMETERS("Missing request.");

    @Getter
    private String message;


}
