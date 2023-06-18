package com.management.motelroom.service.impl;

import com.management.motelroom.entity.Feedback;
import com.management.motelroom.entity.Notification;
import com.management.motelroom.model.dto.FeedbackDto;
import com.management.motelroom.model.dto.NotificationDto;
import com.management.motelroom.repository.NotificationRepository;
import com.management.motelroom.service.NotificationService;
import com.management.motelroom.utils.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    public NotificationDto getNotification(Integer id) {
        Notification notification = notificationRepository.findById(id).get();
        return buildDTO(notification);
    }

    @Override
    public ResponseResult<NotificationDto> getPage(Integer page, Integer limit) {
        Page<Notification> notificationPage =notificationRepository.findAll(PageRequest.of(page,limit, Sort.by(Sort.Direction.DESC,"createdAt")));
        ResponseResult<NotificationDto> notificationDtoResponseResult = new ResponseResult<>();
        notificationDtoResponseResult.setTotalData(notificationPage.getTotalElements());
        notificationDtoResponseResult.setTotalPage(notificationPage.getTotalPages());
        notificationDtoResponseResult.setData(notificationPage.getContent().stream().map(this::buildDTO).collect(Collectors.toList()));
        return notificationDtoResponseResult;
    }

    @Override
    public NotificationDto createNotification(NotificationDto notificationDto) {
        Notification notification = Notification.builder()
                .name(notificationDto.getName())
                .summaryTitle(notificationDto.getSummaryTitle())
                .title(notificationDto.getTitle())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .image(notificationDto.getImage())
                .build();
        notification = notificationRepository.save(notification);
        return buildDTO(notification);
    }

    @Override
    public NotificationDto updateNotification(NotificationDto notificationDto) {
        Notification notification = notificationRepository.getReferenceById(notificationDto.getId());
        notification.setName(notificationDto.getName());
        notification.setSummaryTitle(notificationDto.getSummaryTitle());
        notification.setTitle(notificationDto.getTitle());
        notification.setImage(notificationDto.getImage());
        notificationRepository.save(notification);
        return buildDTO(notification);
    }

    @Override
    public Integer deleteNotification(Integer id) {
        notificationRepository.deleteById(id);
        return id;
    }


    private NotificationDto buildDTO(Notification notification) {
        return NotificationDto.builder()
                .id(notification.getId())
                .name(notification.getName())
                .summaryTitle(notification.getSummaryTitle())
                .title(notification.getTitle())
                .createdAt(notification.getCreatedAt())
                .image(notification.getImage())
                .build();
    }
}
