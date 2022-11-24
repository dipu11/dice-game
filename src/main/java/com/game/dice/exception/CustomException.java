package com.game.dice.exception;

import com.game.dice.dto.ErrorDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

/**
 * Created by Dipu on 11/24/22.
 */
@RestControllerAdvice
public class CustomException extends ResponseEntityExceptionHandler {

    private final static Logger log = LogManager.getLogger(CustomException.class);

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleControllerException(
            Exception ex,
            WebRequest req
    ) {
        log.error(ex);
        ErrorDto errorDto = new ErrorDto(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);

    };
   // @ExceptionHandler(value = {Exception.class})
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {

        log.error(ex);
        String detailedMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorDto errorDto = new ErrorDto(
                HttpStatus.BAD_REQUEST.toString(),
                detailedMessage
        );
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }


}
