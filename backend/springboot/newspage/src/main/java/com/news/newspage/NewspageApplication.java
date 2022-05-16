package com.news.newspage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//,scanBasePackages= {"com.news.newspage.repository"}
//,scanBasePackageClasses = AccountRepository.class
//@ComponentScan(basePackages="com.news.newspage.repository")

/*
위에 있는 세개중에 하나를 넣으면 매핑이 안되고
그렇다고 안 넣으면 리포지토리를 찾을 수 없다고 뜨고
이 클래스(가 위치한 패키지)랑 리포지토리(가 위치한 패키지)를 같이 넣어도 리포지토리를 찾을 수 없다고 뜸
어떻게 해결해야할까
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class NewspageApplication {
    public static void main(String[] args) {
        SpringApplication.run(NewspageApplication.class, args);
    }

}