package com.mvcModel.MvcModel.advices;
import com.mvcModel.MvcModel.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@RestControllerAdvice
public class GlobalExceptionHandlers {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException exception)
    {
        ApiError error= ApiError.builder().status(HttpStatus.NOT_FOUND).message(exception.getMessage()).build();

        return buildErrorResponseEntity(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception)
    {
        ApiError error= ApiError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(exception.getMessage()).build();

        return  buildErrorResponseEntity(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleArgumentNotValidError(MethodArgumentNotValidException exception)
    {
        List<String> subError= exception.getBindingResult().getAllErrors().stream().map(error-> error.getDefaultMessage()).toList();
        ApiError error= ApiError.builder().status(HttpStatus.BAD_REQUEST).message("Input validation failed: ").allError(subError).build();

        return  buildErrorResponseEntity(error);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError error) {
        return new ResponseEntity<>(new ApiResponse<>(error),error.getStatus());
    }
}
