package com.quyennv.datn.user_service.presenter.common;

import com.quyennv.datn.user_service.core.exceptions.EmailUsernameOrPhoneAlreadyUsedException;
import com.quyennv.datn.user_service.presenter.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handleInvalidArgumentsException(MethodArgumentNotValidException ex) {
        List<Object> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            Map<String, String> errMap = new HashMap<>();
            log.error("Error invalid field: "+ error.getField() + " Message: "+ error.getDefaultMessage());
            errMap.put("field", error.getField());
            errMap.put("message", error.getDefaultMessage());
            errors.add(errMap);
        });

        return new ResponseEntity<>(new ApiResponse(false, "Fields validation fails!",errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    ResponseEntity<ApiResponse> handleGlobalException(Exception ex) {
        log.error("handleGlobalException");
        log.error("Error: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    ResponseEntity<ApiResponse> handleAppException(RuntimeException ex) {
        log.error("Error: {}" , ex.getMessage(),ex);
        return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(),null),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InterruptedException.class, ExecutionException.class})
    ResponseEntity<ApiResponse> handleControllerException(Exception ex) {
        log.error("Error in controller: {}" , ex.getMessage(), ex);
        return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(),null),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {AuthenticationException.class})
    ResponseEntity<ApiResponse> handleAuthenticationException(AuthenticationException ex) {
        log.error("Error in AuthenticationException: {}" , ex.getMessage());
        return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(),null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EmailUsernameOrPhoneAlreadyUsedException.class})
    ResponseEntity<ApiResponse> userExist(EmailUsernameOrPhoneAlreadyUsedException ex) {
        log.error("Error in AuthenticationException: {}" , ex.getMessage());
        return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(),null), HttpStatus.BAD_REQUEST);
    }
}
