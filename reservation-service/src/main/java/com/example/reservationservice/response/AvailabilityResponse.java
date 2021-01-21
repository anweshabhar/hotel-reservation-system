package com.example.reservationservice.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvailabilityResponse {

	private List<Long> availableRoomIds;

}
