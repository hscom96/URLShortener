package com.soulmates.urlshortener.common.exception;

import com.soulmates.urlshortener.common.constants.ErrorEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

@Getter
public class CustomException extends RuntimeException{
    protected HttpStatus status;
    protected ErrorEnum errorEnum;

    public CustomException(ErrorEnum errorEnum) {
        super(errorEnum.toString());
        this.status = errorEnum.getErrorResponse().getHttpStatus();
        this.errorEnum = errorEnum;
    }
}
