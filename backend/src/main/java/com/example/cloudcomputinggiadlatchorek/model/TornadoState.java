package com.example.cloudcomputinggiadlatchorek.model;

import com.example.cloudcomputinggiadlatchorek.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tornadostate")
@AllArgsConstructor
@IdClass(TornadoStateCompositeKey.class)
public class TornadoState {
    @Id
    @NonNull
    private Timestamp date;

    @Id
    @NonNull
    private String location;

    @NonNull
    private Float dTemp;

    @NonNull
    private Float windSpeed;

    @Enumerated(EnumType.STRING)
    @NonNull
    private Category tornadoLvl;

    public TornadoState() {
        this.date = new Timestamp(System.currentTimeMillis());
        this.location = "Sample";
        this.dTemp = 0.0f;
        this.windSpeed = 0.0f;
        tornadoLvl = Category.F1;
    }
}
