package com.example.reservationservice.reservation;

import com.example.reservationservice.request.AvailabilityRequest;

public interface ReservationService {

	void checkAvailability(AvailabilityRequest availabilityRequest);
}
