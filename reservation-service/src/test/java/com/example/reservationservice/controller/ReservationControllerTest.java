package com.example.reservationservice.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.reservationservice.request.AvailabilityRequest;
import com.example.reservationservice.reservation.ReservationService;
import com.example.reservationservice.response.AvailabilityResponse;
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
		List<AvailabilityResponse> response = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		when(reservationService.checkAvailability(availabilityRequest)).thenReturn(response);
		try {
			MvcResult mvcResult = mockMvc.perform(post("/reservation/checkAvailability").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writeValueAsString(availabilityRequest)))
					.andReturn();
			assertNotNull(mvcResult.getResponse());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
