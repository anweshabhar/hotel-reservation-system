package com.example.guestprofileservice.entity;

import java.util.Date;

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
public class CardDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String cardNo;
	private String expiryMonth;
	private String expiryYr;
	private String createdBy;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date createdOn;
}
