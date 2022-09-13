package com.stucdy.fcm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FcmApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("김은빈".matches("^*+^은빈*+^*$"));
    }
}