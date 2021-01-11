package com.example.reservationservice.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRequest {

	String userId;
	String roomId;
	String checkInDate;
	String checkOutDate;
}
