package com.secure.secure_back_end.controllers.error_handling;

import com.secure.secure_back_end.configuration.exceptions.UserNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler
{

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiException> handleUserNotFoundException(UserNotFoundException e)
    {
        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.PAYMENT_REQUIRED, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.PAYMENT_REQUIRED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiException> handleMethodArgumentNotValidException(MethodArgumentNotValidException e)
    {
        String collect = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
        ApiException apiException = new ApiException("invalid field arguments", HttpStatus.PAYMENT_REQUIRED, ZonedDateTime.now(), collect);
        return new ResponseEntity<>(apiException, HttpStatus.PAYMENT_REQUIRED);
    }
}
