package com.changgou;

import entity.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.changgou.goods.dao"}) // tk包下的，通用mapper的包扫描
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class);
    }

    // 雪花算法注入容器，存入数据库时自动生成id
    @Bean // 默认bean name 为 idWorker
    public IdWorker idWorker() {
        return new IdWorker(0,0);
    }
}
