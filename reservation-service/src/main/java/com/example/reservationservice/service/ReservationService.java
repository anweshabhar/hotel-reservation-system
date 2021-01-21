package com.example.reservationservice.service;

import java.util.List;

import com.example.reservationservice.request.AvailabilityRequest;
import com.example.reservationservice.request.ReservationRequest;
import com.example.reservationservice.response.ReservationDetailResponse;
import com.example.reservationservice.response.ReservationResponse;

public interface ReservationService {

	List<Long> getAvailableRoomID(AvailabilityRequest availabilityRequest);

	ReservationResponse bookReservation(ReservationRequest reservationRequest);

	List<ReservationDetailResponse> getReservationDetails(String user);

	void cancelReservation(long reservationNo);

}
