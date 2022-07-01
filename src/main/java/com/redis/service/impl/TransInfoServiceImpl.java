package com.redis.service.impl;

import com.redis.constant.Constant;
import com.redis.exception.VNPAYException;
import com.redis.modal.TransInfo;
import com.redis.service.TransInfoService;
import com.redis.utils.JedisUtil;
import com.redis.utils.ProductUtils;
import com.redis.utils.TransInfoUtils;
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
        try {
            // sinh chuỗi 6 số
            String autoGenStr = TransInfoUtils.generateNumber();
            log.info("LOG 1");
            String strValueOfKey = jedisUtil.get(Constant.TRANS_INFO_KEY , bankCodes.get(0));
            log.info("LOG 2");
            List<String> listValue = Arrays.asList(strValueOfKey.split(","));
            log.info("LOG 3");
            // đưa bankCode và kiểm tra chuỗi 6 số có bị trùng không
            String result = TransInfoUtils.checkDuplicate(autoGenStr, listValue, 5);
            log.info("LOG 4");
            // nếu bị trùng thì cho retry lại 5 lần. Sau 5 lần mà vẫn lỗi thì throw exception
            if (Objects.isNull(result)) {
                log.info("TransInfoServiceImpl saveTransInfo Duplicate result ");
                throw new VNPAYException("Duplicate number!!!!");
            }
            // nếu không thì save và đặt expire cho key
            String str = !strValueOfKey.isEmpty() ? (strValueOfKey + "," + result) : result;
            log.info("LOG 5");
            jedisUtil.save(Constant.TRANS_INFO_KEY , bankCodes.get(0), str );
            log.info("LOG 6");
            jedisUtil.expire(Constant.TRANS_INFO_KEY, ProductUtils.getTimeRemaining());
            log.info("LOG 7");
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
