package com.example.reservationservice.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvailabilityResponse {

	private String roomType;
	private int availableRooms;

}
