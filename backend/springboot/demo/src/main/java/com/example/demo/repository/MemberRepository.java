package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public abstract class MemberRepository {
    public abstract Member save(Member member);
    public abstract Optional<Member> findById(Long id);
    public abstract Optional<Member> findByName(String name);
    public abstract List<Member> findAll();
}

