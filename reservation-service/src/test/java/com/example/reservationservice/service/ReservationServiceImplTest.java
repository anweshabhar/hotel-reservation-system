package com.example.reservationservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.reservationservice.api.feign.HotelRoomFeignClient;
import com.example.reservationservice.dto.RoomDTO;
import com.example.reservationservice.entity.Reservation;
import com.example.reservationservice.repository.ReservationRepository;
import com.example.reservationservice.request.AvailabilityRequest;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

	@Mock
	private HotelRoomFeignClient feignClient;

	@InjectMocks
	private ReservationServiceImpl reservationServiceImpl;

	@Mock
	private ReservationRepository repo;

	@Test
	void testCheckAvailability() {
		List<RoomDTO> roomDTO = new ArrayList<>();
		RoomDTO dto1 = new RoomDTO();
		dto1.setRoomId(2);
		dto1.setRoomType("DoubleQueen");
		RoomDTO dto2 = new RoomDTO();
		dto2.setRoomId(1);
		dto2.setRoomType("DoubleQueen");
		roomDTO.add(dto1);
		roomDTO.add(dto2);
		AvailabilityRequest availabilityRequest = new AvailabilityRequest();
		List<Reservation> reservList = new ArrayList<>();
		Reservation reservation = new Reservation();
		reservation.setRoomId(1);
		reservation.setCity("Kolkata");
		reservation.setHotelName("Novotel");
		reservList.add(reservation);
		availabilityRequest.setCity("Kolkata");
		availabilityRequest.setHotelName("Novotel");
		availabilityRequest.setRoomType("DoubleQueen");
		availabilityRequest.setCheckInDt("16-01-2021");
		availabilityRequest.setCheckOutDt("20-01-2021");
		when(feignClient.getRoomDetails("Novotel","Kolkata","DoubleQueen")).thenReturn(roomDTO);
		when(repo.findReservation(Mockito.any(), Mockito.any())).thenReturn(reservList);
		List<Long> response = reservationServiceImpl.getAvailableRoomID(availabilityRequest);
		assertEquals(2L, response.get(0));
	}

}
