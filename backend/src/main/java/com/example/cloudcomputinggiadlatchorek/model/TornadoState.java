package com.example.cloudcomputinggiadlatchorek.model;

import com.example.cloudcomputinggiadlatchorek.Category;
import lombok.AllArgsConstructor;
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
    private Float dTemp;

    @NonNull
    @Column(name = "windspeed")
    private Float windSpeed;

    @NonNull
    @Column(name = "latitude")
    private Float latitude;

    @NonNull
    @Column(name = "longitude")
    private Float longitude;

    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(name = "tornadolvl")
    private Category tornadoLvl;

    public TornadoState() {
        this.date = new Timestamp(System.currentTimeMillis());
        this.location = "";
        this.latitude = 0.0F;
        this.longitude= 0.0F;
        this.dTemp = 0.0f;
        this.windSpeed = 0.0f;
        tornadoLvl = Category.F0;
    }

    public TornadoState(String location, Float latitude, Float longitude, Float delta, Float wind) {
        this.date = new Timestamp(System.currentTimeMillis());
        this.location = location;
        this.latitude = latitude;
        this.longitude= longitude;
        this.dTemp = delta;
        this.windSpeed = wind;
        tornadoLvl = Category.F0;
    }

}
