package com.management.motelroom.service.impl;

import com.management.motelroom.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


@Service
@Slf4j
public class UploadServiceImpl implements UploadService {
    private final static String PATH_ = "./image";
    private final static File file = new File(PATH_);

    public UploadServiceImpl() {
        initDirectory();
    }

    @Override
    public String uploadImage(MultipartFile multipartFile) throws IOException {
        String originName = multipartFile.getOriginalFilename();
        assert originName != null;
        String imageName = UUID.randomUUID()+originName.substring(originName.lastIndexOf("."));

        Path uploadPath = Paths.get(PATH_);
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(imageName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + imageName, ioe);
        }
        return imageName;
    }


    private void initDirectory(){
        if(!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
    }
}
