package com.example.reservationservice.service;

import com.example.reservationservice.request.AvailabilityRequest;

public interface ReservationService {

	int getAvailableRoomCount(AvailabilityRequest availabilityRequest);

}
