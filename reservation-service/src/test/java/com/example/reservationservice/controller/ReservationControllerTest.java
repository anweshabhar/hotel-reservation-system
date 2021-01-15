package com.example.reservationservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.reservationservice.request.AvailabilityRequest;
import com.example.reservationservice.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
@ExtendWith(SpringExtension.class)
class ReservationControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private ReservationService reservationService;

	@Test
	void testCheckAvailability() {
		AvailabilityRequest availabilityRequest = new AvailabilityRequest();
		availabilityRequest.setRoomType("KingBed");
		availabilityRequest.setCity("Kolkata");
		availabilityRequest.setHotelName("Novotel");
		availabilityRequest.setCheckInDt("16-01-2020");
		availabilityRequest.setCheckOutDt("17-01-2021");
		ObjectMapper mapper = new ObjectMapper();
		when(reservationService.getAvailableRoomCount(Mockito.any(AvailabilityRequest.class))).thenReturn(1);
		try {
			MvcResult mvcResult = mockMvc.perform(post("/reservation/availableRoomCount").contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(mapper.writeValueAsString(availabilityRequest)))
					.andExpect(status().isOk())
					.andReturn();
			assertEquals(1,Integer.parseInt(mvcResult.getResponse().getContentAsString()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
