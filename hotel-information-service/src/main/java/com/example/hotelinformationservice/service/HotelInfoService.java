package com.example.hotelinformationservice.service;

import java.util.List;

import com.example.hotelinformationservice.response.HotelVO;

public interface HotelInfoService {

	List<HotelVO> getAllHotels();

	HotelVO getHotelById(long hotelId);

	HotelVO getHotelByNameAndCity(String hotelName, String city);
}
