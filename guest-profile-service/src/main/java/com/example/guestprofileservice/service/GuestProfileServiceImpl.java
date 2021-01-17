package com.example.guestprofileservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	public void addGuest(String user, List<GuestDTO> guestDTO) {
		List<GuestProfile> guestEntity = new ArrayList<>();
		guestDTO.forEach(g -> {
			GuestProfile guestProfile = mapper.mapToGuestEntity(g);
			guestProfile.setCreatedBy(user);
			guestProfile.setCreatedOn(new Date());
			guestEntity.add(guestProfile);
		});
		repository.saveAll(guestEntity);

	}

}
