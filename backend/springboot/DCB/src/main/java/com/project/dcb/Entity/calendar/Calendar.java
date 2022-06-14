package com.project.dcb.Entity.calendar;

import com.project.dcb.Entity.Gathering;
import com.project.dcb.Entity.user.User;
import com.project.dcb.dto.request.CalendarRequest;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Gathering gathering;

    private String name;

    private String title;

    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Calendar(CalendarRequest request, Gathering gathering, User user){
        this.name = user.getName();
        this.gathering = gathering;
        this.date = Date.valueOf(request.getDate());
        this.title = request.getTitle();
    }


}