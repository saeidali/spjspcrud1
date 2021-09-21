package com.example.spjspcrud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class SpjspcrudApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpjspcrudApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(SpjspcrudApplication.class, args);
        LOGGER.info("started your app");
    }

}
