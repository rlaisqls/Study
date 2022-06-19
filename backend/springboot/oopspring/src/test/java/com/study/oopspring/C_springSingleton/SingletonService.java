package com.study.oopspring.C_SpringSingleton;

public class SingletonService {
    //이 메서드를 호출하면 항상 같은 인스턴스를 반환한다.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){}
}