package com.imooc.miaoshaproject;

import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 */
@SpringBootApplication(scanBasePackages = {"com.imooc.miaoshaproject"})
@MapperScan("com.imooc.miaoshaproject.dao")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public Redisson redisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://47.104.175.129:6389").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }
}
