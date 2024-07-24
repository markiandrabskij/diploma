package com.example.diploma.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Stone implements GetIdAndName{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int pricePerCarat;
    private int weight;
    double transparency;
    int posInNecklace;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private StoneType type;

    @ManyToOne
    @JoinColumn(name = "necklace_id")
    private Necklace necklace;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stone stone = (Stone) o;
        return pricePerCarat == stone.pricePerCarat && weight == stone.weight && Double.compare(stone.transparency, transparency) == 0 && posInNecklace == stone.posInNecklace && Objects.equals(name, stone.name) && Objects.equals(type, stone.type) && Objects.equals(necklace, stone.necklace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pricePerCarat, weight, transparency, posInNecklace, type, necklace);
    }
}
