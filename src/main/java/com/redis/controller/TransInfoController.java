package com.redis.controller;

import com.redis.modal.TransInfo;
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
    public void saveTransInfo(@RequestBody TransInfo transInfo) {
        transInfoService.saveTransInfo(transInfo);
    }
}
