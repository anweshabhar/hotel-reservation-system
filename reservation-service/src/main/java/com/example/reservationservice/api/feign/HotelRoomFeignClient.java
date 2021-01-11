package com.example.reservationservice.api.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.reservationservice.dto.HotelInfoDTO;

@FeignClient(name = "api-gateway")
public interface HotelRoomFeignClient {

	@GetMapping(value = "/hotel-information-service/hotel/getHotelsByCity/{city}")
	List<HotelInfoDTO> getHotelsByCity(@PathVariable String city);

}
