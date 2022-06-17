package com.practice.board.dto.response;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardIdResponse {
    @NotNull
    Long Id;
}