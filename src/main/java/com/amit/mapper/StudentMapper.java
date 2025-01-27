package com.amit.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import com.amit.dto.AddressDto;
import com.amit.dto.CommunicationDto;
import com.amit.entity.AddressEntity;
import com.amit.entity.CommunicationEntity;
import com.amit.entity.StudentEntity;
import com.amit.response.StudentDetails;

@Component
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class StudentMapper {

	public List<StudentDetails> map(List<StudentEntity> studentEntityList,
			Map<Integer, List<AddressEntity>> addressMap, Map<Integer, List<CommunicationEntity>> communicationMap) {
		
		return studentEntityList.stream().map(student -> {
			
			var addressList = addressMap.getOrDefault(student.getStudentId(), List.of());
			var communicationList = communicationMap.getOrDefault(student.getStudentId(), List.of());
			
			return mapToStudentResponse(student, addressList, communicationList);
		}).toList();
	}
	
	private StudentDetails mapToStudentResponse(
			StudentEntity student, List<AddressEntity> addressEntityList, List<CommunicationEntity> communicationEntityList) {
		
		var communicationList = new ArrayList<CommunicationDto>();
		var addressList = new ArrayList<AddressDto>();

		for(var address : addressEntityList){
			var dto = new AddressDto();
			dto.setLine1(address.getLine1());
			dto.setLine2(address.getLine2());
			dto.setCity(address.getCity());
			dto.setState(address.getState());
			dto.setPincode(address.getPincode());
			dto.setCountry(address.getCountry());

			addressList.add(dto);
		}

		for(var communication : communicationEntityList){
			var dto = new CommunicationDto();
			dto.setCommChannel(communication.getCommChannel());
			dto.setCommValue(communication.getCommValue());

			communicationList.add(dto);
		}
		
		return StudentDetails.builder()
				.studentId(student.getStudentId())
				.studentName(student.getStudentName())
				.rollNumber(student.getRollNumber())
				.communicationList(communicationList)
				.addressList(addressList)
				.build();
	}

}
