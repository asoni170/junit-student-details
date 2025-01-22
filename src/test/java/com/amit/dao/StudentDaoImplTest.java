package com.amit.dao;

import com.amit.dao.impl.StudentDaoImpl;
import com.amit.util.SqlUtils;
import com.amit.util.constant.DbConstants;
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
import java.util.List;

import static com.amit.util.constant.SqlQueryConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

        Mockito.lenient().when(mockSqlUtils.getSql(SELECT_STUDENT_BY_ROLL_NUMBERS_IN))
                .thenReturn("select s.std_id, s.std_name, s.str_roll_no from t_student s where s.str_roll_no in :studentRollnumbers");

        Mockito.lenient().when(mockEntityManager.createNativeQuery(anyString(), eq(Tuple.class))).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.setParameter(FIELD_ROLL_NUMBER_LIST, mockRollNumbers)).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.getResultList()).thenReturn(List.of(mockTuple));

        Mockito.lenient().when(mockTuple.get(DbConstants.STUDENT_ID, Integer.class)).thenReturn(1);
        Mockito.lenient().when(mockTuple.get(DbConstants.STUDENT_ROLL_NUMBER, String.class)).thenReturn("ROLL001");
        Mockito.lenient().when(mockTuple.get(DbConstants.STUDENT_NAME, String.class)).thenReturn("Test Name");

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
}
