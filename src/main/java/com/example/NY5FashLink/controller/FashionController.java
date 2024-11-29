package com.example.NY5FashLink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fashion")
public class FashionController {

    @GetMapping
    public String fashion() {
        return "fashion";
    }
}
