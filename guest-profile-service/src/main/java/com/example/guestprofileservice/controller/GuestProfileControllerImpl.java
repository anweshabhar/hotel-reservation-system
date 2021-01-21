package com.example.guestprofileservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.guestprofileservice.dto.CardDetailDTO;
import com.example.guestprofileservice.dto.GuestDTO;
import com.example.guestprofileservice.request.GuestRequest;
import com.example.guestprofileservice.response.ApiResponse;
import com.example.guestprofileservice.response.CardDetailResponse;
import com.example.guestprofileservice.service.GuestProfileService;

@RestController
public class GuestProfileControllerImpl implements GuestProfileController {

	private static final String STATUS_MSG = "success";

	@Autowired
	private GuestProfileService guestProfileService;

	@Override
	public ApiResponse<List<Long>> addGuest(String user, List<GuestRequest> request) {
		return new ApiResponse<>(HttpStatus.CREATED.value(), STATUS_MSG, guestProfileService.addGuest(user, request));
	}

	@Override
	public ApiResponse<String> addCard(String user, CardDetailDTO cardDetailDTO) {
		return new ApiResponse<>(HttpStatus.CREATED.value(), STATUS_MSG, guestProfileService.addCardDetail(user, cardDetailDTO));
	}

	@Override
	public ApiResponse<CardDetailResponse> getCard(String user) {
		return new ApiResponse<>(HttpStatus.OK.value(), STATUS_MSG, guestProfileService.getCard(user));
	}

	@Override
	public ApiResponse<String> deleteCard(String user) {
		guestProfileService.deleteCard(user);
		return new ApiResponse<>(HttpStatus.OK.value(), STATUS_MSG,"deleted");
	}

	@Override
	public ApiResponse<String> deleteGuestById(long guestId) {
		guestProfileService.deleteGuest(guestId);
		return new ApiResponse<>(HttpStatus.OK.value(), STATUS_MSG, "deleted");
	}

	@Override
	public ApiResponse<String> addStayHistory(String user) {
		guestProfileService.addStayHistory(user);
		return new ApiResponse<>(HttpStatus.CREATED.value(), STATUS_MSG, "Added");

	}

	@Override
	public ApiResponse<GuestDTO> getGuest(long guestId) {
		return new ApiResponse<>(HttpStatus.OK.value(), STATUS_MSG, guestProfileService.getGuest(guestId));
	}

}
