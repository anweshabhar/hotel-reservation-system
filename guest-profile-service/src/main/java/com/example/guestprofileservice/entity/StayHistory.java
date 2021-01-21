package com.example.guestprofileservice.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StayHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String hotelName;
	private String city;
	private Date checkIn;
	private Date checkOut;
	private String userId;
}
