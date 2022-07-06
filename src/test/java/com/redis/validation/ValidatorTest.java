package com.redis.validation;

import com.redis.constant.Constant;
import com.redis.entity.Product;
import com.redis.exception.ProductException;
import com.redis.exception.VNPAYException;
import com.redis.model.TransInfo;
import com.redis.utils.MessageUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ValidatorTest {
    @InjectMocks
    private Validator validator;
    private Product product = new Product(null, "Iphone 10", -1, 12333.0);

    @BeforeEach
    void setUp() {
        validator = Mockito.mock(Validator.class);
    }

    @Test
    void validateSaveProd_InputTransNoNull() {
        Exception exception = assertThrows(ProductException.class , () -> Validator.validateSaveProd(product));
        assertEquals(MessageUtils.getMessage(Constant.TRANS_NO_NULL) , exception.getMessage() );
    }

    @Test
    void validateSaveProd_InputQtyWrong() {
        product.setTransactionNo("123445");
        Exception exception = assertThrows(ProductException.class , () -> Validator.validateSaveProd(product));
        assertEquals(MessageUtils.getMessage(Constant.QTY_WRONG) , exception.getMessage());
    }

    @Test
    void validateSaveTrans_InputUserNameEmpty() {
        TransInfo transInfo = new TransInfo("" , "VNPAY", "0987734567", true , 123.8);
        Exception exception = assertThrows(VNPAYException.class , () -> Validator.validateSaveTrans(transInfo));
        assertEquals(MessageUtils.getMessage(Constant.USER_NAME_EMPTY) , exception.getMessage());
    }

    @Test
    void validateSaveTrans_InputAmountWrong() {
        TransInfo transInfo = new TransInfo("SonTT",
                "VNPAY",
                "0967787432",
                true,
                -123.0);
        Exception exception = assertThrows(VNPAYException.class , () -> Validator.validateSaveTrans(transInfo));
        assertEquals(MessageUtils.getMessage(Constant.AMOUNT_WRONG) , exception.getMessage());
    }

    @Test
    void validateSaveTrans_FailWithPhoneWrong() {
        TransInfo transInfo = new TransInfo("SonTT",
                "VNPAY",
                "0967787a2",
                true,
                123.0);

        Exception exception = assertThrows(VNPAYException.class , () -> Validator.validateSaveTrans(transInfo));
        assertEquals(MessageUtils.getMessage(Constant.PHONE_WRONG) , exception.getMessage());
    }

    @Test
    @Disabled
    void validateTime() {
    }
}