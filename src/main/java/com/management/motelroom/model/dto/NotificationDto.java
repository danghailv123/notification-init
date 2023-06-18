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
public class NotificationDto {
    private Integer id;

    private String name;

    private String summaryTitle;

    private String title;

    private Timestamp createdAt;

    private String image;
}
