package com.example.NY5FashLink.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AccessToken {
    private static final String HMAC_SHA256 = "HmacSHA256";

    public static String generateToken(String appId, String appCertificate, String channelName, String uid, int expireTimestamp) {
        try {
            // Content to sign
            String content = appId + channelName + uid;

            // Generate signature using HMAC-SHA256
            Mac mac = Mac.getInstance(HMAC_SHA256);
            mac.init(new SecretKeySpec(appCertificate.getBytes(StandardCharsets.UTF_8), HMAC_SHA256));
            byte[] signature = mac.doFinal(content.getBytes(StandardCharsets.UTF_8));

            // Encode token
            String signatureHex = bytesToHex(signature);
            String rawToken = signatureHex + content;
            return Base64.getEncoder().encodeToString(rawToken.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate Agora token", e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

