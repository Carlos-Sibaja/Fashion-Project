package com.example.NY5FashLink.controller;

import com.example.NY5FashLink.model.UserRegistrationDTO;
import com.example.NY5FashLink.service.AdvertisingService;
import com.example.NY5FashLink.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/ads")
@AllArgsConstructor

public class StoreController {

    private final AdvertisingService  advertisingService;

    public StoreController(AdvertisingService advertisingService) {
        this.advertisingService = advertisingService;

        // Define a constant for the maximum file size (in bytes)
        //private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5 MB






    }





}



