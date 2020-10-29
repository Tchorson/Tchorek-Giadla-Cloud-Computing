package com.example.cloudcomputinggiadlatchorek.model;

import com.example.cloudcomputinggiadlatchorek.Category;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TornadoState {
    private Double dTemp;
    private Double windSpeed;
    private Category tornadoLvl;
    private String location;
    private Long date;

    @JsonCreator
    public TornadoState(Double dTemp, Double windSpeed, Category tornadoLvl, String location, Long date) {
        this.dTemp = dTemp;
        this.windSpeed = windSpeed;
        this.tornadoLvl = tornadoLvl;
        this.location = location;
        this.date = date;
    }
}
