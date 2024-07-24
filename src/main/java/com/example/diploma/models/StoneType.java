package com.example.diploma.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class StoneType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(name = "rating_value")
    private int value;
    @OneToMany(mappedBy = "type")
    private List<Stone> stones;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoneType stoneType = (StoneType) o;
        return id == stoneType.id && value == stoneType.value && Objects.equals(name, stoneType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value);
    }
}
