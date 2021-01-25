package com.example.reservationservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.example.reservationservice.api.feign.ReservationSvcFeignClient;
import com.example.reservationservice.dto.GuestDTO;
import com.example.reservationservice.dto.RoomDTO;
import com.example.reservationservice.entity.Reservation;
import com.example.reservationservice.exception.ReservationServiceException;
import com.example.reservationservice.mapper.ReservationMapper;
import com.example.reservationservice.repository.ReservationRepository;
import com.example.reservationservice.request.AvailabilityRequest;
import com.example.reservationservice.request.ReservationRequest;
import com.example.reservationservice.response.ApiResponse;
import com.example.reservationservice.response.ReservationDetailResponse;
import com.example.reservationservice.response.ReservationResponse;

/**
 * @author Anwesha_Bhar
 *
 */
@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationSvcFeignClient feignClient;

	@Autowired
	private ReservationRepository repo;

	@Autowired
	private ReservationMapper mapper;

	@Autowired
	private RoomDetailsSvc roomDetailsSvc;

	private static final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

	/**
	 * Method to get list of available room id's
	 */
	@Override
	public List<Long> getAvailableRoomID(AvailabilityRequest request) {
		log.info("Inside getAvailableRoomID()");
		List<Long> roomIdList = new ArrayList<>();
		List<RoomDTO> roomDTOlist = roomDetailsSvc.getRoomDetails(request);
		if(!CollectionUtils.isEmpty(roomDTOlist)) {
			Date checkIn = getParsedDate(request.getCheckInDt());
			Date checkOut = getParsedDate(request.getCheckOutDt());
			validateDate(checkIn, checkOut);
			List<Reservation> reservList = repo.findReservation(checkIn, checkOut);
			if(!CollectionUtils.isEmpty(reservList)) {
				List<Long> reservedIdList = reservList.stream().map(Reservation::getRoomId).collect(Collectors.toList());
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





	private void validateDate(Date checkIn, Date checkOut) {
		if(checkIn.before(new Date()) || checkOut.before(new Date()))
			throw new ReservationServiceException("Check-in date should be greater than current date");
	}

	private Date getParsedDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date parsedDate=null;
		try {
			parsedDate = sdf.parse(date);
		} catch (ParseException e) {
			throw new ReservationServiceException("Unable to parse date.");
		}
		return parsedDate;
	}

	/**
	 * Method to book reservation returning reservation number in response
	 */
	@Override
	@Transactional
	public ReservationResponse bookReservation(ReservationRequest reservationRequest) {
		ReservationResponse response = new ReservationResponse();
		Reservation reservation = mapper.mapToReservationEntity(reservationRequest);
		if(checkRoomAvailable(reservationRequest.getRoomId(), reservationRequest.getCheckIn(),reservationRequest.getCheckIn(),reservationRequest.getCity(),
				reservationRequest.getHotelName(),reservationRequest.getRoomType()))
		{
			List<GuestDTO> guestDTOlist = new ArrayList<>();
			reservationRequest.getGuestInfo().forEach(g -> {
				GuestDTO dto = mapper.mapToGuestDTO(g);
				guestDTOlist.add(dto);
			});
			ApiResponse<List<Long>> apiResp = feignClient.addGuest(reservationRequest.getUserId(),guestDTOlist);
			reservation.setGuestId(apiResp.getData());
			reservation.setStatus("active");
			Reservation savedReservation = repo.save(reservation);
			response.setReservationNo(savedReservation.getId());
			response.setStatus("booked");
		}
		else {
			throw new ReservationServiceException("Room not available");
		}
		return response;
	}

	private boolean checkRoomAvailable(long roomId, String checkInDate, String checkOutDate, String city, String hotelName,
			String roomType) {
		AvailabilityRequest availabilityRequest = new AvailabilityRequest();
		availabilityRequest.setCheckInDt(checkInDate);
		availabilityRequest.setCheckOutDt(checkOutDate);
		availabilityRequest.setCity(city);
		availabilityRequest.setHotelName(hotelName);
		availabilityRequest.setRoomType(roomType);
		List<Long> lst = getAvailableRoomID(availabilityRequest);
		return (!CollectionUtils.isEmpty(lst) && lst.contains(roomId));
	}

	@Override
	public List<ReservationDetailResponse> getReservationDetails(String user) {
		List<Reservation> lst = repo.findByUserId(user);
		if(!CollectionUtils.isEmpty(lst)) {
			return lst.stream().map(l -> {
				ReservationDetailResponse r = mapper.mapToReservationResponse(l);
				r.setNoOfguests(l.getGuestId().size());
				return r;
			}).collect(Collectors.toList());
		}
		else {
			throw new ReservationServiceException("No reservation found for given reservation number");
		}
	}

	@Override
	public void cancelReservation(long reservationNo) {
		Optional<Reservation> opt = repo.findById(reservationNo);
		if(opt.isPresent()) {
			opt.get().setStatus("cancelled");
			repo.save(opt.get());
		}else {
			throw new ReservationServiceException("No reservation found for given reservation number");
		}
	}

}
