package com.example.cloudcomputinggiadlatchorek.model;

import com.example.cloudcomputinggiadlatchorek.TornadoCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tornadostate")
@IdClass(TornadoState.class)
public class TornadoState implements Serializable {

    @Id
    @NonNull
    @Column(name = "datemeasure")
    private Timestamp date;

    @Id
    @NonNull
    @Column(name = "location")
    private String location;

    @NonNull
    @Column(name = "dtemp")
    private Double dTemp;

    @NonNull
    @Column(name = "windspeed")
    private Double windSpeed;

    @NonNull
    @Column(name = "latitude")
    private Double latitude;

    @NonNull
    @Column(name = "longitude")
    private Double longitude;
    
    @NonNull
    @Column(name = "humidity")
    private Double humidity;

    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(name = "tornadolvl")
    private TornadoCategory tornadoLvl;

    public TornadoState() {
        this.date = new Timestamp(System.currentTimeMillis());
        this.location = "";
        this.latitude = 0.0;
        this.longitude= 0.0;
        this.dTemp = 0.0;
        this.windSpeed = 0.0;
        this.humidity = 0.0;
        tornadoLvl = TornadoCategory.F0;
    }

    public TornadoState(String location, Double latitude, Double longitude, Double delta, Double wind, Double humidity) {
        this.date = new Timestamp(System.currentTimeMillis());
        this.location = location;
        this.latitude = latitude;
        this.longitude= longitude;
        this.dTemp = delta;
        this.windSpeed = wind;
        this.humidity = humidity;
        tornadoLvl = TornadoCategory.F0;
    }

}
