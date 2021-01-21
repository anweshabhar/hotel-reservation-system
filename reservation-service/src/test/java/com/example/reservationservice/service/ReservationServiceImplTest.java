package com.example.reservationservice.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.reservationservice.api.feign.ReservationSvcFeignClient;
import com.example.reservationservice.dto.RoomDTO;
import com.example.reservationservice.entity.Reservation;
import com.example.reservationservice.exception.ReservationServiceException;
import com.example.reservationservice.mapper.ReservationMapper;
import com.example.reservationservice.repository.ReservationRepository;
import com.example.reservationservice.request.AvailabilityRequest;
import com.example.reservationservice.request.ReservationRequest;
import com.example.reservationservice.request.ReservationRequest.GuestInfo;
import com.example.reservationservice.response.ApiResponse;
import com.example.reservationservice.response.ReservationDetailResponse;
import com.example.reservationservice.response.ReservationResponse;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

	@Mock
	private ReservationSvcFeignClient feignClient;

	@InjectMocks
	private ReservationServiceImpl reservationServiceImpl;

	@Mock
	private ReservationRepository repo;

	@Mock
	private ReservationMapper mapper;
	Reservation reservation = new Reservation();
	List<Reservation> reservationList = new ArrayList<>();

	@BeforeEach
	void setUp() {
		reservation.setId(1);
		reservation.setRoomId(1);
		reservation.setCheckIn(new Date());
		reservation.setCheckOut(new Date());
		reservation.setGuestId(Arrays.asList(1L));
		reservationList.add(reservation);

	}

	@Test
	void testGetAvailableRoomID() {
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
		availabilityRequest.setCheckInDt("16-06-2021");
		availabilityRequest.setCheckOutDt("20-06-2021");
		ApiResponse<List<RoomDTO>> apiResponse = new ApiResponse<>();
		apiResponse.setData(roomDTO);
		when(feignClient.getRoomDetails("Novotel","Kolkata","DoubleQueen")).thenReturn(apiResponse);
		when(repo.findReservation(Mockito.any(), Mockito.any())).thenReturn(reservList);
		List<Long> response = reservationServiceImpl.getAvailableRoomID(availabilityRequest);
		assertEquals(2L, response.get(0));
	}

	@Test
	void testBookReservation() {
		ReservationRequest reservationRequest = new ReservationRequest();
		reservationRequest.setCheckIn("16-06-2021");
		reservationRequest.setCheckOut("20-06-2021");
		reservationRequest.setCity("Kolkata");
		reservationRequest.setHotelName("Novotel");
		reservationRequest.setRoomType("KingBed");
		reservationRequest.setUserId("abc");
		reservationRequest.setRoomId(2);
		List<GuestInfo> lst = new ArrayList<>();
		GuestInfo guestInfo = new GuestInfo();
		guestInfo.setName("xyz");
		lst.add(guestInfo);
		reservationRequest.setGuestInfo(lst);
		List<Reservation> reservations = new ArrayList<>();
		reservations.add(reservation);
		List<RoomDTO> roomDTOlist = new ArrayList<>();
		RoomDTO roomDTO = new RoomDTO();
		roomDTO.setRoomId(1);
		RoomDTO roomDTO2 = new RoomDTO();
		roomDTO2.setRoomId(2);
		roomDTOlist.add(roomDTO);
		roomDTOlist.add(roomDTO2);
		ApiResponse<List<Long>> apiResponse = new ApiResponse<>();
		apiResponse.setData(Arrays.asList(1L));
		ApiResponse<List<RoomDTO>> apiResponse2 = new ApiResponse<>();
		apiResponse2.setData(roomDTOlist);
		when(feignClient.addGuest(Mockito.anyString(), Mockito.any())).thenReturn(apiResponse);
		when(feignClient.getRoomDetails(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(apiResponse2);
		when(repo.save(Mockito.any())).thenReturn(reservation);
		when(repo.findReservation(Mockito.any(), Mockito.any())).thenReturn(reservations);
		when(mapper.mapToReservationEntity(Mockito.any(ReservationRequest.class))).thenReturn(reservation);
		ReservationResponse resp = reservationServiceImpl.bookReservation(reservationRequest);
		assertEquals(1L, resp.getReservationNo());
	}

	@Test
	void testBookReservation_Exception() {
		ReservationRequest reservationRequest = new ReservationRequest();
		reservationRequest.setCheckIn("16-06-2021");
		reservationRequest.setCheckOut("20-06-2021");
		reservationRequest.setCity("Kolkata");
		reservationRequest.setHotelName("Novotel");
		reservationRequest.setRoomType("KingBed");
		reservationRequest.setUserId("abc");
		reservationRequest.setRoomId(1);
		List<GuestInfo> lst = new ArrayList<>();
		GuestInfo guestInfo = new GuestInfo();
		guestInfo.setName("xyz");
		lst.add(guestInfo);
		reservationRequest.setGuestInfo(lst);
		List<Reservation> reservations = new ArrayList<>();
		reservations.add(reservation);
		List<RoomDTO> roomDTOlist = new ArrayList<>();
		RoomDTO roomDTO = new RoomDTO();
		roomDTO.setRoomId(1);
		roomDTOlist.add(roomDTO);
		ApiResponse<List<RoomDTO>> apiResponse = new ApiResponse<>();
		apiResponse.setData(roomDTOlist);
		when(feignClient.getRoomDetails(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(apiResponse);
		when(repo.findReservation(Mockito.any(), Mockito.any())).thenReturn(reservations);
		when(mapper.mapToReservationEntity(Mockito.any(ReservationRequest.class))).thenReturn(reservation);
		Assertions.assertThrows(ReservationServiceException.class, () -> {
			reservationServiceImpl.bookReservation(reservationRequest);
		});
	}

	@Test
	void testGetReservationDetails() {
		when(repo.findByUserId("abc")).thenReturn(reservationList);
		ReservationDetailResponse detailResponse = new ReservationDetailResponse();
		detailResponse.setNoOfguests(1);
		detailResponse.setCity("Mumbai");
		detailResponse.setHotelName("Taj");
		detailResponse.setReservationNo(1);
		when(mapper.mapToReservationResponse(reservation)).thenReturn(detailResponse);
		List<ReservationDetailResponse> response = reservationServiceImpl.getReservationDetails("abc");
		assertNotNull(response);
	}

	@Test
	void testGetReservationDetails_Exception() {
		when(repo.findByUserId("abc")).thenReturn(null);
		Assertions.assertThrows(ReservationServiceException.class, () -> {
			reservationServiceImpl.getReservationDetails("abc");
		});
	}


	@Test
	void testCancelReservation() {
		when(repo.findById(1l)).thenReturn(Optional.of(reservation));
		reservationServiceImpl.cancelReservation(1);
		assertTrue(true);

	}

	@Test
	void testCancelReservation_Exception() {
		when(repo.findById(1l)).thenReturn(Optional.empty());
		Assertions.assertThrows(ReservationServiceException.class, () -> {
			reservationServiceImpl.cancelReservation(1);
		});
	}

}
