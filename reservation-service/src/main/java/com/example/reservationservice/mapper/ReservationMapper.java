package com.example.reservationservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.reservationservice.dto.GuestDTO;
import com.example.reservationservice.entity.Reservation;
import com.example.reservationservice.request.ReservationRequest;
import com.example.reservationservice.request.ReservationRequest.AddressInfo;
import com.example.reservationservice.request.ReservationRequest.GuestInfo;
import com.example.reservationservice.response.ReservationDetailResponse;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

	@Mapping(target = "status", ignore = true)
	@Mapping(target = "createdOn", ignore = true)
	@Mapping(target = "guestId", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "checkIn", dateFormat = "dd-MM-yyyy")
	@Mapping(target = "checkOut", dateFormat = "dd-MM-yyyy")
	Reservation mapToReservationEntity(ReservationRequest reservationRequest);

	@Mapping(target = "guestId", ignore = true)
	GuestDTO mapToGuestDTO(GuestInfo guestInfo);
	GuestDTO.AddressDTO mapTpAddressDTO(AddressInfo addressInfo);

	@Mapping(target = "noOfguests", ignore = true)
	@Mapping(target = "reservationNo", source = "id")
	ReservationDetailResponse mapToReservationResponse(Reservation reservation);

}
