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
import com.example.reservationservice.dto.RoomCountDTO;
import com.example.reservationservice.dto.RoomDTO;
import com.example.reservationservice.response.ApiResponse;

@FeignClient(name = "api-gateway")
public interface ReservationSvcFeignClient {

	@GetMapping(value = "/hotel-information-service/hotels/room/details", produces = MediaType.APPLICATION_JSON_VALUE)
	ApiResponse<List<RoomDTO>> getRoomDetails(@RequestParam String hotelName, @RequestParam String city, @RequestParam String roomType);

	@GetMapping(value = "/hotel-information-service/hotels/room/count/{hotelName}/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
	ApiResponse<List<RoomCountDTO>> getRoomCount(@PathVariable String hotelName, @PathVariable String city);

	@PostMapping(value = "/guest-profile-service/guestprofile/guests", consumes = MediaType.APPLICATION_JSON_VALUE)
	ApiResponse<List<Long>> addGuest(@RequestParam String user, @RequestBody List<GuestDTO> guestDTOlist);

}
