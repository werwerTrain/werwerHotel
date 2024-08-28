package com.buaa.werwerhotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WerwerHotelApplication {

    public static void main(String[] args) {
        SpringApplication.run(WerwerHotelApplication.class, args);
    }

}
