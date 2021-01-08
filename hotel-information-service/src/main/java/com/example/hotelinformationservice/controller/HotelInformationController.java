package com.example.hotelinformationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotelinformationservice.response.HotelVO;
import com.example.hotelinformationservice.service.HotelInfoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/hotel")
public class HotelInformationController {

	@Autowired
	private HotelInfoService hotelInfoService;

	@ApiOperation(value = "Get list of all hotels")
	@GetMapping(value = "/allHotels", produces = "application/json")
	public List<HotelVO> getHotels(){
		return hotelInfoService.getAllHotels();
	}

	@ApiOperation(value = "Get hotel information by name and city")
	@GetMapping(value = "/{hotelName}/{city}", produces = "application/json")
	public HotelVO getHotelById(@PathVariable String hotelName,@PathVariable String city) {
		return hotelInfoService.getHotelByNameAndCity(hotelName,city);
	}

}
