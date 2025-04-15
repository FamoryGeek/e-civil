package com.e_civil.e_civil.repositories;

import com.e_civil.e_civil.models.Policier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicierRepository extends JpaRepository<Policier, Long> {
}
