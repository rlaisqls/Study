package com.example.ss.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="board")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Setter
    private String title;
    @NonNull
    @Setter
    private String content;
    @CreatedDate
    @Column(name="create_time")
    @NonNull
    @Setter
    private LocalDate createdAt;
}