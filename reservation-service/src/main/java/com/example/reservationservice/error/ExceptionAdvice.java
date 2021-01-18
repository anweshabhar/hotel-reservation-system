package com.example.reservationservice.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.reservationservice.exception.ReservationServiceException;

@ControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(ReservationServiceException.class)
	public ResponseEntity<ReservationError> mapException(ReservationServiceException ex){
		ReservationError error = new ReservationError();
		error.setErrorMessage(ex.getMessage());
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
