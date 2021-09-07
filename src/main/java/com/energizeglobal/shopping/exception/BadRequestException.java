package com.energizeglobal.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseException {

    public BadRequestException(String message, String... args) {
        super(message);
        setArguments(args);
    }

    public BadRequestException() {
    }
}
