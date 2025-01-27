package com.amit.mock;

import com.amit.entity.AddressEntity;
import com.amit.entity.CommunicationEntity;
import com.amit.entity.StudentEntity;
import jakarta.persistence.Tuple;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.jpa.spi.NativeQueryTupleTransformer;
import org.mockito.Mockito;

import static com.amit.util.constant.DbConstants.*;
import static com.amit.util.constant.CommonConstants.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MockEntityConstants {

    public static final StudentEntity getMockStudent(){

        Object[] fieldValues = {MOCK_STUDENT_ID, MOCK_STUDENT_NAME, MOCK_STUDENT_ROLLNUMBER};
        String[] fieldNames = {STUDENT_ID, STUDENT_NAME, STUDENT_ROLL_NUMBER};

        NativeQueryTupleTransformer nativeQueryTupleTransformer = new NativeQueryTupleTransformer();
        Tuple tuple = nativeQueryTupleTransformer.transformTuple(fieldValues, fieldNames);

        return new StudentEntity(tuple);
    }

    public static final AddressEntity getMockAddress(){

        Object[] fieldVales = {MOCK_ADDRESS_ID, MOCK_ADDRESS_LINE_1, MOCK_ADDRESS_LINE_2, MOCK_ADDRESS_CITY, MOCK_ADDRESS_STATE, MOCK_ADDRESS_PINCODE, MOCK_ADDRESS_COUNTRY, MOCK_STUDENT_ID};
        String[] fieldNames = {ADDRESS_ID, ADDRESS_LINE_1, ADDRESS_LINE_2, ADDRESS_CITY, ADDRESS_STATE, ADDRESS_PINCODE, ADDRESS_COUNTRY, STUDENT_ID};

        NativeQueryTupleTransformer nativeQueryTupleTransformer = new NativeQueryTupleTransformer();
        Tuple tuple = nativeQueryTupleTransformer.transformTuple(fieldVales, fieldNames);

        return new AddressEntity(tuple);
    }

    public static final CommunicationEntity getMockCommunication(){

        Object[] fieldValues = {MOKC_COMMUNICATION_ID, MOCK_COMMUNICATION_CHANNEL_TYPE, MOCK_COMMUNICATION_CHANNEL_VALUE, MOCK_STUDENT_ID};
        String[] fieldNames = {COMMUNICATION_ID, COMMUNICATION_CHANNEL_TYPE, COMMUNICATION_CHANNEL_VALUE, STUDENT_ID};

        NativeQueryTupleTransformer nativeQueryTupleTransformer = new NativeQueryTupleTransformer();
        Tuple tuple = nativeQueryTupleTransformer.transformTuple(fieldValues, fieldNames);

        return new CommunicationEntity(tuple);
    }

}
