package com.shsl;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // 开启注解方式的事务管理
@Slf4j
@MapperScan("com.shsl.mapper")
public class SHSLApplication {
    public static void main(String[] args) {
        SpringApplication.run(SHSLApplication.class, args);
        log.info("Server started");
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/main
