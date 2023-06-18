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
public class FeedbackDto {
    private Integer id;

    private String email;

    private String content;

    private Timestamp createdAt;

    private Integer responded;
}
