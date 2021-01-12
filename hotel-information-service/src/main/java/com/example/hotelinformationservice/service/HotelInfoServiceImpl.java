package com.example.hotelinformationservice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.hotelinformationservice.entity.HotelInfo;
import com.example.hotelinformationservice.entity.Rooms;
import com.example.hotelinformationservice.mapper.HotelInfoMapper;
import com.example.hotelinformationservice.repository.HotelRepository;
import com.example.hotelinformationservice.response.HotelInfoResponseVO;
import com.example.hotelinformationservice.response.HotelVO;
import com.example.hotelinformationservice.response.RoomCountResponseVO;

@Service
public class HotelInfoServiceImpl implements HotelInfoService {

	@Autowired
	private HotelRepository hotelRepo;

	@Autowired
	private HotelInfoMapper mapper;

	@Override
	public List<HotelInfoResponseVO> getAllHotels() {
		List<HotelInfo> lst = hotelRepo.findAll();
		List<HotelInfoResponseVO> respList = new ArrayList<>();
		if(!CollectionUtils.isEmpty(lst)) {
			List<HotelVO> voList = lst.stream().map(l -> {
				HotelVO vo = mapper.mapToHotelVO(l);
				fetchRoomTypes(l, vo);
				return vo;
			}).collect(Collectors.toList());
			Map<String,List<HotelVO>> cityWiseMap = voList.stream().collect(Collectors.groupingBy(HotelVO::getCity));
			cityWiseMap.forEach((k,v) -> {
				HotelInfoResponseVO respVO = new HotelInfoResponseVO();
				respVO.setCity(k);
				respVO.setHotelList(v);
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
		return null;
	}

	@Override
	public HotelVO getHotelByNameAndCity(String hotelName, String city) {
		HotelInfo hotelInfo = hotelRepo.findByHotelNameAndCity(hotelName, city);
		HotelVO vo = mapper.mapToHotelVO(hotelInfo);
		fetchRoomTypes(hotelInfo, vo);
		return vo;
	}

	@Override
	public List<HotelVO> getHotelsByCity(String city) {
		List<HotelInfo> hotelInfoList = hotelRepo.findByCity(city);
		if(!CollectionUtils.isEmpty(hotelInfoList)) {
			return hotelInfoList.stream().map(h -> {
				HotelVO vo = mapper.mapToHotelVO(h);
				fetchRoomTypes(h, vo);
				return vo;
			}).collect(Collectors.toList());
		}
		return Collections.emptyList();
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
		}
		return lst;
	}

}
