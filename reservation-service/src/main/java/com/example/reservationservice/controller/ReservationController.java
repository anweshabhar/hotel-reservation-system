package com.example.reservationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reservationservice.request.AvailabilityRequest;
import com.example.reservationservice.request.ReservationRequest;
import com.example.reservationservice.reservation.ReservationService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@PostMapping(value = "/getReservation", consumes = "application/json", produces = "application/json")
	public String getReservation(@RequestBody ReservationRequest req) {
		return null;
	}

	public void checkAvailability(@RequestBody AvailabilityRequest availabilityRequest ) {
		reservationService.checkAvailability(availabilityRequest);
	}

}
