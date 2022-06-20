package com.study.oopspring.common;

import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

/*proxyMode = ScopedProxyMode.TARGET_CLASS를 설정해주면
 * ObjectProvider를 사용하는 것과 똑같은 효과를 보여준다.
 * 처음 빈 생성할땐 가짜 프록시 빈을 주입해준 다음에
 * request가 들어와서 실제로 이 객체가 생성될때 진짜를 찾아서 넣어주는 방식(지연처리) 으로 작동한다.*/
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
    private String uuid;

    @Setter
    private String requestURL;

    public void log(String message){
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create : "+this);
    }

    @PreDestroy
    public void close(){
        System.out.println("[" + uuid + "] request scope bean close : "+this);
    }
}