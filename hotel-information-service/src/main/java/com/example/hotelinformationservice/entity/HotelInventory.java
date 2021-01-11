package com.example.hotelinformationservice.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Room_Inventory")
public class HotelInventory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String roomType;
	private int noOfvacancies;
	private long hotelId;
	private Date date;

}
