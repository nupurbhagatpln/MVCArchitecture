package com.mvcModel.MvcModel.advices;
import com.mvcModel.MvcModel.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandlers {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException exception)
    {
        ApiError error= ApiError.builder().status(HttpStatus.NOT_FOUND).message(exception.getMessage()).build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleInternalServerError(Exception exception)
    {
        ApiError error= ApiError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(exception.getMessage()).build();

        return  new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleArugmentNotValidError(MethodArgumentNotValidException exception)
    {
        List<String> subError= exception.getBindingResult().getAllErrors().stream().map(error-> error.getDefaultMessage()).toList();
        ApiError error= ApiError.builder().status(HttpStatus.BAD_REQUEST).message("Input validation failed: ").allError(subError).build();

        return  new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
