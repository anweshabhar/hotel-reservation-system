package com.example.guestprofileservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.guestprofileservice.dto.GuestDTO;
import com.example.guestprofileservice.service.GuestProfileService;

@RestController
@RequestMapping("/guestProfile")
public class GuestProfileController {

	@Autowired
	private GuestProfileService guestProfileService;

	@PostMapping(value = "/addGuest", consumes = "application/json")
	public void addGuest(@RequestParam String user, @RequestBody GuestDTO guestDTO) {
		guestProfileService.addGuest(user, guestDTO);
	}

}
