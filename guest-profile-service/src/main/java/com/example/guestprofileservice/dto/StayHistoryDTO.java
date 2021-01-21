package com.example.guestprofileservice.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StayHistoryDTO {

	private String hotelName;
	private String city;
	private Date checkIn;
	private Date checkOut;

}
