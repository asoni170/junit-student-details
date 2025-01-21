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
import java.util.List;

import static com.amit.util.constant.SqlQueryConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

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

        var mockRollNumbers = List.of("ROLL001", "ROLL002", "ROLL003");

        Mockito.lenient().when(mockSqlUtils.getSql(MOCK_QUERY_KEY)).thenReturn(MOCK_QUERY_STRING);
        Method methodGetSql = StudentDaoImpl.class.getDeclaredMethod("getSql", String.class);
        methodGetSql.setAccessible(Boolean.TRUE);

        Mockito.lenient().when(mockEntityManager.createNativeQuery(anyString(), eq(Tuple.class))).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.setParameter(FIELD_ROLL_NUMBER_LIST, mockRollNumbers)).thenReturn(mockQuery);
        Mockito.lenient().when(mockQuery.getResultList()).thenReturn(List.of(mockTuple));

        var result = studentDao.fetchStudentByRollNumber(mockRollNumbers);
        System.out.println(result);
    }
}
