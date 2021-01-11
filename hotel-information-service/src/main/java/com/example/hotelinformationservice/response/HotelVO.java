package com.example.hotelinformationservice.response;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelVO {

	private String hotelName;
	private Set<String> roomTypes;
	private String hotelDesc;
	private String city;
}
