package com.example.diplom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="no mark")
public class GetsException extends RuntimeException{


    public GetsException(String message) {
        super(message);
    }

}
