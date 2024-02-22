package com.ryanisaig.tests.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> notFound(NotFoundException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<Object> invalidRequest(HttpMessageNotReadableException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> badRequest(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);

		if(e.hasFieldErrors()) {
	       return new ResponseEntity<Object>(e.getFieldErrors().stream().map(err -> err.getDefaultMessage()), HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }
	
	
	@ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> errorFound(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
}
