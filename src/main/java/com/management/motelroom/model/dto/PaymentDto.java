package com.management.motelroom.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentDto {
    private Integer id;

    private Integer is;

    private Integer type;

    private Timestamp createdAt;

    private String username;

    private Float money;
}
