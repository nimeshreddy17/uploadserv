package com.uploadserv.exceptions;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private BusinessError businessError;

    private HttpStatus status;

    public BusinessException(String errorMessage, String name, String type, HttpStatus status) {
        super(errorMessage);
        businessError = new BusinessError();
        businessError.setError(errorMessage);
        businessError.setFieldName(name);
        businessError.setType(type);
        this.status = status;
    }

    public BusinessError getBfsBusinessError() {
        return businessError;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
