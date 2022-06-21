package com.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "Product" , timeToLive = 300)
public class Product implements Serializable {
    @Id
    private Long id;
    private String name;
    private int qty;
    private Double price;
}
