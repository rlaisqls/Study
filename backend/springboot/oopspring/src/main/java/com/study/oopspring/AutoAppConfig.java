package com.study.oopspring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( excludeFilters = @ComponentScan.Filter
        (type = FilterType.ANNOTATION, classes = Configuration.class)) //@Component가 붙은 클래스를 빈으로 등록해줌
public class AutoAppConfig {

}