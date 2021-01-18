package com.example.reservationservice.service;

import java.util.List;

import com.example.reservationservice.exception.ReservationServiceException;
import com.example.reservationservice.request.AvailabilityRequest;
import com.example.reservationservice.request.ReservationRequest;

public interface ReservationService {

	List<Long> getAvailableRoomID(AvailabilityRequest availabilityRequest) throws ReservationServiceException;

	String bookReservation(ReservationRequest reservationRequest) throws ReservationServiceException;

}
