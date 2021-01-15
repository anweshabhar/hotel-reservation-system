package com.example.reservationservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.example.reservationservice.dto.RoomDTO;
import com.example.reservationservice.entity.Reservation;
import com.example.reservationservice.repository.ReservationRepository;
import com.example.reservationservice.request.AvailabilityRequest;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private HotelRoomFeignClient hotelRoomFeignClient;

	@Autowired
	private ReservationRepository repo;

	private static final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Override
	public int getAvailableRoomCount(AvailabilityRequest request) {
		int freeRoomsCount = 0;
		List<RoomDTO> roomDTOlist = hotelRoomFeignClient.getRoomDetails(request.getHotelName(), request.getCity(), request.getRoomType());

		if(!CollectionUtils.isEmpty(roomDTOlist)) {
			Date checkIn = getParsedDate(request.getCheckInDt());
			Date checkOut = getParsedDate(request.getCheckOutDt());
			List<Reservation> reservList = repo.findReservation(checkIn, checkOut);
			List<Long> reservedIdList = Collections.emptyList();
			if(!CollectionUtils.isEmpty(reservList)) {
				reservedIdList = reservList.stream().map(Reservation::getRoomId).collect(Collectors.toList());
			}

			if(!CollectionUtils.isEmpty(reservedIdList)) {
				for(RoomDTO dto : roomDTOlist) {
					if(!reservedIdList.contains(dto.getRoomId()))
						freeRoomsCount++;
				}
			}else {
				freeRoomsCount = roomDTOlist.size();
			}

		}
		return freeRoomsCount;

	}

	private Date getParsedDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date parsedDate=null;
		try {
			parsedDate = sdf.parse(date);
		} catch (ParseException e) {
			log.info("Unable to parse date");
		}
		return parsedDate;
	}

}
