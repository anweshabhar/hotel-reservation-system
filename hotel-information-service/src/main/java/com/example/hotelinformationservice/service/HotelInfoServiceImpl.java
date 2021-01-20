package com.example.hotelinformationservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.hotelinformationservice.entity.HotelInfo;
import com.example.hotelinformationservice.entity.Rooms;
import com.example.hotelinformationservice.exception.HotelNotFoundException;
import com.example.hotelinformationservice.mapper.HotelInfoMapper;
import com.example.hotelinformationservice.repository.HotelRepository;
import com.example.hotelinformationservice.response.HotelInfoResponseVO;
import com.example.hotelinformationservice.response.HotelVO;
import com.example.hotelinformationservice.response.RoomCountResponseVO;
import com.example.hotelinformationservice.response.RoomsVO;

@Service
public class HotelInfoServiceImpl implements HotelInfoService {

	private static final String HOTEL_NOT_FOUND = "Hotel not found";

	@Autowired
	private HotelRepository hotelRepo;

	@Autowired
	private HotelInfoMapper mapper;

	@Override
	public List<HotelInfoResponseVO> getAllHotels() {
		List<HotelInfo> lst = hotelRepo.findAll();
		List<HotelInfoResponseVO> respList = new ArrayList<>();
		if(!CollectionUtils.isEmpty(lst)) {
			Map<String, List<HotelInfo>> cityWiseMap = lst.stream().collect(Collectors.groupingBy(HotelInfo::getCity));
			cityWiseMap.forEach((k,v) -> {
				HotelInfoResponseVO respVO = new HotelInfoResponseVO();
				respVO.setCity(k);
				List<HotelVO> voList = v.stream().map(l -> {
					HotelVO vo = mapper.mapToHotelVO(l);
					fetchRoomTypes(l, vo);
					return vo;
				}).collect(Collectors.toList());
				respVO.setHotelList(voList);
				respList.add(respVO);
			});
		}
		return respList;
	}

	@Override
	public HotelVO getHotelById(long hotelId) {
		Optional<HotelInfo> opt = hotelRepo.findById(hotelId);
		if(opt.isPresent()) {
			HotelVO vo = mapper.mapToHotelVO(opt.get());
			fetchRoomTypes(opt.get(), vo);
			return vo;
		}
		else
			throw new HotelNotFoundException(HOTEL_NOT_FOUND);
	}

	@Override
	public HotelVO getHotelByNameAndCity(String hotelName, String city) {
		HotelInfo hotelInfo = hotelRepo.findByHotelNameAndCity(hotelName, city);
		if(null!=hotelInfo) {
			HotelVO vo = mapper.mapToHotelVO(hotelInfo);
			fetchRoomTypes(hotelInfo, vo);
			return vo;
		}else
			throw new HotelNotFoundException(HOTEL_NOT_FOUND);

	}

	private void fetchRoomTypes(HotelInfo h, HotelVO vo) {
		Set<String> roomTypes = h.getRoomsList().stream().map(Rooms::getRoomType)
				.collect(Collectors.toSet());
		vo.setRoomTypes(roomTypes);
	}


	@Override
	public List<RoomCountResponseVO> getRoomCount(String hotelName, String city) {
		List<RoomCountResponseVO> lst = new ArrayList<>();
		HotelInfo hotelInfo = hotelRepo.findByHotelNameAndCity(hotelName, city);
		if(null != hotelInfo) {
			Map<String,Long> map = hotelInfo.getRoomsList().stream().collect(Collectors.groupingBy(Rooms::getRoomType,Collectors.counting()));
			map.forEach( (k,v) -> {
				RoomCountResponseVO vo = new RoomCountResponseVO();
				vo.setRoomType(k);
				vo.setRoomCount(v);
				lst.add(vo);
			});
		}else
			throw new HotelNotFoundException(HOTEL_NOT_FOUND);
		return lst;
	}

	@Override
	public List<RoomsVO> getRoomDetails(String roomType, String hotelName, String city) {
		List<RoomsVO> resp = new ArrayList<>();
		HotelInfo hotelInfo = hotelRepo.findByHotelNameAndCity(hotelName, city);
		if(null!=hotelInfo) {
			resp = hotelInfo.getRoomsList().stream().filter(r->StringUtils.equals(r.getRoomType(), roomType))
					.map(r -> mapper.mapToRoomsVO(r)).collect(Collectors.toList());
		}
		return resp;
	}


}
