package com.example.guestprofileservice.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.guestprofileservice.dto.GuestDTO;
import com.example.guestprofileservice.entity.GuestProfile;
import com.example.guestprofileservice.mapper.GuestProfileMapper;
import com.example.guestprofileservice.repository.GuestProfileRepository;

@Service
public class GuestProfileServiceImpl implements GuestProfileService{

	@Autowired
	private GuestProfileRepository repository;

	@Autowired
	private GuestProfileMapper mapper;

	@Override
	@Transactional
	public void addGuest(String user, GuestDTO guestDTO) {
		GuestProfile guestEntity = mapper.mapToGuestEntity(guestDTO);
		guestEntity.setCreatedBy(user);
		guestEntity.setCreatedOn(new Date());
		repository.save(guestEntity);

	}

}
