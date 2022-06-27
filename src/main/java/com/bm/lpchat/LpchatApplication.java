package com.bm.lpchat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LpchatApplication {

    private static final Logger log = LoggerFactory.getLogger(LpchatApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LpchatApplication.class, args);
    }

}
