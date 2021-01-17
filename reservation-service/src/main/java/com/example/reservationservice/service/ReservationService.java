package com.example.reservationservice.service;

import java.util.List;

import com.example.reservationservice.request.AvailabilityRequest;
import com.example.reservationservice.request.ReservationRequest;

public interface ReservationService {

	List<Long> getAvailableRoomID(AvailabilityRequest availabilityRequest);

	String bookReservation(ReservationRequest reservationRequest);

}
