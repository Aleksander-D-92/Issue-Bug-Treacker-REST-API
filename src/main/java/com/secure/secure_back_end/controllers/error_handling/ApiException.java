package com.secure.secure_back_end.controllers.error_handling;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException
{
    private ZonedDateTime timestamp;
    private HttpStatus httpStatus;
    private String errorMassages;
    private String message;

    public ApiException()
    {
    }

    public ApiException(String message, HttpStatus httpStatus, ZonedDateTime timestamp)
    {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public ApiException(String message, HttpStatus httpStatus, ZonedDateTime timestamp, String errorMassages)
    {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
        this.errorMassages = errorMassages;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }



    public HttpStatus getHttpStatus()
    {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus)
    {
        this.httpStatus = httpStatus;
    }

    public ZonedDateTime getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getErrorMassages()
    {
        return errorMassages;
    }

    public void setErrorMassages(String errorMassages)
    {
        this.errorMassages = errorMassages;
    }
}
