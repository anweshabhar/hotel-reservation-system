package com.example.guestprofileservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;
	private String city;
	private String state;
	private String country;
	@OneToOne(mappedBy = "address")
	private GuestProfile guestProfile;

}
