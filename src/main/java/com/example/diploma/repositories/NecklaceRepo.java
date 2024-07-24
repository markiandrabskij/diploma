package com.example.diploma.repositories;

import com.example.diploma.models.Necklace;
import com.example.diploma.models.Necklace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NecklaceRepo extends JpaRepository<Necklace, Long> {
    Necklace getNecklaceById(long id);
    Page<Necklace> findAll(Pageable pageable);
}
