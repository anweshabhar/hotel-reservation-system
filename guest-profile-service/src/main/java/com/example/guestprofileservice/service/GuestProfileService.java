package com.example.guestprofileservice.service;

import com.example.guestprofileservice.dto.GuestDTO;

public interface GuestProfileService {

	void addGuest(String user, GuestDTO guestDTO);

}
