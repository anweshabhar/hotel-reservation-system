package com.example.guestprofileservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestDTO {

	private long guestId;
	private String name;
	private long phoneNo;
	private AddressDTO address;
	private String createdBy;
	private String createdOn;

	@Getter
	@Setter
	public static class AddressDTO {
		private String city;
		private String state;
		private String country;
	}
}
