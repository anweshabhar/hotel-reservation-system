package com.example.hotelinformationservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.hotelinformationservice.response.HotelInfoResponseVO;
import com.example.hotelinformationservice.response.HotelVO;
import com.example.hotelinformationservice.response.RoomCountResponseVO;
import com.example.hotelinformationservice.response.RoomsVO;
import com.example.hotelinformationservice.service.HotelInfoServiceImpl;

@WebMvcTest
@ExtendWith(SpringExtension.class)
class HotelInformationControllerImplTest {

	@MockBean
	private HotelInfoServiceImpl service;

	@Autowired
	MockMvc mockMvc;
	private HotelVO vo = new HotelVO();

	@BeforeEach
	void setUp() {
		vo .setHotelDesc("testing");
		vo.setHotelId(1);
		vo.setHotelName("Test");
		Set<String> roomTypes = new HashSet<>();
		roomTypes.add("KingBed");
		vo.setRoomTypes(roomTypes);
	}

	@Test
	void testGetRoomDetails() throws Exception{
		List<RoomsVO> roomVOlist = new ArrayList<>();
		roomVOlist.add(new RoomsVO(1, "DoubleQueen", 3000, 4));
		when(service.getRoomDetails(Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(roomVOlist);
		mockMvc.perform(MockMvcRequestBuilders.get("/hotels/room/details")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.param("hotelName", "Novotel").param("city", "Kolkata")
				.param("roomType", "DoubleQueen")).andExpect(status().isOk())
		.andExpect(jsonPath("$.data.[0].roomId").value(1));
	}

	@Test
	void testGetHotelByID() throws Exception{
		HotelVO vo = new HotelVO();
		vo .setHotelDesc("testing");
		vo.setHotelId(1);
		vo.setHotelName("Test");
		Set<String> roomTypes = new HashSet<>();
		roomTypes.add("KingBed");
		vo.setRoomTypes(roomTypes);
		when(service.getHotelById(1)).thenReturn(vo);
		mockMvc.perform(MockMvcRequestBuilders.get("/hotels/{hotelId}",1)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
		.andExpect(jsonPath("$.data.hotelId").value(1))
		.andExpect(jsonPath("$.data.hotelName").value("Test"))
		.andExpect(jsonPath("$.data.roomTypes.[0]").value("KingBed"))
		.andExpect(jsonPath("$.data.hotelDesc").value("testing"));
	}

	@Test
	void testGetRoomCount() throws Exception{
		List<RoomCountResponseVO> lst = new ArrayList<>();
		RoomCountResponseVO vo = new RoomCountResponseVO();
		vo.setRoomCount(2);
		vo.setRoomType("KingBed");
		lst.add(vo);
		when(service.getRoomCount(Mockito.anyString(), Mockito.anyString())).thenReturn(lst);
		mockMvc.perform(MockMvcRequestBuilders.get("/hotels/room/count/{hotelName}/{city}","Taj","Mumbai")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
		.andExpect(jsonPath("$.data.[0].roomType").value("KingBed"))
		.andExpect(jsonPath("$.data.[0].roomCount").value(2));

	}


	@Test
	void testGetAllHotels() throws Exception{
		List<HotelInfoResponseVO> lst = new ArrayList<>();
		HotelInfoResponseVO resp = new HotelInfoResponseVO();
		resp.setCity("Kolkata");
		List<HotelVO> hotelList = new ArrayList<>();
		hotelList.add(vo);
		resp.setHotelList(hotelList);
		lst.add(resp);
		when(service.getAllHotels()).thenReturn(lst);
		mockMvc.perform(MockMvcRequestBuilders.get("/hotels")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
		.andExpect(jsonPath("$.data.[0].city").value("Kolkata"))
		.andExpect(jsonPath("$.data.[0].hotelList.[0].hotelId").value(1));

	}

	@Test
	void testGetHotelByNameAndCity() throws Exception{
		when(service.getHotelByNameAndCity(Mockito.anyString(), Mockito.anyString())).thenReturn(vo);
		mockMvc.perform(MockMvcRequestBuilders.get("/hotels/{hotelName}/{city}","Taj","Mumbai")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
		.andExpect(jsonPath("$.data.hotelId").value(1))
		.andExpect(jsonPath("$.data.hotelDesc").value(vo.getHotelDesc()));

	}

}
