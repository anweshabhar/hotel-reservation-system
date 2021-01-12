package com.example.reservationservice.reservation;

import java.util.List;

import com.example.reservationservice.request.AvailabilityRequest;
import com.example.reservationservice.response.AvailabilityResponse;

public interface ReservationService {

	List<AvailabilityResponse> checkAvailability(AvailabilityRequest availabilityRequest);

}
