package com.buaa.werwerhotel.client;

import com.buaa.werwerhotel.client.fallback.UserClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", contextId = "userClient", path = "/api/users", fallback = UserClientFallback.class)
public interface UserClient {
    @GetMapping("/getEmail/{userId}")
    public String getEmail(@PathVariable("userId") String userId);
}
