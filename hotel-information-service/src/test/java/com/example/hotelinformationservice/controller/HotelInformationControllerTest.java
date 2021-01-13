package com.example.hotelinformationservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.hotelinformationservice.response.RoomsVO;
import com.example.hotelinformationservice.service.HotelInfoServiceImpl;

@WebMvcTest
@ExtendWith(SpringExtension.class)
class HotelInformationControllerTest {

	@MockBean
	private HotelInfoServiceImpl service;

	@Autowired
	MockMvc mockMvc;

	@Test
	void testGetRoomId() {
		List<RoomsVO> roomVOlist = new ArrayList<>();
		roomVOlist.add(new RoomsVO(1, "DoubleQueen", 3000, 4));
		when(service.getRoomDetails(Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(roomVOlist);
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/hotel/roomDetails")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.param("hotelName", "Novotel").param("city", "Kolkata")
					.param("roomType", "DoubleQueen")).andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].roomId").value(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
