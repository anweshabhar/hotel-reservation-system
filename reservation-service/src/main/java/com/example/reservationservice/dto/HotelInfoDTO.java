
package com.example.reservationservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "hotelName",
    "roomTypes",
    "hotelDesc",
    "city"
})
@Getter
@Setter
public class HotelInfoDTO {

    @JsonProperty("hotelName")
    private String hotelName;
    @JsonProperty("roomTypes")
    private List<String> roomTypes = null;
    @JsonProperty("hotelDesc")
    private String hotelDesc;
    @JsonProperty("city")
    private String city;

}
