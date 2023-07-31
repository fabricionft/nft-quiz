package com.quiz.quiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class HandlerExcpetion extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<MessageExcepetionHandler> tratarErro(Exception exception){
        MessageExcepetionHandler message = new MessageExcepetionHandler(
            new Date(),
            HttpStatus.UNAUTHORIZED.value(),
            "Sem autorização",
            exception.getMessage()
        );
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }
}
