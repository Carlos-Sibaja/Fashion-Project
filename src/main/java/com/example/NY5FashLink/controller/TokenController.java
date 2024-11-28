package com.example.NY5FashLink.controller;

import com.example.NY5FashLink.utils.AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    private static final String APP_ID = "1c3afea4acd64d4f94760320716edf63";
    private static final String APP_CERTIFICATE = "7bb06eb61862432bac79b9228d89541a";

    @GetMapping("/generate-token")
    public String generateToken(
            @RequestParam String channelName,
            @RequestParam String uid,
            @RequestParam int expireTimeInSeconds) {

        try {
            int expireTimestamp = (int) (System.currentTimeMillis() / 1000) + expireTimeInSeconds;
            return AccessToken.generateToken(APP_ID, APP_CERTIFICATE, channelName, uid, expireTimestamp);
        } catch (Exception e) {
            return "Error generating token: " + e.getMessage();
        }
    }
}

