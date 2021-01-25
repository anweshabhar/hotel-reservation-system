package com.example.guestprofileservice.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.guestprofileservice.dto.CardDetailDTO;
import com.example.guestprofileservice.dto.GuestDTO;
import com.example.guestprofileservice.dto.GuestDTO.AddressDTO;
import com.example.guestprofileservice.entity.Address;
import com.example.guestprofileservice.entity.CardDetails;
import com.example.guestprofileservice.entity.GuestProfile;
import com.example.guestprofileservice.exception.CardNotFoundException;
import com.example.guestprofileservice.exception.GuestNotFoundException;
import com.example.guestprofileservice.feign.GuestSvcFeignClient;
import com.example.guestprofileservice.mapper.GuestProfileMapper;
import com.example.guestprofileservice.repository.CardDetailsRepository;
import com.example.guestprofileservice.repository.GuestProfileRepository;
import com.example.guestprofileservice.repository.StayHistoryRepository;
import com.example.guestprofileservice.request.GuestRequest;
import com.example.guestprofileservice.request.GuestRequest.AddressRequest;
import com.example.guestprofileservice.response.CardDetailResponse;
import com.example.guestprofileservice.response.ReservationDetailResponse;

@ExtendWith(SpringExtension.class)
class GuestProfileServiceImplTest {

	@Mock
	private GuestProfileRepository repository;

	@Mock
	private GuestProfileMapper mapper;

	@Mock
	private CardDetailsRepository cardRepository;

	@Mock
	private GuestSvcFeignClient feignClient;

	@Mock
	private StayHistoryRepository stayHistoryRepository;

	@InjectMocks
	private GuestProfileServiceImpl service;
	List<GuestProfile> guestProfileList = new ArrayList<>();
	GuestProfile guestProfile = new GuestProfile();
	Address address = new Address();
	CardDetails cardDetails = new CardDetails();
	GuestDTO guestDTO = new GuestDTO();

	@BeforeEach
	void setUp() {
		guestProfile.setGuestId(1);
		guestProfile.setName("test");
		guestProfile.setPhoneNo(987654321);
		address.setCity("Delhi");
		address.setCountry("India");
		address.setId(1);
		address.setState("Delhi");
		guestProfile.setAddress(address);
		guestProfileList.add(guestProfile);

		cardDetails.setCardNo("12345657");
		cardDetails.setExpiryMonth("01");
		cardDetails.setExpiryYr("2025");
		cardDetails.setName("test");
		cardDetails.setId(1);

		guestDTO.setName("guest");
		guestDTO.setGuestId(1);
		guestDTO.setPhoneNo(123456);
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setCity("Mumbai");
		addressDTO.setCountry("India");
		addressDTO.setState("Mh");
		guestDTO.setAddress(addressDTO);
	}

	@Test
	void testAddGuest() {
		when(mapper.mapToGuestEntity(Mockito.any(GuestRequest.class))).thenReturn(guestProfile);
		when(repository.saveAll(Mockito.anyList())).thenReturn(guestProfileList);
		List<GuestRequest> request = new ArrayList<>();
		GuestRequest guest = new GuestRequest();
		guest.setName("test");
		guest.setPhoneNo(987654321);
		AddressRequest addressRequest = new AddressRequest();
		addressRequest.setCity("Delhi");
		addressRequest.setCountry("India");
		addressRequest.setState("Delhi");
		guest.setAddress(addressRequest);
		request.add(guest);
		List<Long> idList = service.addGuest("abc", request);
		assertEquals(1, idList.size());
	}

	@Test
	void testAddCardDetail() {

		CardDetailDTO dto = new CardDetailDTO();
		dto.setCardNo("12345657");
		dto.setExpiryMonth("01");
		dto.setExpiryYr("2025");
		dto.setName("test");
		when(mapper.mapToCardEntity(Mockito.any(CardDetailDTO.class))).thenReturn(cardDetails);
		String resp = service.addCardDetail("abc", dto);
		assertEquals("success", resp);
	}

	@Test
	void testGetCard() {
		when(cardRepository.findByCreatedBy("abc")).thenReturn(Optional.of(cardDetails));
		CardDetailResponse resp = service.getCard("abc");
		assertEquals("01", resp.getExpiryMonth());
	}

	@Test
	void testGetCard_Exception() {
		when(cardRepository.findByCreatedBy("abc")).thenReturn(Optional.empty());
		Assertions.assertThrows(CardNotFoundException.class, () -> {
			service.getCard("abc");
		});
	}

	@Test
	void testGetGuestDetails() {
		when(repository.findByCreatedBy("abc")).thenReturn(guestProfileList);
		when(mapper.mapToGuestDTO(Mockito.any(GuestProfile.class))).thenReturn(guestDTO);
		List<GuestDTO> resp = service.getGuestDetails("abc");
		assertEquals(1, resp.size());
	}

	@Test
	void testGetGuestDetails_Exception() {
		when(repository.findByCreatedBy("abc")).thenReturn(Collections.emptyList());
		Assertions.assertThrows(GuestNotFoundException.class, () -> {
			service.getGuestDetails("abc");
		});
	}

	@Test
	void testDeleteCard() {
		when(cardRepository.findByCreatedBy("abc")).thenReturn(Optional.of(cardDetails));
		service.deleteCard("abc");
		assertTrue(true);
	}

	@Test
	void testDeleteCard_Exception() {
		when(cardRepository.findByCreatedBy("abc")).thenReturn(Optional.empty());
		Assertions.assertThrows(CardNotFoundException.class, () -> {
			service.deleteCard("abc");
		});
	}

	@Test
	void testDeleteGuest() {
		when(repository.findById(1l)).thenReturn(Optional.of(guestProfile));
		service.deleteGuest(1);
		assertTrue(true);
	}

	@Test
	void testDeleteGuest_Exception() {
		when(repository.findById(1l)).thenReturn(Optional.empty());
		Assertions.assertThrows(GuestNotFoundException.class, () -> {
			service.deleteGuest(1);
		});
	}

	@Test
	void testAddStayHistory() throws ParseException {
		List<ReservationDetailResponse> lst = new ArrayList<>();
		ReservationDetailResponse resp = new ReservationDetailResponse();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		resp.setCheckIn(sdf.parse("16-01-2021"));
		resp.setCheckOut(sdf.parse("18-01-2021"));
		resp.setCity("Mumbai");
		resp.setHotelName("Taj");
		resp.setNoOfguests(4);
		resp.setPrice(3000);
		resp.setRoomId(4);
		resp.setReservationNo(2);
		resp.setRoomType("KingBed");
		resp.setStatus("inactive");
		lst.add(resp);
		when(feignClient.getReservationDetails("abc")).thenReturn(lst);
		service.addStayHistory("abc");
		assertTrue(true);
	}

	@Test
	void testGetGuest() {
		when(repository.findById(1l)).thenReturn(Optional.of(guestProfile));
		when(mapper.mapToGuestDTO(guestProfile)).thenReturn(guestDTO);
		GuestDTO dto = service.getGuest(1);
		assertEquals("guest", dto.getName());
	}

	@Test
	void testGetGuest_Exception() {
		when(repository.findById(1l)).thenReturn(Optional.empty());
		Assertions.assertThrows(GuestNotFoundException.class, () -> {
			service.getGuest(1);
		});
	}

}
