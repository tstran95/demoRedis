package com.redis.service.impl;

import com.redis.constant.Constant;
import com.redis.exception.VNPAYException;
import com.redis.modal.TransInfo;
import com.redis.service.TransInfoService;
import com.redis.utils.JedisUtil;
import com.redis.utils.MessageUtils;
import com.redis.utils.TransInfoUtils;
import com.redis.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class TransInfoServiceImpl implements TransInfoService {
    private final JedisUtil jedisUtil;

    @Override
    public void saveTransInfo(TransInfo transInfo) {
        log.info("TransInfoServiceImpl saveTransInfo START with transInfo {}", transInfo);
        List<String> bankCodes = Arrays.asList("1600", "1610", "1620", "1630");
        if (Objects.isNull(transInfo)) {
            throw new VNPAYException(MessageUtils.getMessage(Constant.TRANS_INFO_NULL));
        }
        try {
            Validator.validateSaveTrans(transInfo);
            String autoGenStr;
            // Can retry 5 times
            for (int i = 0; i <= 5; i++) {
                if (i == 5) {
                    log.info("TransInfoServiceImpl saveTransInfo Duplicate result ");
                    throw new VNPAYException(MessageUtils.getMessage(Constant.DOUBLE_TRANS));
                }
                // generate string with 6 number
                autoGenStr = TransInfoUtils.generateNumber();
                boolean result = jedisUtil.saveInSet(bankCodes.get(0) , autoGenStr);
                if (result) break;
            }
        } catch (VNPAYException e) {
            log.info("TransInfoServiceImpl saveTransInfo error with message ", e);
            throw e;
        } catch (Exception e) {
            log.error("TransInfoServiceImpl saveTransInfo error with message ", e);
            throw e;
        }
        log.info("TransInfoServiceImpl saveTransInfo END");
    }
}
