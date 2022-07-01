package com.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "Product")
public class Product implements Serializable {
    @Id
    @Column(name = "token")
    private String token;

    @Column(name = "transaction_no")
    private String transactionNo;

    @Column(name = "name")
    private String name;

    @Column(name = "qty")
    private int qty;

    @Column(name = "price")
    private Double price;


    @Column(name = "created_date")
    private String CreatedDate;

    @Column(name = "updated_date")
    private String UpdateDate;


    public Product(String transactionNo, String name, int qty, Double price) {
        this.transactionNo = transactionNo;
        this.name = name;
        this.qty = qty;
        this.price = price;
    }
}
