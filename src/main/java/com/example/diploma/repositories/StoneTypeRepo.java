package com.example.diploma.repositories;

import com.example.diploma.models.StoneType;
import com.example.diploma.models.StoneType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoneTypeRepo extends JpaRepository<StoneType, Long> {
}
