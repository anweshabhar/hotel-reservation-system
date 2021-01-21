package com.example.hotelinformationservice.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.hotelinformationservice.exception.HotelNotFoundException;

@ControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(HotelNotFoundException.class)
	public ResponseEntity<HotelInformationSvcError> mapException(HotelNotFoundException ex){
		HotelInformationSvcError error = new HotelInformationSvcError();
		error.setErrorMessage(ex.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}
