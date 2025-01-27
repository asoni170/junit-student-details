package com.amit.service.impl;

import com.amit.dao.impl.StudentDaoImpl;
import com.amit.exception.ResourceNotFoundException;
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

import java.util.Collections;
import java.util.List;

import static com.amit.mock.MockEntityConstants.getMockStudent;
import static com.amit.util.constant.CommonConstants.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentDaoImpl studentDao;

    @Test
    public void testGetStudentsByRollNumbers_EmptyList(){

        var mockRollnumber = List.of("RLOO01", "RL002");

        Mockito.lenient().when(studentDao.fetchStudentByRollNumber(mockRollnumber)).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> studentService.getStudentsByRollNumbers(mockRollnumber));
        verify(studentDao, times(1)).fetchStudentByRollNumber(mockRollnumber);
    }

    @Test
    public void testGetStudentsByRollNumbers(){

        var mockRollnumber = List.of("RLOO01", "RL002");

        Mockito.lenient().when(studentDao.fetchStudentByRollNumber(mockRollnumber)).thenReturn(List.of(getMockStudent()));

        var result = studentService.getStudentsByRollNumbers(mockRollnumber);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(MOCK_STUDENT_ID, result.get(0).getStudentId());
        assertEquals(MOCK_STUDENT_NAME, result.get(0).getStudentName());
        assertEquals(MOCK_STUDENT_ROLLNUMBER, result.get(0).getRollNumber());

        verify(studentDao, times(1)).fetchStudentByRollNumber(mockRollnumber);
    }

    @Test
    public void testGetStudentDetailsByRollnumbers_EmpltyList(){

        var mockRollnumber = List.of("ROL001", "ROLL002");

        Mockito.lenient().when(studentDao.fetchStudentByRollNumber(mockRollnumber)).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> studentService.getStudentDetailsByRollnumbers(mockRollnumber));
        verify(studentDao, times(1)).fetchStudentByRollNumber(mockRollnumber);
    }
}
