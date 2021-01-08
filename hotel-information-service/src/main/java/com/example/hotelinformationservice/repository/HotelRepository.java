package com.example.hotelinformationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotelinformationservice.entity.HotelInfo;

@Repository
public interface HotelRepository extends JpaRepository<HotelInfo, Long>{
	HotelInfo findByHotelNameAndCity(String hotelName, String city);
}
