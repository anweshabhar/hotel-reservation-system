package com.example.hotelinformationservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Rooms")
public class Rooms {

	@Id
	private long roomId;
	private String roomType;
	private String desc;

	@ManyToOne
	@JoinColumn(name = "hotelId",nullable = false)
	private HotelInfo hotel;

}
