package com.example.socialnetwork.config;

import java.util.UUID;

public class ApplicationUtils {

    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
