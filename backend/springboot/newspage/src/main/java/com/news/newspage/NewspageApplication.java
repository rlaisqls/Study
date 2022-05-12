package com.news.newspage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class NewspageApplication {
    public static void main(String[] args) {
        SpringApplication.run(NewspageApplication.class, args);
    }

}
