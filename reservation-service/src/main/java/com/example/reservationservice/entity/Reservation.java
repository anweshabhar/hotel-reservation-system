package com.example.reservationservice.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long roomId;
	private String userId;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date checkIn;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date checkOut;
	private String hotelName;
	private String city;
	private Date createdOn;
	private double price;
	private String roomType;
	@ElementCollection
	private List<Long> guestId;
	private String status;

}
