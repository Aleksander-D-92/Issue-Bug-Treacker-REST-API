package com.secure.secure_back_end.controllers.error_handling;

import com.secure.secure_back_end.configuration.exceptions.UserAlreadyExistsException;
import com.secure.secure_back_end.configuration.exceptions.UserNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler
{

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiException> handleUserNotFoundException(UserNotFoundException e)
    {
        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.CONFLICT, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiException> handleUserAlreadyExistsException(UserAlreadyExistsException e)
    {
        ApiException apiException = new ApiException("User with this name all ready exists", HttpStatus.CONFLICT, ZonedDateTime.now(), e.getLocalizedMessage());
        return new ResponseEntity<>(apiException, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiException> handleMethodArgumentNotValidException(MethodArgumentNotValidException e)
    {
        String collectedErrors = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
        ApiException apiException = new ApiException("invalid field arguments", HttpStatus.BAD_REQUEST, ZonedDateTime.now(), collectedErrors);
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiException> handleConstraintViolationException(ConstraintViolationException e)
    {
        ApiException apiException = new ApiException("Invalid @PathVariable or @RequestParam", HttpStatus.BAD_REQUEST, ZonedDateTime.now(), e.getLocalizedMessage());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiException> handleMissingServletRequestParameterException(MissingServletRequestParameterException e)
    {
        ApiException apiException = new ApiException("Missing or invalid @RequestParam", HttpStatus.BAD_REQUEST, ZonedDateTime.now(), e.getLocalizedMessage());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiException> handleDataIntegrityViolationException(DataIntegrityViolationException e)
    {
        ApiException apiException = new ApiException("Error during SQL operation, probably invalid SQL data was passed", HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now(), e.getLocalizedMessage());
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiException> handleEntityNotFoundException(EntityNotFoundException e)
    {
        ApiException apiException = new ApiException("This entity doesnt exist in DB", HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now(), e.getLocalizedMessage());
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    private ResponseEntity<ApiException> handleNullPointerException(NullPointerException e)
    {
        ApiException apiException = new ApiException("Null pointer...", HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now(), e.getLocalizedMessage());
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<ApiException> handleIllegalArgumentException(IllegalArgumentException e)
    {
        ApiException apiException = new ApiException("Invalid arguments where passed ", HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now(), e.getLocalizedMessage());
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
