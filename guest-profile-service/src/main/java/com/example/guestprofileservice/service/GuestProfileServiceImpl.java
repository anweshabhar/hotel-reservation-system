package com.example.guestprofileservice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.guestprofileservice.dto.CardDetailDTO;
import com.example.guestprofileservice.dto.GuestDTO;
import com.example.guestprofileservice.entity.CardDetails;
import com.example.guestprofileservice.entity.GuestProfile;
import com.example.guestprofileservice.entity.StayHistory;
import com.example.guestprofileservice.exception.CardNotFoundException;
import com.example.guestprofileservice.exception.GuestNotFoundException;
import com.example.guestprofileservice.feign.GuestSvcFeignClient;
import com.example.guestprofileservice.mapper.GuestProfileMapper;
import com.example.guestprofileservice.repository.CardDetailsRepository;
import com.example.guestprofileservice.repository.GuestProfileRepository;
import com.example.guestprofileservice.repository.StayHistoryRepository;
import com.example.guestprofileservice.request.GuestRequest;
import com.example.guestprofileservice.response.CardDetailResponse;
import com.example.guestprofileservice.response.ReservationDetailResponse;

@Service
public class GuestProfileServiceImpl implements GuestProfileService{

	@Autowired
	private GuestProfileRepository repository;

	@Autowired
	private GuestProfileMapper mapper;

	@Autowired
	private CardDetailsRepository cardRepository;

	@Autowired
	private GuestSvcFeignClient feignClient;

	@Autowired
	private StayHistoryRepository stayHistoryRepository;

	@Override
	@Transactional
	public List<Long> addGuest(String user, List<GuestRequest> request) {
		List<GuestProfile> guestEntity = new ArrayList<>();
		request.forEach(g -> {
			GuestProfile guestProfile = mapper.mapToGuestEntity(g);
			guestProfile.setCreatedBy(user);
			guestProfile.setCreatedOn(new Date());
			guestEntity.add(guestProfile);
		});
		List<GuestProfile> lst = repository.saveAll(guestEntity);
		if(!CollectionUtils.isEmpty(lst)) {
			return lst.stream().map(GuestProfile::getGuestId).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	@Override
	public String addCardDetail(String user, CardDetailDTO dto) {
		CardDetails cardDetails = mapper.mapToCardEntity(dto);
		cardDetails.setCreatedBy(user);
		cardDetails.setCreatedOn(new Date());
		cardRepository.save(cardDetails);
		return "success";
	}

	@Override
	public CardDetailResponse getCard(String user) {
		Optional<CardDetails> opt = cardRepository.findByCreatedBy(user);
		if(opt.isPresent()) {
			CardDetailResponse response = new CardDetailResponse();
			response.setCardNo(opt.get().getCardNo());
			response.setExpiryMonth(opt.get().getExpiryMonth());
			response.setExpiryYr(opt.get().getExpiryYr());
			response.setName(opt.get().getName());
			return response;
		}
		return null;
	}

	@Override
	public List<GuestDTO> getGuestDetails(String user) {
		List<GuestProfile> lst = repository.findByCreatedBy(user);
		if(!CollectionUtils.isEmpty(lst)) {
			return lst.stream().map(l -> mapper.mapToGuestDTO(l)).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	@Override
	public void deleteCard(String user) {
		Optional<CardDetails> opt = cardRepository.findByCreatedBy(user);
		if(opt.isPresent()) {
			cardRepository.deleteById(opt.get().getId());
		}else
			throw new CardNotFoundException("Card not found");

	}

	@Override
	public void deleteGuest(long guestId) {
		Optional<GuestProfile> opt = repository.findById(guestId);
		if(opt.isPresent()) {
			repository.deleteById(opt.get().getGuestId());
		}else
			throw new GuestNotFoundException("Given guest id not present");
	}

	@Override
	public void addStayHistory(String user) {
		List<ReservationDetailResponse> lst = feignClient.getReservationDetails(user);
		if(!CollectionUtils.isEmpty(lst)) {
			List<ReservationDetailResponse> history = lst.stream().filter(l -> !StringUtils.equals("cancelled", l.getStatus()))
					.filter(l -> l.getCheckOut().before(new Date())).collect(Collectors.toList());
			if(!CollectionUtils.isEmpty(history)) {
				history.forEach(h -> {
					StayHistory stayHistory = new StayHistory();
					stayHistory.setCheckIn(h.getCheckIn());
					stayHistory.setCheckOut(h.getCheckOut());
					stayHistory.setCity(h.getCity());
					stayHistory.setHotelName(h.getHotelName());
					stayHistory.setUserId(user);
					stayHistoryRepository.save(stayHistory);
				});
			}
		}
	}

	@Override
	public GuestDTO getGuest(long guestId) {
		Optional<GuestProfile> opt = repository.findById(guestId);
		if(opt.isPresent()) {
			return mapper.mapToGuestDTO(opt.get());
		}else
			throw new GuestNotFoundException("Guest not found");

	}

}
