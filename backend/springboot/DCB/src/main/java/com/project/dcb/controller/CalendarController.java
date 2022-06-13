package com.project.dcb.controller;

import com.project.dcb.dto.request.CalendarRequest;
import com.project.dcb.dto.response.CalendarResponse;
import com.project.dcb.dto.response.ResultListResponse;
import com.project.dcb.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    @PostMapping("/user/calendar") //개인 일정 생성
    public ResponseEntity<Object> writeMyCalendar(@RequestBody CalendarRequest request){
        calendarService.writeMyCalendar(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/manager/calendar") //학급 일정 생성
    public ResponseEntity<Object> writeClassCalendar(@RequestBody CalendarRequest request){
        calendarService.writeClassCalendar(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/admin/calendar") //전체 일정 생성
    public ResponseEntity<Object> writeGeneralCalendar(@RequestBody CalendarRequest request){
        calendarService.writeGeneralCalendar(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/user/calendar") //개인 일정 조회
    public ResponseEntity<Object> findMyCalendar(){
        return new ResponseEntity<>
                (new ResultListResponse<>(calendarService.findMyCalendar()), HttpStatus.OK);
    }

    @GetMapping("/user/calendar/class") //학급 일정 조회
    public ResponseEntity<Object> findClassCalendar(){
        return new ResponseEntity<>
                (new ResultListResponse<>(calendarService.findClassCalendar()), HttpStatus.OK);
    }

    @GetMapping("/calendar") //전체 일정 조회
    public ResponseEntity<Object> findGeneralCalendar(HttpServletResponse response){
        return new ResponseEntity<>
                (new ResultListResponse<>(calendarService.findGeneralCalendar()), HttpStatus.OK);
    }

}