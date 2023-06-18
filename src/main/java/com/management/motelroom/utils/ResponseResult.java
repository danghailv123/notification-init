package com.management.motelroom.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseResult<T>{
    List<T> data;
    Long totalData;
    Integer totalPage;
}
