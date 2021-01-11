package com.example.hotelinformationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotelinformationservice.response.HotelVO;
import com.example.hotelinformationservice.service.HotelInfoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/hotel")
public class HotelInformationController {

	@Autowired
	private HotelInfoService hotelInfoService;

	@ApiOperation(value = "Get list of all hotels by city")
	@GetMapping(value = "/getHotelsByCity/{city}", produces = "application/json")
	public List<HotelVO> getHotelsByCity(@PathVariable String city){
		return hotelInfoService.getHotelsByCity(city);
	}

	@ApiOperation(value = "Get hotel information by name and city")
	@GetMapping(value = "/{hotelName}/{city}", produces = "application/json")
	public HotelVO getHotelByNameAndCity(@PathVariable String hotelName,@PathVariable String city) {
		return hotelInfoService.getHotelByNameAndCity(hotelName,city);
	}

	@ApiOperation(value = "Get list of all hotels")
	@GetMapping(value = "/getAllHotels", produces = "application/json")
	public List<HotelVO> getAllHotels(){
		return hotelInfoService.getAllHotels();
	}


	@ApiOperation(value = "Update hotel inventory")
	@PostMapping(value = "/updateInventory")
	public boolean updateInventory(@RequestParam long hotelId, @RequestParam String roomType) {
		return hotelInfoService.updateInventory(hotelId, roomType);
	}

}
