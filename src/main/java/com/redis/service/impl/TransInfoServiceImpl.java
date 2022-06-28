package com.redis.service.impl;

import com.redis.constant.Constant;
import com.redis.exception.VNPAYException;
import com.redis.modal.TransInfo;
import com.redis.service.TransInfoService;
import com.redis.utils.ProductUtils;
import com.redis.utils.TransInfoUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class TransInfoServiceImpl implements TransInfoService {

    private final RedisTemplate<Object, Object> template;

    @Override
    public void saveTransInfo(TransInfo transInfo) {
        List<String> bankCodes = Arrays.asList("1231", "1232", "1233", "1234");
        // sinh chuỗi 6 số
        String autoGenStr = TransInfoUtils.generateNumber();
        String strValueOfKey = (String) getAll(bankCodes.get(0));
        List<String> listValue = Arrays.asList(strValueOfKey.split(","));
        // đưa bankCode và kiểm tra chuỗi 6 số có bị trùng không
        String result = checkDuplicate(autoGenStr , listValue , 5);
        // nếu bị trùng thì cho retry lại 5 lần. Sau 5 lần mà vẫn lỗi thì throw exception
        if (Objects.isNull(result)) throw new VNPAYException("Duplicate number!!!!");
        // nếu không thì save và đặt expire cho key
        String str = !strValueOfKey.equals("") ? (strValueOfKey + "," + result) : result;
        template.opsForHash().put(Constant.TRANS_INFO_KEY , bankCodes.get(0) , str);
        template.expire(Constant.TRANS_INFO_KEY, Duration.ofSeconds(ProductUtils.getTimeRemaining()));
        System.out.println(str);

    }
    private Object getAll(String bankCode) {
        Object str = template.opsForHash().get(Constant.TRANS_INFO_KEY , bankCode);
        return Objects.isNull(str) ? "" : str ;
    }

    private String checkDuplicate(String autoGenStr , List<String> listValue, int count) {
        boolean flag = true;
        for (int i = count; i > 0 ; i--) {
            for (int j = 0; j < listValue.size() ; j++) {
                if (autoGenStr.equals(listValue.get(j))) {
                    flag = false;
                    autoGenStr = TransInfoUtils.generateNumber();
                    break;
                }
            }
        }
        if (flag) return autoGenStr;
        return null;
    }
}
