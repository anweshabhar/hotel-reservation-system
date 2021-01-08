package com.example.hotelinformationservice.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "HotelInfo")
public class HotelInfo {

	@Id
	private long hotelId;
	private String hotelName;

	@OneToMany(mappedBy = "hotel" )
	private List<Rooms> roomsList;
	private String hotelDesc;
	private String city;

}
