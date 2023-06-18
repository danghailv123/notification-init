package com.management.motelroom.service;

import com.management.motelroom.model.dto.NotificationDto;
import com.management.motelroom.utils.ResponseResult;
import org.springframework.http.ResponseEntity;

public interface NotificationService {
    NotificationDto getNotification(Integer id);

    ResponseResult<NotificationDto> getPage(Integer page, Integer limit);

    NotificationDto createNotification(com.management.motelroom.model.dto.NotificationDto notificationDto);

    NotificationDto updateNotification(NotificationDto notificationDto);

    Integer deleteNotification(Integer id);
}
