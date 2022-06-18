package com.practice.board.entity.user;

import com.practice.board.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("General")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GeneralUser extends User {
    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private User user;
    private String password;
}