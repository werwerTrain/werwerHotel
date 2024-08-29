package com.buaa.werwerhotel.client.fallback;

import com.buaa.werwerhotel.client.UserClient;

public class UserClientFallback implements UserClient {
    @Override
    public String getEmail(String userId) {
        System.out.println("Get email request failed, fallback method executed");
        return "fallback@example.com"; // Example fallback value
    }
}
