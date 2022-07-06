package com.redis.config;

import com.redis.model.Bank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "banks")
@PropertySource(value = "classpath:banks.yml", factory = YamlPropertySourceFactory.class)
@Data
public class YamlBankProperties {
    private List<Bank> banks;
}
