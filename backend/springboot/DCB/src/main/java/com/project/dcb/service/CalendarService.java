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

    //일정 조회
    public List<CalendarResponse> findMyCalendar() {
        User user = currentUser();
        return findCalendar(calendarRepository.findByNameAndGathering(user.getUsername(), Gathering.INDIVIDUAL));
    }

    public List<CalendarResponse> findClassCalendar() {
        User user = currentUser();
        return findCalendar(calendarRepository.findByGathering(user.getGathering()));
    }

    public List<CalendarResponse> findGeneralCalendar() {
        return findCalendar(calendarRepository.findByGathering(Gathering.GENERAL));
    }

    private List<CalendarResponse> findCalendar(List<Calendar> calendarRepository) {
        return calendarRepository.stream()
                .map(CalendarResponse::new)
                .collect(Collectors.toList());
    }

    //일정 생성
    public void writeMyCalendar(CalendarRequest request) {
        User user = currentUser();
        calendarRepository.save(new Calendar(request, Gathering.INDIVIDUAL, user));
    }

    public void writeClassCalendar(CalendarRequest request) {
        User user = currentUser();
        calendarRepository.save(new Calendar(request, user.getGathering(), user));
    }

    //현재 유저 가져오기
    public User currentUser() {
        return userRepository.findByUsername(SecurityUtil.getCurrentUsername())
                .orElseThrow(InvalidTokenException::new);
    }
}