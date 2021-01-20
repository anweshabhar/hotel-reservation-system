package com.example.hotelinformationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotelinformationservice.response.HotelInfoResponseVO;
import com.example.hotelinformationservice.response.HotelVO;
import com.example.hotelinformationservice.response.RoomCountResponseVO;
import com.example.hotelinformationservice.response.RoomsVO;
import com.example.hotelinformationservice.service.HotelInfoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/hotels")
public class HotelInformationController {

	@Autowired
	private HotelInfoService hotelInfoService;

	@ApiOperation(value = "Get list of all hotels by city")
	@GetMapping(value = "/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<HotelVO> getHotelsByCity(@PathVariable String city){
		return hotelInfoService.getHotelsByCity(city);
	}

	@ApiOperation(value = "Get hotel information by name and city")
	@GetMapping(value = "/{hotelName}/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
	public HotelVO getHotelByNameAndCity(@PathVariable String hotelName,@PathVariable String city) {
		return hotelInfoService.getHotelByNameAndCity(hotelName,city);
	}

	@ApiOperation(value = "Get list of all hotels")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<HotelInfoResponseVO> getAllHotels(){
		return hotelInfoService.getAllHotels();
	}

	@ApiOperation(value = "Get room count for each type for given hotel name and city")
	@GetMapping(value = "/room/count/{hotelName}/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RoomCountResponseVO> getRoomCount(@PathVariable String hotelName, @PathVariable String city) {
		return hotelInfoService.getRoomCount(hotelName, city);
	}

	@ApiOperation(value = "Get room details for given hotel, city and room type")
	@GetMapping(value = "/room/details", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RoomsVO> getRoomDetails(@RequestParam String hotelName, @RequestParam String city, @RequestParam String roomType){
		return hotelInfoService.getRoomDetails(roomType,hotelName,city);
	}

}
