package com.example.hotelinformationservice.request;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelInfoRequest {

	private String cityName;
	private Date arrivalDate;
	private Date departureDate;
}
