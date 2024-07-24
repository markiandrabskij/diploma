package com.example.diploma.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Necklace implements GetIdAndName{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int nextStonePos = 0;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "necklace", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Stone> stones = new LinkedHashSet<>();
}
