package com.amit.dao;

import com.amit.dao.impl.StudentDaoImpl;
import com.amit.util.SqlUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.amit.util.constant.DbConstants.*;
import static com.amit.util.constant.SqlQueryConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class StudentDaoImplTest {

    @InjectMocks
    private StudentDaoImpl studentDao;

    @Mock
    private SqlUtils mockSqlUtils;

    @Mock
    private EntityManager mockEntityManager;

    @Mock
    private Query mockQuery;

    @Mock
    private Tuple mockTuple;

    @Test
    public void testGetSql() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        Mockito.lenient().when(mockSqlUtils.getSql(MOCK_QUERY_KEY)).thenReturn(MOCK_QUERY_STRING);

        Method methodGetSql = StudentDaoImpl.class.getDeclaredMethod("getSql", String.class);
        methodGetSql.setAccessible(Boolean.TRUE);

        var result = (String) methodGetSql.invoke(studentDao, MOCK_QUERY_KEY);

        assertEquals(MOCK_QUERY_STRING, result);
    }

    @Test
    public void testFetchStudentByRollNumber() throws NoSuchMethodException{

        var mockRollNumbers = List.of("ROLL001");

        Mockito.lenient().when(mockSqlUtils.getSql(SELECT_STUDENT_BY_ROLL_NUMBERS_IN)).thenReturn(MOCK_QUERY_STRING);

        Mockito.lenient().when(mockEntityManager.createNativeQuery(anyString(), eq(Tuple.class))).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.setParameter(FIELD_ROLL_NUMBER_LIST, mockRollNumbers)).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.getResultList()).thenReturn(List.of(mockTuple));

        Mockito.lenient().when(mockTuple.get(STUDENT_ID, Integer.class)).thenReturn(1);
        Mockito.lenient().when(mockTuple.get(STUDENT_ROLL_NUMBER, String.class)).thenReturn("ROLL001");
        Mockito.lenient().when(mockTuple.get(STUDENT_NAME, String.class)).thenReturn("Test Name");

        var result = studentDao.fetchStudentByRollNumber(mockRollNumbers);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getStudentId());
        assertEquals("ROLL001", result.get(0).getRollNumber());
        assertEquals("Test Name", result.get(0).getStudentName());

        verify(mockEntityManager, times(1)).createNativeQuery(anyString(), eq(Tuple.class));
        verify(mockQuery, times(1)).setParameter(FIELD_ROLL_NUMBER_LIST, mockRollNumbers);
        verify(mockQuery, times(1)).getResultList();
    }

    @Test
    public void testfetchStudentByRollNumber_EmpltyList(){

        var mockRollNumbers = List.of("ROLL001");

        Mockito.lenient().when(mockSqlUtils.getSql(SELECT_STUDENT_BY_ROLL_NUMBERS_IN)).thenReturn(MOCK_QUERY_STRING);

        Mockito.lenient().when(mockEntityManager.createNativeQuery(anyString(), eq(Tuple.class))).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.setParameter(FIELD_ROLL_NUMBER_LIST, mockRollNumbers)).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.getResultList()).thenReturn(Collections.emptyList());

        var result = studentDao.fetchStudentByRollNumber(mockRollNumbers);

        assertNotNull(result);
        assertEquals(0, result.size());

        verify(mockEntityManager, times(1)).createNativeQuery(anyString(), eq(Tuple.class));
        verify(mockQuery, times(1)).setParameter(FIELD_ROLL_NUMBER_LIST, mockRollNumbers);
        verify(mockQuery, times(1)).getResultList();
        verify(mockTuple, times(0)).get(anyString());
    }

    @Test
    public void testFetchAddressListByStudentId(){

        var mockStudentIds = List.of(1);

        Mockito.lenient().when(mockSqlUtils.getSql(SELECT_ADDRESS_BY_STUDENT_ID)).thenReturn(MOCK_QUERY_STRING);

        Mockito.lenient().when(mockEntityManager.createNativeQuery(anyString(), eq(Tuple.class))).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.setParameter(FIELD_STUDENT_ID_LIST, mockStudentIds)).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.getResultList()).thenReturn(List.of(mockTuple));

        Mockito.lenient().when(mockTuple.get(ADDRESS_ID, Integer.class)).thenReturn(1);
        Mockito.lenient().when(mockTuple.get(ADDRESS_LINE_1, String.class)).thenReturn("Demo Line 1");
        Mockito.lenient().when(mockTuple.get(ADDRESS_LINE_2, String.class)).thenReturn("Demo Line 2");
        Mockito.lenient().when(mockTuple.get(STUDENT_ID, Integer.class)).thenReturn(1);

        var result = studentDao.fetchAddressListByStudentId(mockStudentIds);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.containsKey(1));
        assertEquals("Demo Line 1", result.get(1).stream().findFirst().get().getLine1());
        assertEquals("Demo Line 2", result.get(1).stream().findFirst().get().getLine2());

        verify(mockEntityManager, times(1)).createNativeQuery(anyString(), eq(Tuple.class));
        verify(mockQuery, times(1)).setParameter(FIELD_STUDENT_ID_LIST, mockStudentIds);
        verify(mockQuery, times(1)).getResultList();
    }

    @Test
    public void testFetchAddressListByStudentId_EmptyList(){

        var mockStudentIds = List.of(1);

        Mockito.lenient().when(mockSqlUtils.getSql(SELECT_ADDRESS_BY_STUDENT_ID)).thenReturn(MOCK_QUERY_STRING);

        Mockito.lenient().when(mockEntityManager.createNativeQuery(anyString(), eq(Tuple.class))).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.setParameter(FIELD_STUDENT_ID_LIST, mockStudentIds)).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.getResultList()).thenReturn(Collections.emptyList());

        var result = studentDao.fetchAddressListByStudentId(mockStudentIds);

        assertNotNull(result);
        assertEquals(0, result.size());

        verify(mockEntityManager, times(1)).createNativeQuery(anyString(), eq(Tuple.class));
        verify(mockQuery, times(1)).setParameter(FIELD_STUDENT_ID_LIST, mockStudentIds);
        verify(mockTuple, times(0)).get(anyString());
    }

    @Test
    public void testFetchCommunicationListByStudentId(){

        var mockStudentIds = List.of(1);

        Mockito.lenient().when(mockSqlUtils.getSql(SELECT_COMMUNICATION_BY_STUDENT_ID)).thenReturn(MOCK_QUERY_STRING);

        Mockito.lenient().when(mockEntityManager.createNativeQuery(anyString(), eq(Tuple.class))).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.setParameter(FIELD_STUDENT_ID_LIST, mockStudentIds)).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.getResultList()).thenReturn(Arrays.asList(mockTuple));

        Mockito.lenient().when(mockTuple.get(COMMUNICATION_ID, Integer.class)).thenReturn(1);
        Mockito.lenient().when(mockTuple.get(STUDENT_ID, Integer.class)).thenReturn(1);
        Mockito.lenient().when(mockTuple.get(COMMUNICATION_CHANNEL_TYPE, String.class)).thenReturn("Phone");
        Mockito.lenient().when(mockTuple.get(COMMUNICATION_CHANNEL_VALUE, String.class)).thenReturn("9876543210");

        var result = studentDao.fetchCommunicationListByStudentId(mockStudentIds);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(1).get(0).getStudentId());
        assertEquals(1, result.get(1).get(0).getCommId());
        assertEquals("Phone", result.get(1).get(0).getCommChannel());
        assertEquals("9876543210", result.get(1).get(0).getCommValue());

        verify(mockEntityManager, times(1)).createNativeQuery(anyString(), eq(Tuple.class));
        verify(mockQuery, times(1)).setParameter(FIELD_STUDENT_ID_LIST, mockStudentIds);
        verify(mockQuery, times(1)).getResultList();

    }

    @Test
    public void testFetchCommunicationListByStudentId_EmptyList(){

        var mockStudentIds = List.of(1);

        Mockito.lenient().when(mockSqlUtils.getSql(SELECT_COMMUNICATION_BY_STUDENT_ID)).thenReturn(MOCK_QUERY_STRING);

        Mockito.lenient().when(mockEntityManager.createNativeQuery(anyString(), eq(Tuple.class))).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.setParameter(FIELD_STUDENT_ID_LIST, mockStudentIds)).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.getResultList()).thenReturn(Collections.emptyList());

        var result = studentDao.fetchCommunicationListByStudentId(mockStudentIds);

        assertNotNull(result);
        assertEquals(0, result.size());

        verify(mockEntityManager, times(1)).createNativeQuery(anyString(), eq(Tuple.class));
        verify(mockQuery, times(1)).setParameter(FIELD_STUDENT_ID_LIST, mockStudentIds);
        verify(mockTuple, times(0)).get(anyString());
    }

    @Test
    public void testGetTotalRecordCount(){

        Mockito.lenient().when(mockSqlUtils.getSql(SELECT_STUDENT_TOTAL_COUNT)).thenReturn(MOCK_QUERY_STRING);

        Mockito.lenient().when(mockEntityManager.createNativeQuery(anyString(), eq(Integer.class))).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.getSingleResult()).thenReturn(1);

        var result = studentDao.getTotalRecordCount();

        assertNotNull(result);
        assertEquals(1, result);

        verify(mockEntityManager, times(1)).createNativeQuery(anyString(), eq(Integer.class));
        verify(mockQuery, times(1)).getSingleResult();
    }

    @Test
    public void testGetPaginatedStudent(){

        var mockPageNumber = 1;
        var mockPageSize = 5;

        Mockito.lenient().when(mockSqlUtils.getSql(SELECT_ALL_STUDENT_PAGINATED)).thenReturn(MOCK_QUERY_STRING);

        Mockito.lenient().when(mockEntityManager.createNativeQuery(anyString(), eq(Tuple.class))).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.setParameter(PAGE_NUMBER, mockPageNumber-1)).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.setParameter(PAGE_SIZE, mockPageSize)).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.getResultList()).thenReturn(List.of(mockTuple, mockTuple, mockTuple, mockTuple, mockTuple));

        Mockito.lenient().when(mockTuple.get(STUDENT_ID, Integer.class)).thenReturn(1);
        Mockito.lenient().when(mockTuple.get(STUDENT_NAME, String.class)).thenReturn("Demo Name");
        Mockito.lenient().when(mockTuple.get(STUDENT_ROLL_NUMBER, String.class)).thenReturn("Demo Roll");

        var result = studentDao.getPaginatedStudent(mockPageNumber, mockPageSize);

        assertNotNull(result);
        assertEquals(5, result.size());
        assertEquals(1, result.stream().findAny().get().getStudentId());
        assertEquals("Demo Name", result.stream().findAny().get().getStudentName());
        assertEquals("Demo Roll", result.stream().findAny().get().getRollNumber());

        verify(mockEntityManager, times(1)).createNativeQuery(anyString(), eq(Tuple.class));
        verify(mockQuery, times(2)).setParameter(anyString(), anyInt());
        verify(mockQuery, times(1)).getResultList();
    }

    @Test
    public void testGetPaginatedStudent_EmptyList(){

        var mockPageNumber = 1;
        var mockPageSize = 5;

        Mockito.lenient().when(mockSqlUtils.getSql(SELECT_ALL_STUDENT_PAGINATED)).thenReturn(MOCK_QUERY_STRING);

        Mockito.lenient().when(mockEntityManager.createNativeQuery(anyString(), eq(Tuple.class))).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.setParameter(PAGE_NUMBER, mockPageNumber-1)).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.setParameter(PAGE_SIZE, mockPageSize)).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.getResultList()).thenReturn(Collections.emptyList());

        var result = studentDao.getPaginatedStudent(mockPageNumber, mockPageSize);

        assertNotNull(result);
        assertEquals(0, result.size());

        verify(mockEntityManager, times(1)).createNativeQuery(anyString(), eq(Tuple.class));
        verify(mockQuery, times(2)).setParameter(anyString(), anyInt());
        verify(mockTuple, times(0)).get(anyString());
    }

}
