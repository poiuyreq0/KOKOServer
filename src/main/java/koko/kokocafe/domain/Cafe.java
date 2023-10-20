package koko.kokocafe.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter @Setter
public class Cafe {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private Coordinate coordinate;

    @ElementCollection
    private List<Long> positions = new ArrayList<>();

    @ElementCollection
    private Map<String, Long> benefits = new HashMap<>();

    public Cafe() {

    }

    public Cafe(String name, Coordinate coordinate, List<Long> positions, Map<String, Long> benefits) {
        this.name = name;
        this.coordinate = coordinate;
        this.positions = positions;
        this.benefits = benefits;
    }
}
