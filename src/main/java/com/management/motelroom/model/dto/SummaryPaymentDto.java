package com.management.motelroom.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
public class SummaryPaymentDto {

    private String username;

    private Double money;

    public SummaryPaymentDto(String username, Double money) {
        this.username = username;
        this.money = money;
    }
}
