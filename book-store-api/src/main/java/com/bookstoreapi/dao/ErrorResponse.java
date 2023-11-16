package com.bookstoreapi.dao;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ErrorResponse {

    private String errorCode;
    private String description;

}