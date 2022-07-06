package com.redis.config;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
class YamlPropertySourceFactoryTest {
    @Autowired
    private YamlBankProperties properties;

    @Test
    public void whenFactoryProvidedThenYamlPropertiesInjected() {
        assertEquals("", properties.getBanks().get(0).getBankName());
    }
}