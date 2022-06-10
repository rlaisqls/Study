package com.practice.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class BoardIdResponse {
    @NotNull
    Long Id; //이정도면 그냥 inner 클래스로 만드는게 낫지 않나 싶지만 통일을 위해서 그냥 따로 만들어줬다.
}