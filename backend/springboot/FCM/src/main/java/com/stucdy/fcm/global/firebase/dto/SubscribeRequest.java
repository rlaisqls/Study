package com.stucdy.fcm.global.firebase.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class SubscribeRequest {

    @NotNull
    private List<Long> userIdList;

    @NotNull(message = "roomId는 null일 수 없습니다")
    private Long roomId;
}