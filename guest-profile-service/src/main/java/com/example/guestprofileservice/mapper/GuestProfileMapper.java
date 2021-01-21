package com.example.guestprofileservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.guestprofileservice.dto.CardDetailDTO;
import com.example.guestprofileservice.dto.GuestDTO;
import com.example.guestprofileservice.dto.GuestDTO.AddressDTO;
import com.example.guestprofileservice.entity.Address;
import com.example.guestprofileservice.entity.CardDetails;
import com.example.guestprofileservice.entity.GuestProfile;
import com.example.guestprofileservice.request.GuestRequest;

@Mapper(componentModel = "spring")
public interface GuestProfileMapper {

	@Mapping(target = "createdOn", dateFormat = "dd-MM-yyyy")
	GuestProfile mapToGuestEntity(GuestDTO guestDTO);

	@Mapping(target = "guestProfile", ignore = true)
	@Mapping(target = "id", ignore = true)
	Address mapToAddressEntity(GuestDTO.AddressDTO addressDTO);

	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "createdOn", ignore = true)
	@Mapping(target = "id", ignore = true)
	CardDetails mapToCardEntity(CardDetailDTO cardDetailDTO);

	GuestDTO mapToGuestDTO(GuestProfile guestProfile);
	AddressDTO mapToAddressDTO(Address address);

	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "createdOn", ignore = true)
	@Mapping(target = "guestId", ignore = true)
	GuestProfile mapToGuestEntity(GuestRequest guestRequest);
	@Mapping(target = "guestProfile", ignore = true)
	@Mapping(target = "id", ignore = true)
	Address mapToAddressEntity(GuestRequest.AddressRequest addressRequest);

}
