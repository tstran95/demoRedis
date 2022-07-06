package com.redis.model;

import com.redis.config.YamlPropertySourceFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//import java.util.List;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Configuration
//@EnableConfigurationProperties
//@ConfigurationProperties(prefix = "banks")
//@PropertySource(value = "classpath:application.yaml", factory = YamlPropertySourceFactory.class)
//public class ListOfBank {
//    private List<Bank> banks;
//}
