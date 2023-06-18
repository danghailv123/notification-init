package com.management.motelroom.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    String uploadImage(MultipartFile multipartFile) throws IOException;
}
