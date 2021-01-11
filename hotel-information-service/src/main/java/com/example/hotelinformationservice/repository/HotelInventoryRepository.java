package com.example.hotelinformationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotelinformationservice.entity.HotelInventory;

public interface HotelInventoryRepository extends JpaRepository<HotelInventory, Long>{

	HotelInventory findByHotelIdAndRoomType(long hotelId, String roomType);
}
