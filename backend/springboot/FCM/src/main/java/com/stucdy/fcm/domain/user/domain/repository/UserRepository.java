package com.stucdy.fcm.domain.user.domain.repository;

import com.stucdy.fcm.domain.user.domain.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findTop10ByEmailContains(String email);

    @Query(nativeQuery=true, value="SELECT * FROM user WHERE (name regexp ?1) ORDER BY name")
    List<User> findTop10ByNameRegexp(@Param("regexp") String regexp);
}