package com.project.wouldyou.controller.dto.response;

import com.project.wouldyou.domain.Constellation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ConstellationResponse {

    private List<ConstellationDto> result;

    public void addConstellation(Constellation constellation){

        ConstellationDto constellationDto = ConstellationDto
                .builder()
                .name(constellation.getName())
                .scientificName(constellation.getScientificName())
                .englishName(constellation.getEnglishName())
                .location(constellation.getLocation())
                .culmination(constellation.getCulmination())
                .description(constellation.getDescription())
                .image(constellation.getImage())
                .build();
        result.add(constellationDto);
    }

    @Getter
    @Builder
    @AllArgsConstructor
    private static class ConstellationDto {
        private String name;
        private String scientificName;
        private String englishName;
        private String location;
        private String culmination;
        private String description;
        private String image;
    }
}