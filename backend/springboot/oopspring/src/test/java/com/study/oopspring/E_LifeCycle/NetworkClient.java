package com.study.oopspring.E_LifeCycle;

import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


public class NetworkClient {
    @Setter
    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출 url = " + url);
    }

    public void connect(){
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url+ "message = " + message);
    }

    public void disconnect(){
        System.out.println("close : " + url);
    }

    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}