package com.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.sql.Timestamp;

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
}
