package com.study.oopspring.F_Scope;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonWithPrototypeTest1 {
    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUserPrototype(){
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean1.logic();
        assertThat(count2).isEqualTo(1);
    }

    /**
     * Provides instances of {@code T}. Typically implemented by an injector. For
     * any type {@code T} that can be injected, you can also inject
     * {@code Provider<T>}. Compared to injecting {@code T} directly, injecting
     * {@code Provider<T>} enables:
     *
     * <ul>
     *   <li>retrieving multiple instances.</li>
     *   <li>lazy or optional retrieval of an instance.</li>
     *   <li>breaking circular dependencies.</li>
     *   <li>abstracting scope so you can look up an instance in a smaller scope
     *      from an instance in a containing scope.</li>
     * </ul>
     */

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean{
        /*ObjectFactory, ObjectProvider 스프링 의존적
        javax.inject.Provider 자바표준
        스프링 컨테이너 말고 다른걸로 바꿀 일 없으면 그냥 스프링걸 쓰는게 좋다!*/
        private final ObjectProvider<PrototypeBean> prototypeBeanProvider; //조회 대신 해줌

        //@Lookup이라는 것도 있기는 함함 잘 쓰지는 않음
       public int logic(){
            //빈이 직접 의존관계를 찾는 것을 DL(Dependency Lookup) 이라고 한다.
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    @Getter
    static class PrototypeBean {
        private int count = 0;
        public void addCount() {
            count++;
        }
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}