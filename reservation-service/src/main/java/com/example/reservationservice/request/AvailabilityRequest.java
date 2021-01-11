package com.example.reservationservice.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvailabilityRequest {

	private String hotelId;
	private String checkInDt;
	private String checkOutDt;
	private int roomCount;
}
