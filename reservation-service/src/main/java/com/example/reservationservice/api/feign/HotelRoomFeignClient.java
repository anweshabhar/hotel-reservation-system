package com.example.reservationservice.api.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.reservationservice.dto.GuestDTO;
import com.example.reservationservice.dto.HotelInfoDTO;
import com.example.reservationservice.dto.RoomCountDTO;
import com.example.reservationservice.dto.RoomDTO;

@FeignClient(name = "api-gateway")
public interface HotelRoomFeignClient {

	@GetMapping(value = "/hotel-information-service/hotel/getHotelsByCity/{city}")
	List<HotelInfoDTO> getHotelsByCity(@PathVariable String city);

	@GetMapping(value = "/hotel-information-service/hotel/roomDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	List<RoomDTO> getRoomDetails(@RequestParam String hotelName, @RequestParam String city, @RequestParam String roomType);

	@GetMapping(value = "/hotel-information-service/hotel/roomCount/{hotelName}/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
	List<RoomCountDTO> getRoomCount(@PathVariable String hotelName, @PathVariable String city);

	@PostMapping(value = "/guest-profile-service/guestProfile/addGuest", consumes = "application/json")
	void addGuest(@RequestParam String user, @RequestBody List<GuestDTO> guestDTOlist);

}
