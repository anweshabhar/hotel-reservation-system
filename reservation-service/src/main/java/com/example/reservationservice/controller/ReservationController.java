package com.example.reservationservice.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.reservationservice.request.ReservationRequest;
import com.example.reservationservice.response.ApiResponse;
import com.example.reservationservice.response.AvailabilityResponse;
import com.example.reservationservice.response.ReservationDetailResponse;
import com.example.reservationservice.response.ReservationResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Reservation service operations")
@RequestMapping("/reservation")
public interface ReservationController {

	@ApiOperation(value = "Api to Book reservation")
	@PostMapping(value = "/reserve", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ApiResponse<ReservationResponse> bookReservation(@RequestBody ReservationRequest reservationRequest);

	@ApiOperation(value = "Api to get available room id's")
	@GetMapping(value = "/available", produces = MediaType.APPLICATION_JSON_VALUE)
	ApiResponse<AvailabilityResponse> getAvailableRoomCount(@RequestParam String hotelName,@RequestParam String city,@RequestParam String checkInDt,
			@RequestParam String checkOutDt,@RequestParam String roomType);

	@ApiOperation(value = "Api to get reservation details for given user")
	@GetMapping(value = "/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
	ApiResponse<List<ReservationDetailResponse>> getReservationDetails(@PathVariable String user);

	@ApiOperation(value = "Api to update reservation to cancel status")
	@PutMapping(value = "/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
	ApiResponse<String> cancelReservation(@PathVariable long reservationNo);

}
