package com.example.guestprofileservice.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.guestprofileservice.exception.CardNotFoundException;
import com.example.guestprofileservice.exception.GuestNotFoundException;

@ControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(GuestNotFoundException.class)
	public ResponseEntity<GuestProfileSvcError> mapException(GuestNotFoundException ex){
		GuestProfileSvcError error = new GuestProfileSvcError();
		error.setErrorMessage(ex.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CardNotFoundException.class)
	public ResponseEntity<GuestProfileSvcError> mapException(CardNotFoundException ex){
		GuestProfileSvcError error = new GuestProfileSvcError();
		error.setErrorMessage(ex.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}
