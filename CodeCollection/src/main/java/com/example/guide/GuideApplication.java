package com.example.guide;

import com.example.guide.extend.ExcludeAutoConfigurationClass;
import com.example.guide.util.RedisUtil;
import com.sun.codemodel.internal.JAnnotatable;
import com.sun.codemodel.internal.JClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
//通过exclude可以排除自动配置类
@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@SpringBootApplication(excludeName = {"DataSourceAutoConfiguration","RedisAutoConfiguration", "RedisRepositoriesAutoConfiguration"})

public class GuideApplication {

    public static void main(String[] args) {

        SpringApplication.run(GuideApplication.class, args);
        System.out.println("http://localhost:8086/test2?param=java");
        System.out.println("http://localhost:8086/t");
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(GuideApplication.class);
//    }
}
