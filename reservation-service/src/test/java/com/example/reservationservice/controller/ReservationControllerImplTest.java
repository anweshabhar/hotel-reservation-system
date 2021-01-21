package com.example.reservationservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.reservationservice.request.AvailabilityRequest;
import com.example.reservationservice.request.ReservationRequest;
import com.example.reservationservice.response.ReservationDetailResponse;
import com.example.reservationservice.response.ReservationResponse;
import com.example.reservationservice.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
@ExtendWith(SpringExtension.class)
class ReservationControllerImplTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private ReservationService reservationService;

	private AvailabilityRequest availabilityRequest = new AvailabilityRequest();
	private ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	void setUp() {
		availabilityRequest.setRoomType("KingBed");
		availabilityRequest.setCity("Kolkata");
		availabilityRequest.setHotelName("Novotel");
		availabilityRequest.setCheckInDt("16-01-2020");
		availabilityRequest.setCheckOutDt("17-01-2021");
	}

	@Test
	void testGetAvailableRoomCount() {
		List<Long> resp = new ArrayList<>();
		resp.add(1L);
		when(reservationService.getAvailableRoomID(Mockito.any(AvailabilityRequest.class))).thenReturn(resp);
		try {
			mockMvc.perform(get("/reservation/available").param("hotelName", "Taj")
					.param("city", "Mumbai").param("checkInDt", "16-01-2021")
					.param("checkOutDt", "17-01-2021").param("roomType", "KingBed")
					)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.availableRoomIds.[0]").value(1));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	void testBookReservation() {
		ReservationRequest request = new ReservationRequest();
		ReservationResponse resp = new ReservationResponse();
		resp.setReservationNo(11);
		resp.setStatus("success");
		when(reservationService.bookReservation(Mockito.any(ReservationRequest.class))).thenReturn(resp);
		ObjectMapper mapper = new ObjectMapper();
		try {
			mockMvc.perform(post("/reservation/reserve").contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(mapper.writeValueAsString(request)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.reservationNo").value(11))
			.andExpect(jsonPath("$.data.status").value("success"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testGetReservationDetails() {
		ReservationDetailResponse response = new ReservationDetailResponse();
		response.setRoomId(1);
		response.setReservationNo(1);
		List<ReservationDetailResponse> lst = new ArrayList<>();
		lst.add(response);
		when(reservationService.getReservationDetails("abc")).thenReturn(lst);
		try {
			mockMvc.perform(get("/reservation/{user}","abc")
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.data.[0].roomId").value(1))
			.andExpect(jsonPath("$.data.[0].reservationNo").value(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
