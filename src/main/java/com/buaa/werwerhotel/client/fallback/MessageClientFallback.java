package com.buaa.werwerhotel.client.fallback;

import com.buaa.werwerhotel.client.MessageClient;

import java.util.Map;

public class MessageClientFallback implements MessageClient {
    @Override
    public void addMessage(Map<String, Object> messageMap) {
        System.out.println("add message have sent request failed, fallback method executed.");
    }
}
