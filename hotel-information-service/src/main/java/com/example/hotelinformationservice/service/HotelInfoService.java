package com.example.hotelinformationservice.service;

import java.util.List;

import com.example.hotelinformationservice.response.HotelInfoResponseVO;
import com.example.hotelinformationservice.response.HotelVO;
import com.example.hotelinformationservice.response.RoomCountResponseVO;

public interface HotelInfoService {

	List<HotelInfoResponseVO> getAllHotels();

	HotelVO getHotelById(long hotelId);

	HotelVO getHotelByNameAndCity(String hotelName, String city);

	List<HotelVO> getHotelsByCity(String city);

	List<RoomCountResponseVO> getRoomCount(String hotelName, String city);
}
