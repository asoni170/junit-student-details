package com.amit.response;

import java.util.List;

import com.amit.dto.AddressDto;
import com.amit.dto.CommunicationDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentDetails {

	private String studentName;

	private Integer studentId;

	private String rollNumber;

	private List<CommunicationDto> communicationList;

	private List<AddressDto> addressList;

}
