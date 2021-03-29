package com.uploadserv.exceptions;

public class BusinessError {

    private String error;
    private String fieldName;
    private String type;

    public BusinessError() {
    }

    public BusinessError(String fieldName, String error, String type) {
        this.fieldName = fieldName;
        this.error = error;
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
