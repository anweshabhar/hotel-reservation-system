package com.example.guestprofileservice.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.guestprofileservice.dto.CardDetailDTO;
import com.example.guestprofileservice.dto.GuestDTO;
import com.example.guestprofileservice.request.GuestRequest;
import com.example.guestprofileservice.response.ApiResponse;
import com.example.guestprofileservice.response.CardDetailResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Guest profile service operations")
@RequestMapping("/guestprofile")
public interface GuestProfileController {

	@ApiOperation(value = "Api to add new guest list")
	@PostMapping(value = "/guests", consumes = MediaType.APPLICATION_JSON_VALUE)
	ApiResponse<List<Long>> addGuest(@RequestParam String user, @RequestBody List<GuestRequest> request) ;

	@ApiOperation(value = "Api to get guest by given guest id")
	@GetMapping(value = "/{guestId}", produces = MediaType.APPLICATION_JSON_VALUE)
	ApiResponse<GuestDTO> getGuest(@PathVariable long guestId);

	@ApiOperation(value = "Api to add new credit card")
	@PostMapping(value = "/card", consumes = MediaType.APPLICATION_JSON_VALUE)
	ApiResponse<String> addCard(@RequestParam String user, @RequestBody CardDetailDTO cardDetailDTO);

	@ApiOperation(value = "Api to get credit card for given userid")
	@GetMapping(value = "/card/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
	ApiResponse<CardDetailResponse> getCard(@PathVariable String user);

	@ApiOperation(value = "Api to delete card for given userid")
	@DeleteMapping(value = "/card/{user}")
	ApiResponse<String> deleteCard(@PathVariable String user);

	@ApiOperation(value = "Api to delete guest by given guest id")
	@DeleteMapping(value = "/{guestId}")
	ApiResponse<String> deleteGuestById(@PathVariable long guestId);

	@ApiOperation(value = "Api to get stay history for given user id")
	@PostMapping(value = "/stayhistory/{user}")
	ApiResponse<String> addStayHistory(@PathVariable String user);

}
