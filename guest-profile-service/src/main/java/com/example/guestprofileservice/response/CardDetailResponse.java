package com.example.guestprofileservice.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDetailResponse {

	private String name;
	private String cardNo;
	private String expiryMonth;
	private String expiryYr;
}
