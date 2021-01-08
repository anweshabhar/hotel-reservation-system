package com.example.guestprofileservice.mapper;

import org.mapstruct.Mapper;

import com.example.guestprofileservice.dto.GuestDTO;
import com.example.guestprofileservice.entity.GuestProfile;

@Mapper(componentModel = "spring")
public interface GuestProfileMapper {

	GuestProfile mapToGuestEntity(GuestDTO guestDTO);

}
