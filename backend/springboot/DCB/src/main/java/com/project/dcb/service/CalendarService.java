package com.project.dcb.service;

import com.project.dcb.Entity.calendar.Calendar;
import com.project.dcb.Entity.calendar.CalendarRepository;
import com.project.dcb.Entity.Gathering;
import com.project.dcb.Entity.user.User;
import com.project.dcb.Entity.user.UserRepository;
import com.project.dcb.dto.request.CalendarRequest;
import com.project.dcb.dto.response.CalendarResponse;
import com.project.dcb.exception.InvalidTokenException;
import com.project.dcb.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final UserRepository userRepository;
    private final CalendarRepository calendarRepository;

    public List<CalendarResponse> findMyCalendar() {
        User user = nowUser();
        return calendarRepository.findByUsernameAndGathering(user.getUsername(), Gathering.INDIVIDUAL).stream()
                .map(CalendarResponse::new)
                .collect(Collectors.toList());
    }

    public List<CalendarResponse> findClassCalendar() {
        User user = nowUser();
        return calendarRepository.findByGathering(user.getGathering()).stream()
                .map(CalendarResponse::new)
                .collect(Collectors.toList());
    }

    public List<CalendarResponse> findGeneralCalendar() {
        return calendarRepository.findByGathering(Gathering.GENERAL).stream()
                .map(CalendarResponse::new)
                .collect(Collectors.toList());
    }

    public void writeMyCalendar(CalendarRequest request) {
        User user = nowUser();
        calendarRepository.save(
                Calendar.builder()
                        .username(user.getUsername())
                        .gathering(Gathering.INDIVIDUAL)
                        .date(Date.valueOf(request.getDate()))
                        .title(request.getTitle())
                        .build()
        );
    }

    public void writeClassCalendar(CalendarRequest request) {
        User user = nowUser();
        calendarRepository.save(
                Calendar.builder()
                        .username(user.getUsername())
                        .gathering(user.getGathering())
                        .date(Date.valueOf(request.getDate()))
                        .title(request.getTitle())
                        .build()
        );
    }

    public void writeGeneralCalendar(CalendarRequest request) {
        User user = nowUser();
        calendarRepository.save(
                Calendar.builder()
                        .username(user.getUsername())
                        .gathering(Gathering.GENERAL)
                        .date(Date.valueOf(request.getDate()))
                        .title(request.getTitle())
                        .build()
        );
    }

    public User nowUser(){
        return SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findByUsername)
                .orElseThrow(InvalidTokenException::new);
    }
}