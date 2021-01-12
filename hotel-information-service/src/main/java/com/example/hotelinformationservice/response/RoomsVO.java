package com.example.hotelinformationservice.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomsVO {

	private long roomId;
	private String roomType;
	private double price;
	private int maxGuestAllowed;

}
