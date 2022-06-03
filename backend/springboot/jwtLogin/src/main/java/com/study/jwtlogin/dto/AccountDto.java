package com.study.jwtlogin.dto;

import com.study.jwtlogin.entity.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    @NotNull
    @Size(min = 3, max = 20)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    private String authority;

    public static AccountDto from(Account account) {
        if (account == null) return null;
        return AccountDto.builder()
                .username(account.getUsername())
                .authority(String.valueOf(account.getAuthority()))
                .build();
    }
}