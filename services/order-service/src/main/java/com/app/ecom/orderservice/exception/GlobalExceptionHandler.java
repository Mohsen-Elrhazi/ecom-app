package com.app.ecom.orderservice.exception;

import com.app.ecom.orderservice.dto.common.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

 @RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        ApiError apiError = ApiError.builder()
                .success(false)
                .message("Validation failed")
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(apiError);
    }

     @ExceptionHandler(ResourceNotFoundException.class)
     public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException ex){
         ApiError apiError = ApiError.builder()
                 .success(false)
                 .message(ex.getMessage())
                 .status(HttpStatus.NOT_FOUND.value())
                 .build();

         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
     }

     @ExceptionHandler(InsufficientStockException.class)
     public ResponseEntity<ApiError> handleInsufficientStock(InsufficientStockException ex){
         ApiError apiError = ApiError.builder()
                 .success(false)
                 .message(ex.getMessage())
                 .status(HttpStatus.BAD_REQUEST.value())
                 .build();

         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
     }
}
