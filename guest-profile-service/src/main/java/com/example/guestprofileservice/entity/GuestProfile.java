package com.example.guestprofileservice.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Guest")
@Getter
@Setter
public class GuestProfile {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long guestId;

	private String name;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	private long phoneNo;
	private String createdBy;
	private Date createdOn;


}
