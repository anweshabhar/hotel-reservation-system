package com.example.hotelinformationservice.mapper;

import org.mapstruct.Mapper;

import com.example.hotelinformationservice.entity.HotelInfo;
import com.example.hotelinformationservice.entity.Rooms;
import com.example.hotelinformationservice.response.HotelVO;
import com.example.hotelinformationservice.response.RoomsVO;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HotelInfoMapper {

	@Mapping(target = "roomTypes", ignore = true)
	HotelVO mapToHotelVO(HotelInfo hotelInfo);
	RoomsVO mapToRoomsVO(Rooms rooms);

}
