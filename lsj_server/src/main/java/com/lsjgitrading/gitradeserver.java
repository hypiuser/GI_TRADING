package com.lsjgitrading;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperScan("com.lsjgitrading.mapper")
public class gitradeserver {
    public static void main(String[] args) {
        SpringApplication.run(gitradeserver.class, args);
    }

}
