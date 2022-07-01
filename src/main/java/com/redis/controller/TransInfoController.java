package com.redis.controller;

import com.redis.constant.Constant;
import com.redis.exception.VNPAYException;
import com.redis.modal.TransInfo;
import com.redis.reponse.TransInfoResponse;
import com.redis.service.TransInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/trans")
@AllArgsConstructor
@Slf4j
public class TransInfoController {
    private final TransInfoService transInfoService;

    @PostMapping
    public TransInfoResponse saveTransInfo(@RequestBody TransInfo transInfo) {
        log.info("TransInfoController saveTransInfo START with transInfo {}" , transInfo);
        TransInfoResponse response = new TransInfoResponse();
        int count = 0;
        try {
            for (int i = 0; i < 100_000; i++) {
                transInfoService.saveTransInfo(transInfo);
                count++;
            }
            response.setRCode(Constant.RCODE_01);
            response.setMessage(Constant.SUCCESS);
        } catch (Exception e) {
            response.setRCode(Constant.RCODE_01);
            response.setMessage(e.getMessage());
        }
        System.err.println(count + " COUNT");
        log.info("TransInfoController saveTransInfo END with transInfo {}" , transInfo);
        return response;
    }
}
