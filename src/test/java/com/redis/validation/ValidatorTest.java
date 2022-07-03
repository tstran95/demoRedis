package com.redis.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ValidatorTest {
    @Mock
    Validator validator;

    @Test
    void validateSaveProd() {
    }

    @Test
    void validateTime() {
    }

    @Test
    void checkNumber() {
        Mockito.when(Validator.checkNothing("0")).thenReturn(true);
        assertFalse(Validator.checkNumber("0"));
    }

    @Test
    void checkNothing() {
    }
}