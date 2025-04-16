package com.e_civil.e_civil.repositories;

import com.e_civil.e_civil.models.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeRepository extends JpaRepository<Demande, Long> {
}
