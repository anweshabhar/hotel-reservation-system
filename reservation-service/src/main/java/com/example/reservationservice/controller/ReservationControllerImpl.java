package com.example.reservationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.reservationservice.request.AvailabilityRequest;
import com.example.reservationservice.request.ReservationRequest;
import com.example.reservationservice.response.ApiResponse;
import com.example.reservationservice.response.AvailabilityResponse;
import com.example.reservationservice.response.ReservationDetailResponse;
import com.example.reservationservice.response.ReservationResponse;
import com.example.reservationservice.service.ReservationService;

@RestController
public class ReservationControllerImpl implements ReservationController{

	@Autowired
	private ReservationService reservationService;

	private static final String STATUS_MSG = "success";


	@Override
	public ApiResponse<ReservationResponse> bookReservation(ReservationRequest reservationRequest) {
		return new ApiResponse<>(HttpStatus.CREATED.value(),STATUS_MSG,reservationService.bookReservation(reservationRequest));
	}


	@Override
	public ApiResponse<AvailabilityResponse> getAvailableRoomCount( String hotelName, String city, String checkInDt,
			String checkOutDt, String roomType) {
		AvailabilityResponse availabilityResponse = new AvailabilityResponse();
		AvailabilityRequest availabilityRequest = new AvailabilityRequest();
		availabilityRequest.setCheckInDt(checkInDt);
		availabilityRequest.setCheckOutDt(checkOutDt);
		availabilityRequest.setCity(city);
		availabilityRequest.setHotelName(hotelName);
		availabilityRequest.setRoomType(roomType);
		List<Long> roomIdList = reservationService.getAvailableRoomID(availabilityRequest);
		availabilityResponse.setAvailableRoomIds(roomIdList);
		return new ApiResponse<>(HttpStatus.OK.value(), STATUS_MSG, availabilityResponse);
	}


	@Override
	public ApiResponse<List<ReservationDetailResponse>> getReservationDetails(String user) {
		return new ApiResponse<>(HttpStatus.OK.value(), STATUS_MSG, reservationService.getReservationDetails(user));
	}


	@Override
	public ApiResponse<String> cancelReservation(long reservationNo) {
		reservationService.cancelReservation(reservationNo);
		return new ApiResponse<>(HttpStatus.OK.value(), STATUS_MSG, "cancelled");
	}

}
