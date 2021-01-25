package com.example.reservationservice.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reservationservice.api.feign.ReservationSvcFeignClient;
import com.example.reservationservice.dto.RoomDTO;
import com.example.reservationservice.request.AvailabilityRequest;
import com.example.reservationservice.response.ApiResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class RoomDetailsSvc {

	private static final Logger log = LoggerFactory.getLogger(RoomDetailsSvc.class);

	@Autowired
	private ReservationSvcFeignClient feignClient;

	@HystrixCommand(fallbackMethod = "getFallbackRoomDetails")
	public List<RoomDTO> getRoomDetails(AvailabilityRequest request) {
		log.info("Starting getRoomDetails ");
		ApiResponse<List<RoomDTO>> apiResponse = feignClient.getRoomDetails(request.getHotelName(), request.getCity(), request.getRoomType());
		return apiResponse.getData();
	}

	public List<RoomDTO> getFallbackRoomDetails(AvailabilityRequest request){
		log.info("Starting getFallbackRoomDetails ");
		RoomDTO dto = new RoomDTO();
		dto.setMaxGuestAllowed(2);
		dto.setPrice(2000);
		dto.setRoomId(11);
		dto.setRoomType(request.getRoomType());
		return Arrays.asList(dto);
	}

}
