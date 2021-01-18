package com.example.reservationservice.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationError {

	private int errorCode;
	private String errorMessage;
}
