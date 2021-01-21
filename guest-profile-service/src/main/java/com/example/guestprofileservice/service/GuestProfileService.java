package com.example.guestprofileservice.service;

import java.util.List;

import com.example.guestprofileservice.dto.CardDetailDTO;
import com.example.guestprofileservice.dto.GuestDTO;
import com.example.guestprofileservice.request.GuestRequest;
import com.example.guestprofileservice.response.CardDetailResponse;

public interface GuestProfileService {

	List<Long> addGuest(String user, List<GuestRequest> request);

	String addCardDetail(String user, CardDetailDTO dto);

	CardDetailResponse getCard(String user);

	List<GuestDTO> getGuestDetails(String user);

	void deleteCard(String user);

	void deleteGuest(long guestId);

	void addStayHistory(String user);

	GuestDTO getGuest(long guestId);

}
