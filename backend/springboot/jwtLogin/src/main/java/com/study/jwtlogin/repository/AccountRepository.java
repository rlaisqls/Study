package com.study.jwtlogin.repository;

import com.study.jwtlogin.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    /*Authority를 n:m 매핑으로 구현했을때
    Optional<Account> findOneWithAuthorityByUsername(String username);
    를 사용했음. 검색해도 관련 내용이 전혀 안나와서 정확힌 모르겠지만 건너 테이블에 있는 정보까지 끌어오겠다는 뜻이 아닐까 싶음*/
}