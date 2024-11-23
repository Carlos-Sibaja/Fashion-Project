package com.example.NY5FashLink.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        try {
            System.out.println("Starting upload of file: " + file.getOriginalFilename());
            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            System.out.println("File uploaded successfully. URL: " + uploadResult.get("url"));
            return (String) uploadResult.get("url");
        } catch (IOException e) {
            System.err.println("Error uploading file to Cloudinary: " + e.getMessage());
            throw e;
        }
    }
}