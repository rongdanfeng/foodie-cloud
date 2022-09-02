package com.imooc.cart;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// tk Mybatis扫描Mapper
@MapperScan(basePackages = "com.imooc.cart.mapper")
// 扫描Component
@ComponentScan(basePackages = {"com.imooc","org.n3r.idworker"})
@EnableDiscoveryClient
@EnableScheduling
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class,args);
    }
}
