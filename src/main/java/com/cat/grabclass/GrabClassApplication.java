package com.cat.grabclass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Mr.xin
 */
@MapperScan("com.cat.grabclass.dao")
@SpringBootApplication
@EnableTransactionManagement
public class GrabClassApplication {
    public static void main(String[] args) {
        SpringApplication.run(GrabClassApplication.class, args);
    }
}
