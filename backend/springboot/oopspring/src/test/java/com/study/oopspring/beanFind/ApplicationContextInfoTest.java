package com.study.oopspring.beanFind;

import com.study.oopspring.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("전체 빈 조회")
    void findAllBean() {

        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); //빈 이름 전체 조회
        System.out.println(beanDefinitionNames.length);

        for (String beanDefinitionName : beanDefinitionNames) {  //iter
            Object bean = ac.getBean(beanDefinitionName); //빈 이름으로 객체 조회
            System.out.println("name = " + beanDefinitionName + "object = " + bean); //soutv
        }

    }

    @Test
    @DisplayName("어플리케이션 빈 조회")
    void findApplicationBean() {

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        System.out.println(beanDefinitionNames.length);

        for (String beanDefinitionName : beanDefinitionNames) {  //iter
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            //BeanDefinition.ROLE_INFRASTRUCTURE 스프링이 내부에서 사용하는 빈
            //BeanDefinition.ROLE_APPLICATION 사용자가 정의한 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + "object = " + bean); //soutv
            }
        }

    }
}