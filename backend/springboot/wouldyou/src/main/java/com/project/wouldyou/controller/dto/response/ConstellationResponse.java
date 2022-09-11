package com.project.wouldyou.controller.dto.response;

import com.project.wouldyou.domain.Constellation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Getter
@Builder
@AllArgsConstructor
public class ConstellationResponse {

    private String name;
    private String scientificName;
    private String englishName;
    private String location;
    private String culmination;
    private String midnightTime;
    private String description;
    private String image;

    public static ConstellationResponse of(LocalDateTime dateTime, Constellation constellation) {

        LocalDateTime culminationDatetime = constellation.getCulminationDatetime();
        LocalDateTime midnightTime = culminationDatetime.minusMinutes(dateTime.until(culminationDatetime, ChronoUnit.DAYS) * 4);

        return ConstellationResponse
                .builder()
                .name(constellation.getName())
                .scientificName(constellation.getScientificName())
                .englishName(constellation.getEnglishName())
                .location(constellation.getLocation())
                .culmination(constellation.getCulmination())
                .midnightTime(midnightTime.format(DateTimeFormatter.ofPattern("a HH:mm:ss")))
                .description(constellation.getDescription())
                .image(constellation.getImage())
                .build();
    }

    public static boolean midNightTimeInRange(LocalDateTime dateTime, Constellation constellation) {

        LocalDateTime culminationDatetime = constellation.getCulminationDatetime();
        LocalDateTime midnightTime = culminationDatetime.minusMinutes(dateTime.until(culminationDatetime, ChronoUnit.DAYS) * 4);

        return LocalTime.of(19, 0).isBefore(midnightTime.toLocalTime()) || midnightTime.toLocalTime().isBefore(LocalTime.of(2, 0));
    }
}