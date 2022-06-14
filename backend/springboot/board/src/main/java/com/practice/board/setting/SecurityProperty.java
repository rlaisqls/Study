package com.practice.board.setting;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class SecurityProperty {

    private String header;
    private String secret;
    private Exp exp;

    @Getter
    @Setter
    public static class Exp {
        private Long token;
        private Long refresh;
    }
}