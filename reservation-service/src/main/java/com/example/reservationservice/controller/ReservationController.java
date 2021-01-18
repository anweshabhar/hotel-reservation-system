package com.example.reservationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reservationservice.exception.ReservationServiceException;
import com.example.reservationservice.request.AvailabilityRequest;
import com.example.reservationservice.request.ReservationRequest;
import com.example.reservationservice.service.ReservationService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@ApiOperation(value = "api to Book reservation")
	@PostMapping(value = "/bookReservation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String bookReservation(@RequestBody ReservationRequest reservationRequest) throws ReservationServiceException {
		return reservationService.bookReservation(reservationRequest);
	}

	@ApiOperation(value = "api to get available room id's")
	@PostMapping(value = "/availableRoomCount", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Long> getAvailableRoomCount(@RequestBody AvailabilityRequest availabilityRequest ) throws ReservationServiceException {
		return reservationService.getAvailableRoomID(availabilityRequest);
	}

}
