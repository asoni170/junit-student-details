package com.amit.util;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PageNumberAndSizeValidatorTest {

    @Mock
    private PageNumberAndSizeValidator mockPageNumberAndSizeValidator;

    @Test
    public void testValidatePage_InvalidPageNumber() {
        Integer mockPageNumber = -1;
        Integer mockPageSize = 10;

        MockedStatic mocked = mockStatic(PageNumberAndSizeValidator.class);
        mocked.when(() -> PageNumberAndSizeValidator.validatePage(mockPageNumber, mockPageSize)).thenThrow(new RuntimeException());
        mocked.close();

    }

    @Test
    public void testValidatePage_InvalidPageSize() {
        Integer mockPageNumber = -1;
        Integer mockPageSize = 10;

        MockedStatic mocked = mockStatic(PageNumberAndSizeValidator.class);
        mocked.when(() -> PageNumberAndSizeValidator.validatePage(mockPageNumber, mockPageSize)).thenThrow(new RuntimeException());
        mocked.close();
    }
}
