package com.practice.board.entity.user;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("Google")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GoogleUser extends User {

    private String oauthId;

}