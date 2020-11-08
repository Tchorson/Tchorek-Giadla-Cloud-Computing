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
@AllArgsConstructor
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

    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(name = "tornadolvl")
    private Category tornadoLvl;

    public TornadoState() {
        this.date = new Timestamp(System.currentTimeMillis());
        this.location = "Sample";
        this.dTemp = 0.0f;
        this.windSpeed = 0.0f;
        tornadoLvl = Category.F1;
    }
}
