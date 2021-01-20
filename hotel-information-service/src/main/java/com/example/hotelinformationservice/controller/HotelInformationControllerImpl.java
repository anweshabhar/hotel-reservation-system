package com.example.hotelinformationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotelinformationservice.response.ApiResponse;
import com.example.hotelinformationservice.response.HotelInfoResponseVO;
import com.example.hotelinformationservice.response.HotelVO;
import com.example.hotelinformationservice.response.RoomCountResponseVO;
import com.example.hotelinformationservice.response.RoomsVO;
import com.example.hotelinformationservice.service.HotelInfoService;

@RestController
public class HotelInformationControllerImpl implements HotelController {

	private static final String STATUS_MSG = "success";

	@Autowired
	private HotelInfoService hotelInfoService;

	@Override
	public ApiResponse<HotelVO> getHotelByNameAndCity( String hotelName, String city) {
		return new ApiResponse<>(HttpStatus.OK.value(), STATUS_MSG, hotelInfoService.getHotelByNameAndCity(hotelName,city));
	}

	@Override
	public ApiResponse<List<HotelInfoResponseVO>> getAllHotels(){
		return new ApiResponse<>(HttpStatus.OK.value(), STATUS_MSG, hotelInfoService.getAllHotels());
	}

	@Override
	public ApiResponse<List<RoomCountResponseVO>> getRoomCount( String hotelName,  String city) {
		return new ApiResponse<>(HttpStatus.OK.value(), STATUS_MSG, hotelInfoService.getRoomCount(hotelName, city));
	}

	@Override
	public ApiResponse<List<RoomsVO>> getRoomDetails(String hotelName, String city, String roomType){
		return new ApiResponse<>(HttpStatus.OK.value(), STATUS_MSG, hotelInfoService.getRoomDetails(roomType,hotelName,city));
	}

	@Override
	public ApiResponse<HotelVO> getHotelByID(long hotelId) {
		return new ApiResponse<>(HttpStatus.OK.value(), STATUS_MSG, hotelInfoService.getHotelById(hotelId));
	}

}
