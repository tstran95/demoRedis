package com.redis.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "TransInfo")
public class TransInfo implements Serializable {
    private String userName;
    private String bankName;
    private String phoneNumber;
    private boolean otpStatus;
    private Double amount;
}
