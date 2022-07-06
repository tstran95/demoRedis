package com.redis.config;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class YamlPropertySourceFactoryTest {
    @Autowired
    private YAMLConfig yamlConfig;

    @Test
    public void whenFactoryProvidedThenYamlPropertiesInjected() {
        assertEquals(yamlConfig.getBanks().get(1).getBankName() , "123" );
    }
}