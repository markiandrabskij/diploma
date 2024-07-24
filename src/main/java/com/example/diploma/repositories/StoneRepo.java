package com.example.diploma.repositories;

import com.example.diploma.models.Necklace;
import com.example.diploma.models.Stone;
import com.example.diploma.models.Necklace;
import com.example.diploma.models.Stone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StoneRepo extends JpaRepository<Stone, Long> {
    Page<Stone> findAll(Pageable pageable);
    Page<Stone> findAllByNecklaceIsNull(Pageable pageable);
    Page<Stone> getStoneByNecklaceAndTransparencyBetweenOrderByPosInNecklace(Necklace necklace, double transparency, double transparency2, Pageable pageable);
    Set<Stone> getStonesByNecklace (Necklace necklace);
}
