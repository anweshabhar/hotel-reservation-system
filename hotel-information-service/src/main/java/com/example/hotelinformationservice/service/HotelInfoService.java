package com.example.hotelinformationservice.service;

import java.util.List;
import java.util.Map;

import com.example.hotelinformationservice.response.HotelVO;

public interface HotelInfoService {

	List<HotelVO> getAllHotels();

	HotelVO getHotelById(long hotelId);

	HotelVO getHotelByNameAndCity(String hotelName, String city);

	List<HotelVO> getHotelsByCity(String city);

	Map<String, Long> getRoomCount(String hotelName, String city);
}
