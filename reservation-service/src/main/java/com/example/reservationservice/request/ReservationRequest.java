package com.example.reservationservice.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRequest {

	private long roomId;
	private String userId;
	private String hotelName;
	private String city;
	private String checkInDate;
	private String checkOutDate;
	private String roomType;
	private double price;
	private List<GuestInfo> guestInfo;

	@Getter
	@Setter
	public static class GuestInfo {
		private String name;
		private long phoneNo;
		private AddressInfo address;
	}

	@Getter
	@Setter
	public static class AddressInfo {
		private String city;
		private String state;
		private String country;
	}
}
