package com.shsl;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // Enable annotated transaction management
@Slf4j
@MapperScan("com.shsl.mapper")
public class SHSLApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SHSLApplication.class, args);
        log.info("Server started");
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SHSLApplication.class);
    }

}

