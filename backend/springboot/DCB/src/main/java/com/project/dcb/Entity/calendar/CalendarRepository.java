package com.project.dcb.Entity.calendar;

import com.project.dcb.Entity.Gathering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findByNameAndGathering(String username, Gathering gathering);
    List<Calendar> findByGathering(Gathering gathering);
}