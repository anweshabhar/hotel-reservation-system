package com.example.hotelinformationservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.hotelinformationservice.entity.HotelInfo;
import com.example.hotelinformationservice.entity.Rooms;
import com.example.hotelinformationservice.exception.HotelNotFoundException;
import com.example.hotelinformationservice.mapper.HotelInfoMapper;
import com.example.hotelinformationservice.repository.HotelRepository;
import com.example.hotelinformationservice.response.HotelInfoResponseVO;
import com.example.hotelinformationservice.response.HotelVO;
import com.example.hotelinformationservice.response.RoomCountResponseVO;
import com.example.hotelinformationservice.response.RoomsVO;

@ExtendWith(MockitoExtension.class)
class HotelInfoServiceImplTest {

	@Mock
	private HotelRepository hotelRepo;

	@Mock
	private HotelInfoMapper mapper;

	@InjectMocks
	private HotelInfoServiceImpl service;

	private HotelVO vo = new HotelVO();
	HotelInfo info = new HotelInfo();
	List<Rooms> rList = new ArrayList<>();
	Rooms rooms = new Rooms();

	@BeforeEach
	void setUp() {
		vo .setHotelDesc("testing");
		vo.setHotelId(1);
		vo.setHotelName("Test");
		Set<String> roomTypes = new HashSet<>();
		roomTypes.add("KingBed");
		vo.setRoomTypes(roomTypes);
		info.setCity("Kolkata");
		info.setHotelDesc("desc");
		info.setHotelId(1);
		info.setHotelName("Novotel");
		rooms.setMaxGuestAllowed(3);
		rooms.setPrice(3000);
		rooms.setRoomId(1);
		rooms.setRoomType("KingBed");
		rList.add(rooms);
		info.setRoomsList(rList);
	}

	@Test
	void testGetAllHotels() {
		List<HotelInfo> lst = new ArrayList<>();
		lst.add(info);
		when(hotelRepo.findAll()).thenReturn(lst);
		when(mapper.mapToHotelVO(Mockito.any(HotelInfo.class))).thenReturn(vo);
		List<HotelInfoResponseVO> resp = service.getAllHotels();
		assertEquals(1,resp.size());
	}

	@Test
	void testGetHotelById() {
		when(hotelRepo.findById(1l)).thenReturn(Optional.of(info));
		when(mapper.mapToHotelVO(Mockito.any(HotelInfo.class))).thenReturn(vo);
		HotelVO hotelVO = service.getHotelById(1);
		assertEquals("Test", hotelVO.getHotelName());
	}

	@Test
	void testGetHotelById_Exception() {
		when(hotelRepo.findById(1l)).thenReturn(Optional.empty());
		Assertions.assertThrows(HotelNotFoundException.class, () -> {
			service.getHotelById(1);
		});
	}

	@Test
	void testGetHotelByNameAndCity() {
		when(hotelRepo.findByHotelNameAndCity(Mockito.anyString(), Mockito.anyString())).thenReturn(info);
		when(mapper.mapToHotelVO(Mockito.any(HotelInfo.class))).thenReturn(vo);
		HotelVO hotelVO = service.getHotelByNameAndCity("Taj", "Mumbai");
		assertEquals(1, hotelVO.getHotelId());
	}

	@Test
	void testGetHotelByNameAndCity_Exception() {
		when(hotelRepo.findByHotelNameAndCity(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
		Assertions.assertThrows(HotelNotFoundException.class, () -> {
			service.getHotelByNameAndCity("Taj", "Mumbai");
		});
	}

	@Test
	void testGetRoomCount() {
		when(hotelRepo.findByHotelNameAndCity(Mockito.anyString(), Mockito.anyString())).thenReturn(info);
		List<RoomCountResponseVO> resp = service.getRoomCount("Taj", "Mumbai");
		assertEquals(1, resp.size());
	}

	@Test
	void testGetRoomCount_Exception() {
		when(hotelRepo.findByHotelNameAndCity(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
		Assertions.assertThrows(HotelNotFoundException.class, () -> {
			service.getRoomCount("Taj", "Mumbai");
		});
	}

	@Test
	void testGetRoomDetails() {
		when(hotelRepo.findByHotelNameAndCity(Mockito.anyString(), Mockito.anyString())).thenReturn(info);
		RoomsVO roomsVO = new RoomsVO();
		roomsVO.setMaxGuestAllowed(3);
		roomsVO.setPrice(3000);
		roomsVO.setRoomId(1);
		roomsVO.setRoomType("KingBed");
		when(mapper.mapToRoomsVO(rooms)).thenReturn(roomsVO);
		List<RoomsVO> list = service.getRoomDetails("KingBed", "Taj", "Mumbai");
		assertEquals(1, list.size());
	}

}
