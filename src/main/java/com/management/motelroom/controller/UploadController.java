package com.management.motelroom.controller;


import com.management.motelroom.service.UploadService;
import com.management.motelroom.utils.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/upload")
public class UploadController {
    private final UploadService uploadService;

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam(name = "image") MultipartFile file) throws IOException {
        return ResponseEntity.ok().body(uploadService.uploadImage(file));
    }
}
