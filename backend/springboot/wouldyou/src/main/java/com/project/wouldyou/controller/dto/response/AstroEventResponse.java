package com.project.wouldyou.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AstroEventResponse {

    private List<AstroEventDto> result;

    public void addEvent(String astroEvent, String astroTitle){
        AstroEventDto astroEvent1 = new AstroEventDto(astroEvent, astroTitle);
        result.add(astroEvent1);
    }

    @Getter
    @AllArgsConstructor
    private static class AstroEventDto {
        String astroEvent;
        String astroTitle;
    }

}