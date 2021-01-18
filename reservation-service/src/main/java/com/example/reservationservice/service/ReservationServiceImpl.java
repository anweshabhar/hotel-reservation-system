package com.example.reservationservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.reservationservice.api.feign.HotelRoomFeignClient;
import com.example.reservationservice.dto.GuestDTO;
import com.example.reservationservice.dto.RoomDTO;
import com.example.reservationservice.entity.Reservation;
import com.example.reservationservice.exception.ReservationServiceException;
import com.example.reservationservice.repository.ReservationRepository;
import com.example.reservationservice.request.AvailabilityRequest;
import com.example.reservationservice.request.ReservationRequest;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private HotelRoomFeignClient hotelRoomFeignClient;

	@Autowired
	private ReservationRepository repo;

	private static final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Override
	public List<Long> getAvailableRoomID(AvailabilityRequest request) throws ReservationServiceException {
		List<Long> roomIdList = new ArrayList<>();
		List<RoomDTO> roomDTOlist = hotelRoomFeignClient.getRoomDetails(request.getHotelName(), request.getCity(), request.getRoomType());

		if(!CollectionUtils.isEmpty(roomDTOlist)) {
			Date checkIn = getParsedDate(request.getCheckInDt());
			Date checkOut = getParsedDate(request.getCheckOutDt());
			if(checkIn.before(new Date()) || checkOut.before(new Date()))
				throw new ReservationServiceException("Check-in date should be greater than current date");
			List<Reservation> reservList = repo.findReservation(checkIn, checkOut);
			List<Long> reservedIdList = Collections.emptyList();
			if(!CollectionUtils.isEmpty(reservList)) {
				reservedIdList = reservList.stream().map(Reservation::getRoomId).collect(Collectors.toList());
			}

			if(!CollectionUtils.isEmpty(reservedIdList)) {
				for(RoomDTO dto : roomDTOlist) {
					if(!reservedIdList.contains(dto.getRoomId())) {
						roomIdList.add(dto.getRoomId());
					}

				}
			}else {
				roomDTOlist.forEach(r -> roomIdList.add(r.getRoomId()));
			}

		}
		return roomIdList;

	}

	private Date getParsedDate(String date) throws ReservationServiceException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date parsedDate=null;
		try {
			parsedDate = sdf.parse(date);
		} catch (ParseException e) {
			throw new ReservationServiceException("Unable to parse date.");
		}
		return parsedDate;
	}

	@Override
	public String bookReservation(ReservationRequest reservationRequest) throws ReservationServiceException {
		Reservation reservation = mapToReservationEntity(reservationRequest);
		List<GuestDTO> guestDTOlist = new ArrayList<>();
		reservationRequest.getGuestInfo().forEach(g -> {
			GuestDTO dto = new GuestDTO();
			dto.setName(g.getName());
			dto.setPhoneNo(g.getPhoneNo());
			GuestDTO.AddressDTO addressDTO = new GuestDTO.AddressDTO();
			addressDTO.setCity(g.getAddress().getCity());
			addressDTO.setCountry(g.getAddress().getCountry());
			addressDTO.setState(g.getAddress().getState());
			dto.setAddress(addressDTO);
			guestDTOlist.add(dto);
		});

		repo.save(reservation);
		hotelRoomFeignClient.addGuest(reservationRequest.getUserId(),guestDTOlist);
		return "success";
	}

	private Reservation mapToReservationEntity(ReservationRequest reservationRequest) throws ReservationServiceException {
		Reservation reservation = new Reservation();
		reservation.setUserId(reservationRequest.getUserId());
		reservation.setCity(reservationRequest.getCity());
		reservation.setHotelName(reservationRequest.getHotelName());
		reservation.setRoomId(reservationRequest.getRoomId());
		reservation.setRoomType(reservationRequest.getRoomType());
		reservation.setPrice(reservationRequest.getPrice());
		reservation.setCheckIn(getParsedDate(reservationRequest.getCheckInDate()));
		reservation.setCheckOut(getParsedDate(reservationRequest.getCheckOutDate()));
		return reservation;
	}

}
