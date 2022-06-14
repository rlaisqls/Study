package com.project.dcb.Entity.user;

import com.project.dcb.Entity.Board.Board;
import com.project.dcb.Entity.Gathering;
import com.project.dcb.Entity.calendar.Calendar;
import com.project.dcb.dto.request.RegisterRequest;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import sun.security.util.Password;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Gathering gathering;

    private String username;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "user")
    private List<Calendar> calendars;

    public User(RegisterRequest request, PasswordEncoder passwordEncoder) {
        name = request.getName();
        username = request.getUsername();
        password = passwordEncoder.encode(request.getPassword());
        gathering = Gathering.valueOf("CLASS_" + request.getGathering().charAt(0) + "_" + request.getGathering().charAt(2));
        authority = Authority.valueOf("ROLE_USER");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(authority.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}