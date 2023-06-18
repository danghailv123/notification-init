package com.management.motelroom.controller;

import com.management.motelroom.model.dto.NotificationDto;
import com.management.motelroom.service.NotificationService;
import com.management.motelroom.utils.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDto> notificationDtoResponseEntity(@PathVariable Integer id) {
        return ResponseEntity.ok().body(notificationService.getNotification(id));
    }

    @GetMapping
    public ResponseResult<NotificationDto> notificationDtoResponseResult(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                         @RequestParam(name = "limit", defaultValue = "30") Integer limit) {
        return notificationService.getPage(page, limit);
    }

    @PostMapping
    public ResponseEntity<NotificationDto> createNotification(@RequestBody NotificationDto notificationDto) {
        return ResponseEntity.ok().body(notificationService.createNotification(notificationDto));
    }

    @PutMapping
    public ResponseEntity<NotificationDto> updateNotification(@RequestBody NotificationDto notificationDto){
        return ResponseEntity.ok().body(notificationService.updateNotification(notificationDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deletedNotification(@PathVariable Integer id){
        return ResponseEntity.ok().body(notificationService.deleteNotification(id));

    }
}
