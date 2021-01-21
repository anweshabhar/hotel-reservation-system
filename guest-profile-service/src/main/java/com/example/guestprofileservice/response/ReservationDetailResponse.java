package com.example.guestprofileservice.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDetailResponse {

	private long reservationNo;
	private long roomId;
	private Date checkIn;
	private Date checkOut;
	private String hotelName;
	private String city;
	private double price;
	private String roomType;
	private int noOfguests;
	private String status;

}
