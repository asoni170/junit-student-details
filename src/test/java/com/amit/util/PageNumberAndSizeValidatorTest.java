package com.amit.util;

import com.amit.exception.PageInvalidException;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static com.amit.util.constant.CommonConstants.INVALID_PAGE_NUMBER;
import static com.amit.util.constant.CommonConstants.INVALID_PAGE_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PageNumberAndSizeValidatorTest {

    @Test
    public void testValidatePage_ShouldThrowException_WhenPageNumberIsNull() {
        Exception exception = assertThrows(PageInvalidException.class, () -> PageNumberAndSizeValidator.validatePage(null, 10) );
        assertEquals(INVALID_PAGE_NUMBER, exception.getMessage());
    }

    @Test
    public void testValidatePage_ShouldThrowException_WhenPageNumberIsNegative() {
        Exception exception = assertThrows(PageInvalidException.class, () -> PageNumberAndSizeValidator.validatePage(-1, 10) );
        assertEquals(INVALID_PAGE_NUMBER, exception.getMessage());
    }

    @Test
    public void testValidatePage_ShouldThrowException_WhenSizeNumberIsNull() {
        Exception exception = assertThrows(PageInvalidException.class, () -> PageNumberAndSizeValidator.validatePage(1, null) );
        assertEquals(INVALID_PAGE_SIZE, exception.getMessage());
    }

    @Test
    public void testValidatePage_ShouldThrowException_WhenSizeNumberIsNegative() {
        Exception exception = assertThrows(PageInvalidException.class, () -> PageNumberAndSizeValidator.validatePage(1, -10) );
        assertEquals(INVALID_PAGE_SIZE, exception.getMessage());
    }
}
