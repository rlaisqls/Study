package com.project.dcb.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CalendarRequest {

    @NotNull
    private String title;

    @NotNull
    private String date;
}