package com.example.guestprofileservice.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.example.guestprofileservice.dto.CardDetailDTO;
import com.example.guestprofileservice.dto.GuestDTO;
import com.example.guestprofileservice.dto.GuestDTO.AddressDTO;
import com.example.guestprofileservice.request.GuestRequest;
import com.example.guestprofileservice.response.CardDetailResponse;
import com.example.guestprofileservice.service.GuestProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
@ExtendWith(SpringExtension.class)
class GuestProfileControllerImplTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private GuestProfileService guestProfileService;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	void testAddGuest() throws Exception {
		List<GuestRequest> lst = new ArrayList<>();
		GuestRequest req = new GuestRequest();
		req.setName("abc");
		lst.add(req);
		List<Long> idList = new ArrayList<>();
		idList.add(1l);
		when(guestProfileService.addGuest("abc", lst)).thenReturn(idList);
		mockMvc.perform(post("/guestprofile/guests").param("user", "abc").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(lst)))
		.andExpect(status().isOk());
	}

	@Test
	void testCardDetail() throws Exception{
		CardDetailDTO dto = new CardDetailDTO();
		dto.setCardNo("123456789");
		when(guestProfileService.addCardDetail("abc",dto)).thenReturn("success");
		mockMvc.perform(post("/guestprofile/card").param("user", "abc").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(dto)))
		.andExpect(status().isOk());
	}

	@Test
	void testGetGuest() throws Exception {
		GuestDTO guestDTO = new GuestDTO();
		guestDTO.setName("guest");
		guestDTO.setGuestId(1);
		guestDTO.setPhoneNo(123456);
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setCity("Mumbai");
		addressDTO.setCountry("India");
		addressDTO.setState("Mh");
		guestDTO.setAddress(addressDTO);
		when(guestProfileService.getGuest(1)).thenReturn(guestDTO);
		mockMvc.perform(get("/guestprofile/{guestId}",1).contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.guestId").value(1));
	}

	@Test
	void testAddStayHistory() throws Exception {
		doNothing().when(guestProfileService).addStayHistory("abc");
		mockMvc.perform(post("/guestprofile/stayhistory/{user}","abc"))
		.andExpect(status().isOk());
	}

	@Test
	void testDeleteGuestById() throws Exception {
		doNothing().when(guestProfileService).deleteGuest(1);
		mockMvc.perform(delete("/guestprofile/{guestId}",1))
		.andExpect(status().isOk());
	}

	@Test
	void testDeleteCard() throws Exception {
		doNothing().when(guestProfileService).deleteCard("abc");
		mockMvc.perform(delete("/guestprofile/card/{user}","abc"))
		.andExpect(status().isOk());
	}

	@Test
	void testGetCard() throws Exception {
		CardDetailResponse response = new CardDetailResponse();
		response.setCardNo("12345678");
		response.setExpiryMonth("01");
		response.setExpiryYr("2030");
		response.setName("test user");
		when(guestProfileService.getCard("abc")).thenReturn(response);
		mockMvc.perform(get("/guestprofile/card/{user}","abc"))
		.andExpect(status().isOk()).andExpect(jsonPath("$.data.cardNo").value("12345678"));
	}

}
