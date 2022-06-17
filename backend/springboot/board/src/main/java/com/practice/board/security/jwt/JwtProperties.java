package com.practice.board.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

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