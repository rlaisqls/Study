package com.project.dcb.dto.response;

import com.project.dcb.Entity.calendar.Calendar;
import com.project.dcb.Entity.Gathering;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Date;

@Getter
@Setter
public class CalendarResponse {
    private Date date;

    private String title;

    @Enumerated(EnumType.STRING)
    private Gathering gathering;

    public CalendarResponse(Calendar calendar) {
        date = calendar.getDate();
        title = calendar.getTitle();
        gathering = calendar.getGathering();
    }
}