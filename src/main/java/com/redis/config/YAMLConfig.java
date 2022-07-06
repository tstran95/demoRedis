package com.redis.config;

import com.redis.model.Bank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "banks")
public class YAMLConfig {
    private List<Bank> banks;
}
