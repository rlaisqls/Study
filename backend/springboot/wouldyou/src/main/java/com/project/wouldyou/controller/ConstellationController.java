package com.project.wouldyou.controller;

import com.project.wouldyou.controller.dto.response.ConstellationListResponse;
import com.project.wouldyou.controller.dto.response.ConstellationResponse;
import com.project.wouldyou.domain.Constellation;
import com.project.wouldyou.domain.repository.ConstellationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
public class ConstellationController {

    private final ConstellationRepository constellationRepository;

    @CrossOrigin
    @GetMapping("/ConstellationService/getConstellation")
    public ConstellationListResponse constellation(@RequestParam Integer solMonth, @RequestParam Integer solDay) {

        ConstellationListResponse response = new ConstellationListResponse(new ArrayList<>());

        LocalDateTime solDateTime = LocalDateTime.now().withMonth(solMonth).withDayOfMonth(solDay);

        constellationRepository
                .findBy()
                .stream()
                .filter(c -> ConstellationResponse.midNightTimeInRange(solDateTime, c))
                .forEach(c -> response.addConstellation(solDateTime, c));

        response.sort();

        return response;
    }

    @CrossOrigin
    @GetMapping("/ConstellationService/getConstellation/one")
    public ConstellationResponse constellationOne(@RequestParam Integer solMonth, @RequestParam Integer solDay) {

        LocalDateTime solDateTime = LocalDateTime.now().withMonth(solMonth).withDayOfMonth(solDay);

        Constellation first = constellationRepository
                .findBy()
                .stream()
                .filter(c -> ConstellationResponse.midNightTimeInRange(solDateTime, c))
                .findFirst()
                .orElseThrow(IllegalStateException::new);

        return ConstellationResponse.of(solDateTime, first);
    }

    @CrossOrigin
    @GetMapping("/ConstellationService/getConstellation/name")
    public ConstellationResponse constellationByName(@RequestParam String name, @RequestParam Integer solMonth, @RequestParam Integer solDay) {

        Constellation constellation = constellationRepository.findByNameStartingWith(name);

        LocalDateTime solDateTime = LocalDateTime.now().withMonth(solMonth).withDayOfMonth(solDay).withHour(9).withMinute(0).withSecond(0).withNano(0);

        return ConstellationResponse.of(solDateTime, constellation);
    }

}