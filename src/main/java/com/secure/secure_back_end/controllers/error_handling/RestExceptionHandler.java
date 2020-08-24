package com.secure.secure_back_end.controllers.error_handling;

import com.secure.secure_back_end.configuration.exceptions.UserNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler
{

    @ExceptionHandler(UserNotFoundException.class)
    public ApiException handleUserNotFoundException(UserNotFoundException e)
    {
        return new ApiException(e.getMessage(), HttpStatus.CONFLICT, ZonedDateTime.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiException handleMethodArgumentNotValidException(MethodArgumentNotValidException e)
    {
        String collectedErrors = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
        return new ApiException("invalid field arguments", HttpStatus.BAD_REQUEST, ZonedDateTime.now(), collectedErrors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ApiException handleConstraintViolationException(ConstraintViolationException e)
    {
        return new ApiException("Invalid @PathVariable or @RequestParam", HttpStatus.BAD_REQUEST, ZonedDateTime.now(), e.getLocalizedMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiException handleMissingServletRequestParameterException(MissingServletRequestParameterException e)
    {
        return new ApiException("Missing or invalid @RequestParam", HttpStatus.BAD_REQUEST, ZonedDateTime.now(), e.getLocalizedMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ApiException handleDataIntegrityViolationException(DataIntegrityViolationException e)
    {
        return new ApiException("Error during SQL operation, probably invalid SQL data was passed", HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now(), e.getLocalizedMessage());
    }
}
