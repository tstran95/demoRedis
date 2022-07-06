package com.redis.controller;

import com.redis.constant.Constant;
import com.redis.exception.VNPAYException;
import com.redis.model.TransInfo;
import com.redis.service.impl.TransInfoServiceImpl;
import com.redis.utils.MessageUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
@ExtendWith(MockitoExtension.class)
class TransInfoControllerTest {
    @Mock
    private TransInfoServiceImpl transInfoService;

    @InjectMocks
    private TransInfoController transInfoController;

    @BeforeEach
    void setUp() {
        transInfoController = new TransInfoController(transInfoService);
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
        assertEquals(transInfoController.saveTransInfo(transInfo).getMessage() , Constant.SUCCESS);
    }

    @Test
    void saveTransInfo_FailWithProdNull() {
        Mockito.doThrow(new VNPAYException(MessageUtils.getMessage(Constant.TRANS_INFO_NULL)))
                .when(transInfoService).saveTransInfo(null);
        assertEquals(MessageUtils.getMessage(Constant.TRANS_INFO_NULL) , transInfoController.saveTransInfo(null).getMessage());
    }


    @Test
    void saveTransInfo_FailWithUserNameEmpty() {
        TransInfo transInfo = new TransInfo("",
                "VNPAY",
                "0967787432",
                true,
                123.0);
        Mockito.doThrow(new VNPAYException(MessageUtils.getMessage(Constant.USER_NAME_EMPTY)))
                .when(transInfoService).saveTransInfo(transInfo);
        assertEquals(MessageUtils.getMessage(Constant.USER_NAME_EMPTY) , transInfoController.saveTransInfo(transInfo).getMessage());
    }

    @Test
    void saveTransInfo_FailWithAmountWrong() {
        TransInfo transInfo = new TransInfo("sddd",
                "VNPAY",
                "0967787432",
                true,
                -123.0);
        Mockito.doThrow(new VNPAYException(MessageUtils.getMessage(Constant.AMOUNT_WRONG)))
                .when(transInfoService).saveTransInfo(transInfo);
        assertEquals(MessageUtils.getMessage(Constant.AMOUNT_WRONG) , transInfoController.saveTransInfo(transInfo).getMessage());
    }

    @Test
    void saveTransInfo_FailWithPhoneWrong() {
        TransInfo transInfo = new TransInfo("SonTT",
                "VNPAY",
                "09677a7432",
                true,
                123.0);
        Mockito.doThrow(new VNPAYException(MessageUtils.getMessage(Constant.PHONE_WRONG)))
                .when(transInfoService).saveTransInfo(transInfo);
        assertEquals(MessageUtils.getMessage(Constant.PHONE_WRONG) , transInfoController.saveTransInfo(transInfo).getMessage());
    }

    @Test
    void saveTransInfo_FailWithDuplicate() {
        Mockito.doThrow(new VNPAYException(MessageUtils.getMessage(Constant.DOUBLE_TRANS)))
                .when(transInfoService).saveTransInfo(any());
        assertEquals(MessageUtils.getMessage(Constant.DOUBLE_TRANS) , transInfoController.saveTransInfo(any()).getMessage());
    }

    @Test
    void saveTransInfo_FailToConnectRedis() {
        Mockito.doThrow(new VNPAYException(MessageUtils.getMessage(Constant.CONNECT_REDIS_ERROR)))
                .when(transInfoService).saveTransInfo(any());
        assertEquals(MessageUtils.getMessage(Constant.CONNECT_REDIS_ERROR) , transInfoController.saveTransInfo(any()).getMessage());
    }
}