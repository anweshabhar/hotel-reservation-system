package com.example.hotelinformationservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.hotelinformationservice.entity.HotelInfo;
import com.example.hotelinformationservice.entity.Rooms;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class HotelRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private HotelRepository repo;
	HotelInfo hotelInfo = new HotelInfo();

	@BeforeEach
	void setUp() {
		hotelInfo.setCity("Mumbai");
		hotelInfo.setHotelDesc("test");
		hotelInfo.setHotelName("Test");
		List<Rooms> lst = new ArrayList<>();
		Rooms rooms = new Rooms();
		rooms.setHotel(hotelInfo);
		rooms.setMaxGuestAllowed(4);
		rooms.setPrice(3000);
		rooms.setRoomType("KingBed");
		lst.add(rooms);
		hotelInfo.setRoomsList(lst);
	}

	@Test
	void testFindByHotelNameAndCity() {
		HotelInfo savedEntity = entityManager.persist(hotelInfo);
		HotelInfo resp = repo.findByHotelNameAndCity("Test", "Mumbai");
		assertEquals(savedEntity.getHotelId(), resp.getHotelId());
	}


}
