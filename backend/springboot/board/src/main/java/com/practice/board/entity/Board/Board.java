package com.practice.board.entity.Board;

import com.practice.board.entity.comment.Comment;
import com.practice.board.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid", nullable = false)
    private User user;

    private String title;

    private String content;

    @OneToMany(mappedBy = "board") //default가 LAZY
    private List<Comment> comments;
}