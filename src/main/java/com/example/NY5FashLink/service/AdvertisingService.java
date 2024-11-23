package com.example.NY5FashLink.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.NY5FashLink.model.*;
import com.example.NY5FashLink.repository.StoreRepository;
import com.example.NY5FashLink.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AdvertisingService {

    private final UserRepository userRepository;
    private StoreRepository storeRepository;

    private Cloudinary cloudinary;


    public String uploadArtPiece(MultipartFile file) throws IOException {
        // Convert the MultipartFile to a byte array
        byte[] fileBytes = file.getBytes();

        // Add required metadata
        Map<String, Object> uploadOptions = new HashMap<>();
        uploadOptions.put("resource_type", "image");

        // Upload file to Cloudinary
        Map uploadResult = cloudinary.uploader().upload(fileBytes, uploadOptions);

        // Extract and return the uploaded file's URL
        return (String) uploadResult.get("secure_url");
    }

    // Agregar publicidad
    public void addAdvertising(String email, String storeName, double comissionRate, String comissionCode,
                               String startDate, String endDate, MultipartFile artPieceURL)
                               throws IOException {
        // Obtener usuario autenticado
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //String username = auth.getEmail();

        // Buscar el usuario tipo STORE
        Users users = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found ."));

        // Crear instancia de Store(modelo)
        Store store = new Store();

        store.setStoreName(storeName);
        store.setComissionRate(comissionRate);
        store.setComissionCode(comissionCode);
        store.setStartDate(startDate);
        store.setEndingdate(endDate);
        store.setStoreName(storeName);

        try {
            // Upload profile picture and get UR
            String pictureUrl = uploadartPieceURL(artPieceURL);
            store.setArtPieceURL(pictureUrl);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload profile picture", e);
        }

        storeRepository.save(store);
    }

    public String uploadartPieceURL(MultipartFile file) throws IOException {
        // Convert the MultipartFile to a byte array
        byte[] fileBytes = file.getBytes();

        // Add required metadata
        Map<String, Object> uploadOptions = new HashMap<>();
        uploadOptions.put("resource_type", "image");

        // Upload file to Cloudinary
        Map uploadResult = cloudinary.uploader().upload(fileBytes, uploadOptions);

        // Extract and return the uploaded file's URL
        return (String) uploadResult.get("secure_url");
    }

}
