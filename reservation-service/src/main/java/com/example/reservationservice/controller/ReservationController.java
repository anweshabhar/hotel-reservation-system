package com.example.reservationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reservationservice.request.AvailabilityRequest;
import com.example.reservationservice.request.ReservationRequest;
import com.example.reservationservice.reservation.ReservationService;
import com.example.reservationservice.response.AvailabilityResponse;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@PostMapping(value = "/reserve", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String bookReservation(@RequestBody ReservationRequest req) {
		return null;
	}

	@PostMapping(value = "/checkAvailability", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AvailabilityResponse> checkAvailability(@RequestBody AvailabilityRequest availabilityRequest ) {
		return reservationService.checkAvailability(availabilityRequest);
	}

}
