package com.example.guestprofileservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.guestprofileservice.response.ReservationDetailResponse;

@FeignClient(name = "api-gateway")
public interface GuestSvcFeignClient {

	@GetMapping(value = "/reservation-service/reservation/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
	List<ReservationDetailResponse> getReservationDetails(@PathVariable String user);
}
