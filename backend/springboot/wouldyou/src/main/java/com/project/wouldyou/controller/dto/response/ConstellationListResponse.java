package com.project.wouldyou.controller.dto.response;

import com.project.wouldyou.domain.Constellation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ConstellationListResponse {

    private List<ConstellationResponse> result;

    public void addConstellation(LocalDateTime dateTime, Constellation constellation) {
        result.add(ConstellationResponse.of(dateTime, constellation));
    }

    public void sort() {
        result.sort(Comparator.comparing(ConstellationResponse::getMidnightTime));

        while(result.get(0).getMidnightTime().startsWith("오전")||result.get(0).getMidnightTime().startsWith("AM")) {

            ConstellationResponse constellation = result.get(0);
            result.remove(0);
            result.add(constellation);
        }
    }
}