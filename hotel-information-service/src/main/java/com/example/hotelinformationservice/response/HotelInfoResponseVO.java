package com.example.hotelinformationservice.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelInfoResponseVO {

	private String city;
	private List<HotelVO> hotelList;
}
