package com.example.hotelinformationservice.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelVO {

	private String hotelName;
	private List<RoomsVO> roomsList;
	private String hotelDesc;
	private String city;
}
