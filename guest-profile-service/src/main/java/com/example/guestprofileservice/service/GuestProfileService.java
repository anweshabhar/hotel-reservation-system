package com.example.guestprofileservice.service;

import java.util.List;

import com.example.guestprofileservice.dto.GuestDTO;

public interface GuestProfileService {

	void addGuest(String user, List<GuestDTO> guestDTOlist);

}
