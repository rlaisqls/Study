package com.study.oopspring.F_Scope;


import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProtoTypeTest {
    @Test
    void singletonBeanFind(){
        System.out.println("ProtoTypeTest.singletonBeanFind");
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);
        ProtoTypeBean bean1 = ac.getBean(ProtoTypeBean.class);
        ProtoTypeBean bean2 = ac.getBean(ProtoTypeBean.class);
        assertThat(bean1).isNotEqualTo(bean2);
        //bean1.destroy();
        //bean2.destroy();
        ac.close();
    }

    @Scope("prototype")
    static class ProtoTypeBean{
        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }
        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.destroy");
        }
    }
}