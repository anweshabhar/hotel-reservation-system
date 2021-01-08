package com.example.hotelinformationservice.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.hotelinformationservice.entity.HotelInfo;
import com.example.hotelinformationservice.mapper.HotelInfoMapper;
import com.example.hotelinformationservice.repository.HotelRepository;
import com.example.hotelinformationservice.response.HotelVO;

@Service
public class HotelInfoServiceImpl implements HotelInfoService {

	@Autowired
	private HotelRepository repository;

	@Autowired
	private HotelInfoMapper mapper;

	@Override
	public List<HotelVO> getAllHotels() {
		List<HotelInfo> lst = repository.findAll();
		if(!CollectionUtils.isEmpty(lst)) {
			return lst.stream().map(l -> mapper.mapToHotelVO(l)).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	@Override
	public HotelVO getHotelById(long hotelId) {
		Optional<HotelInfo> opt = repository.findById(hotelId);
		if(opt.isPresent()) {
			return mapper.mapToHotelVO(opt.get());
		}
		return null;
	}

	@Override
	public HotelVO getHotelByNameAndCity(String hotelName, String city) {
		HotelInfo hotelInfo = repository.findByHotelNameAndCity(hotelName, city);
		return mapper.mapToHotelVO(hotelInfo);
	}

}
