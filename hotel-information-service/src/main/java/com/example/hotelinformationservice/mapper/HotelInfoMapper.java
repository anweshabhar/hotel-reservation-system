package com.example.hotelinformationservice.mapper;

import org.mapstruct.Mapper;

import com.example.hotelinformationservice.entity.HotelInfo;
import com.example.hotelinformationservice.entity.Rooms;
import com.example.hotelinformationservice.response.HotelVO;
import com.example.hotelinformationservice.response.RoomsVO;

@Mapper(componentModel = "spring")
public interface HotelInfoMapper {

	HotelVO mapToHotelVO(HotelInfo hotelInfo);
	RoomsVO mapToRoomsVO(Rooms rooms);

}
