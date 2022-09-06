package com.project.wouldyou.domain.repository;

import com.project.wouldyou.domain.Constellation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConstellationRepository extends JpaRepository<Constellation, Integer> {

    List<Constellation> findBySeasonOrSeason(String season1, String season2);
}