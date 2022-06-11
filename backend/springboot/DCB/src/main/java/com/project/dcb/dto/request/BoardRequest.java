package com.project.dcb.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BoardRequest {

    @NotNull
    private String title;

    @NotNull
    private String content;
}