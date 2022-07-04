package com.redis.service.impl;

import com.redis.constant.Constant;
import com.redis.exception.VNPAYException;
import com.redis.modal.TransInfo;
import com.redis.utils.JedisUtil;
import com.redis.utils.MessageUtils;
import com.redis.utils.ProductUtils;
import com.redis.utils.TransInfoUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TransInfoServiceImplTest {
//    @Mock
    private JedisUtil jedisUtil;

//    @InjectMocks
    private TransInfoServiceImpl transInfoService;

    @BeforeEach
    void setUp() {
        jedisUtil = Mockito.mock(JedisUtil.class);
        transInfoService = new TransInfoServiceImpl(jedisUtil);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveTransInfo_Success() {
        TransInfo transInfo = new TransInfo("SonTT",
                                            "VNPAY",
                                            "0967787432",
                                            true,
                                            123.0);
        Mockito.when(jedisUtil.saveInSet(any() , any())).thenReturn(true);
        transInfoService.saveTransInfo(transInfo);
        Mockito.verify(jedisUtil , Mockito.times(1)).saveInSet(any() , any());
    }

    @Test
    void saveTransInfo_FailWithTransInfoNull() {
        Exception exception = assertThrows(VNPAYException.class , () -> transInfoService.saveTransInfo(null));
        assertEquals(MessageUtils.getMessage(Constant.TRANS_INFO_NULL) , exception.getMessage());
    }

    @Test
    void saveTransInfo_FailWithUserNameEmpty() {
        TransInfo transInfo = new TransInfo("",
                                            "VNPAY",
                                            "0967787432",
                                            true,
                                            123.0);
        Exception exception = assertThrows(VNPAYException.class , () -> transInfoService.saveTransInfo(transInfo));
        assertEquals(MessageUtils.getMessage(Constant.USER_NAME_EMPTY) , exception.getMessage());
    }

    @Test
    void saveTransInfo_FailWithAmountWrong() {
        TransInfo transInfo = new TransInfo("SonTT",
                "VNPAY",
                "0967787432",
                true,
                -123.0);
        Exception exception = assertThrows(VNPAYException.class , () -> transInfoService.saveTransInfo(transInfo));
        assertEquals(MessageUtils.getMessage(Constant.AMOUNT_WRONG) , exception.getMessage());
    }

    @Test
    void saveTransInfo_FailWithPhoneWrong() {
        TransInfo transInfo = new TransInfo("SonTT",
                "VNPAY",
                "0967787a2",
                true,
                123.0);

        Exception exception = assertThrows(VNPAYException.class , () -> transInfoService.saveTransInfo(transInfo));
        assertEquals(MessageUtils.getMessage(Constant.PHONE_WRONG) , exception.getMessage());
    }

    @Test
    void saveTransInfo_FailWithDuplicate() {
        TransInfo transInfo = new TransInfo("SonTT",
                "VNPAY",
                "0967787122",
                true,
                123.0);
        Mockito.when(jedisUtil.saveInSet(any(), any())).thenReturn(false);
        Exception exception = assertThrows(VNPAYException.class , () -> transInfoService.saveTransInfo(transInfo));
        assertEquals(MessageUtils.getMessage(Constant.DOUBLE_TRANS) , exception.getMessage());
    }
}