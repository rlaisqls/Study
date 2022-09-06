package com.project.wouldyou.controller;

import com.project.wouldyou.controller.dto.response.ConstellationResponse;
import com.project.wouldyou.domain.repository.ConstellationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
public class ConstellationController {

    private final ConstellationRepository constellationRepository;

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/ConstellationService/getConstellation")
    public ConstellationResponse constellation(@RequestParam Integer solMonth) {

        ConstellationResponse response = new ConstellationResponse(new ArrayList<>());

        if (solMonth < 4) {
            constellationRepository
                    .findBySeasonOrSeason("봄철 밤하늘 별자리", "북쪽 밤하늘 별자리")
                    .forEach(response::addConstellation);
        } else if (solMonth < 7) {
            constellationRepository
                    .findBySeasonOrSeason("여름철 밤하늘 별자리", "북쪽 밤하늘 별자리")
                    .forEach(response::addConstellation);
        } else if (solMonth < 10) {
            constellationRepository
                    .findBySeasonOrSeason("가을철 밤하늘 별자리", "북쪽 밤하늘 별자리")
                    .forEach(response::addConstellation);
        } else {
            constellationRepository
                    .findBySeasonOrSeason("겨울철 밤하늘 별자리", "북쪽 밤하늘 별자리")
                    .forEach(response::addConstellation);
        }

        return response;
    }

}