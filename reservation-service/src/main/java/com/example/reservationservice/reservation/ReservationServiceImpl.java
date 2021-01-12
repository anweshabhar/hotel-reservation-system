package com.example.reservationservice.reservation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.reservationservice.request.AvailabilityRequest;
import com.example.reservationservice.response.AvailabilityResponse;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Override
	public List<AvailabilityResponse> checkAvailability(AvailabilityRequest availabilityRequest) {


		//check reservation table for the given hotelId, checkin dt and checkoutDt

		//get no. of rooms for each room type

		//update no. of rooms in response
		return null;

	}

}
