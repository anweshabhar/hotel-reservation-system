package com.example.hotelinformationservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomsVO {

	private long roomId;
	private String roomType;
	private double price;
	private int maxGuestAllowed;

}
