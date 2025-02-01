package com.example.quickcart.controllers;

import com.example.quickcart.dto.ExceptionResponseDto;
import com.example.quickcart.exceptions.UserAlreadyExistsException;
import com.example.quickcart.exceptions.UserNotFoundException;
import com.example.quickcart.exceptions.UserNotRegisteredException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .status(404)
                .error(e.getMessage())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(exceptionResponseDto, null, 404);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseDto> handleUserAlreadyExistsException(UserAlreadyExistsException e, HttpServletRequest request) {
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .status(409)
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .error(e.getMessage())
                .build();
        return new ResponseEntity<>(exceptionResponseDto, null, 409);
    }

    @ExceptionHandler(UserNotRegisteredException.class)
    public ResponseEntity<ExceptionResponseDto> handleUserNotRegisteredException(UserNotRegisteredException e, HttpServletRequest request) {
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .status(404)
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .error(e.getMessage())
                .build();
        return new ResponseEntity<>(exceptionResponseDto, null, 404);
    }
}
