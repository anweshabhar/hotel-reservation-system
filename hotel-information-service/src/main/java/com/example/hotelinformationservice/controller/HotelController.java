package com.example.hotelinformationservice.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hotelinformationservice.response.ApiResponse;
import com.example.hotelinformationservice.response.HotelInfoResponseVO;
import com.example.hotelinformationservice.response.HotelVO;
import com.example.hotelinformationservice.response.RoomCountResponseVO;
import com.example.hotelinformationservice.response.RoomsVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Api operations related to hotel information service ")
@RequestMapping("/hotels")
public interface HotelController {

	@ApiOperation(value = "Api to get hotel by id")
	@GetMapping(value = "/{hotelId}", produces = MediaType.APPLICATION_JSON_VALUE)
	ApiResponse<HotelVO> getHotelByID(@PathVariable long hotelId);

	@ApiOperation(value = "Api to get hotel information by name and city")
	@GetMapping(value = "/{hotelName}/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
	ApiResponse<HotelVO> getHotelByNameAndCity(@PathVariable String hotelName,@PathVariable String city);

	@ApiOperation(value = "Api to get list of all hotels")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	ApiResponse<List<HotelInfoResponseVO>> getAllHotels();

	@ApiOperation(value = "Api to get room count of each type for given hotel name and city")
	@GetMapping(value = "/room/count/{hotelName}/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
	ApiResponse<List<RoomCountResponseVO>> getRoomCount(@PathVariable String hotelName, @PathVariable String city);

	@ApiOperation(value = "Get room details for given hotel, city and room type")
	@GetMapping(value = "/room/details", produces = MediaType.APPLICATION_JSON_VALUE)
	ApiResponse<List<RoomsVO>> getRoomDetails(@RequestParam String hotelName, @RequestParam String city, @RequestParam String roomType);

}
