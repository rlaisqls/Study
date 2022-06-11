package com.project.dcb.Entity.Board;

import com.project.dcb.Entity.Gathering;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Gathering gathering;

    private String username;

    private String title;

    private String content;
}