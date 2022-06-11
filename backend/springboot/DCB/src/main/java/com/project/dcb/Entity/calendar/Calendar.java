package com.project.dcb.Entity.calendar;

import com.project.dcb.Entity.Gathering;
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

    private String username;

    private String title;

    private Date date;

}