package com.amit.mapper;

import com.amit.entity.AddressEntity;
import com.amit.entity.CommunicationEntity;
import com.amit.entity.StudentEntity;
import com.amit.response.StudentDetails;
import jakarta.persistence.Tuple;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import static com.amit.mock.MockEntityConstants.*;
import static com.amit.util.constant.CommonConstants.*;
import static com.amit.util.constant.DbConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class StudentMapperTest {

    @InjectMocks
    private StudentMapper studentMapper;

    @Mock
    private Tuple mockTuple;

    @Test
    public void testMap(){

        var mockStudentEntityList = List.of(getMockStudent());

        var mockAddressEntityMap = new HashMap<Integer, List<AddressEntity>>();
        var mockCommunicationEntityMap = new HashMap<Integer, List<CommunicationEntity>>();
        mockAddressEntityMap.put(MOCK_STUDENT_ID, List.of(getMockAddress()));
        mockCommunicationEntityMap.put(MOCK_STUDENT_ID, List.of(getMockCommunication()));

        var result = studentMapper.map(mockStudentEntityList, mockAddressEntityMap, mockCommunicationEntityMap);

        assertNotNull(result);
        assertEquals(1, result.size());

        assertEquals(MOCK_STUDENT_ID, result.get(0).getStudentId());
        assertEquals(MOCK_STUDENT_NAME, result.get(0).getStudentName());
        assertEquals(MOCK_STUDENT_ROLLNUMBER, result.get(0).getRollNumber());

        assertEquals(MOCK_ADDRESS_LINE_1, result.get(0).getAddressList().get(0).getLine1());
        assertEquals(MOCK_ADDRESS_LINE_2, result.get(0).getAddressList().get(0).getLine2());
        assertEquals(MOCK_ADDRESS_CITY, result.get(0).getAddressList().get(0).getCity());
        assertEquals(MOCK_ADDRESS_STATE, result.get(0).getAddressList().get(0).getState());
        assertEquals(MOCK_ADDRESS_PINCODE, result.get(0).getAddressList().get(0).getPincode());
        assertEquals(MOCK_ADDRESS_COUNTRY, result.get(0).getAddressList().get(0).getCountry());

        assertEquals(MOCK_COMMUNICATION_CHANNEL_TYPE, result.get(0).getCommunicationList().get(0).getCommChannel());
        assertEquals(MOCK_COMMUNICATION_CHANNEL_VALUE, result.get(0).getCommunicationList().get(0).getCommValue());
    }

    @Test
    public void testMapToStudentResponse() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        var mapToStudentResponseMethod = StudentMapper.class.getDeclaredMethod(
                "mapToStudentResponse", StudentEntity.class, List.class, List.class);
        mapToStudentResponseMethod.setAccessible(Boolean.TRUE);

        Mockito.lenient().when(mockTuple.get(STUDENT_NAME)).thenReturn(MOCK_STUDENT_NAME);
        Mockito.lenient().when(mockTuple.get(STUDENT_ROLL_NUMBER)).thenReturn(MOCK_STUDENT_ROLLNUMBER);
        Mockito.lenient().when(mockTuple.get(STUDENT_ID)).thenReturn(MOCK_STUDENT_ID);

        var result = (StudentDetails)mapToStudentResponseMethod.invoke(studentMapper,
                getMockStudent(), List.of(getMockAddress()), List.of(getMockCommunication()));

        assertNotNull(result);
        assertEquals(MOCK_STUDENT_ID, result.getStudentId());
        assertEquals(MOCK_STUDENT_NAME, result.getStudentName());
        assertEquals(MOCK_STUDENT_ROLLNUMBER, result.getRollNumber());

        assertEquals(MOCK_ADDRESS_LINE_1, result.getAddressList().get(0).getLine1());
        assertEquals(MOCK_ADDRESS_LINE_2, result.getAddressList().get(0).getLine2());
        assertEquals(MOCK_ADDRESS_CITY, result.getAddressList().get(0).getCity());
        assertEquals(MOCK_ADDRESS_STATE, result.getAddressList().get(0).getState());
        assertEquals(MOCK_ADDRESS_PINCODE, result.getAddressList().get(0).getPincode());
        assertEquals(MOCK_ADDRESS_COUNTRY, result.getAddressList().get(0).getCountry());

        assertEquals(MOCK_COMMUNICATION_CHANNEL_TYPE, result.getCommunicationList().get(0).getCommChannel());
        assertEquals(MOCK_COMMUNICATION_CHANNEL_VALUE, result.getCommunicationList().get(0).getCommValue());
    }
}
