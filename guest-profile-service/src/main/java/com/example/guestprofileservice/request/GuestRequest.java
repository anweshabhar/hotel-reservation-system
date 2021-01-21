package com.example.guestprofileservice.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestRequest {

	private String name;
	private long phoneNo;
	private AddressRequest address;

	@Getter
	@Setter
	public static class AddressRequest {
		private String city;
		private String state;
		private String country;
	}
}
