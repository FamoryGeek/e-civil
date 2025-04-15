package com.e_civil.e_civil.repositories;

import com.e_civil.e_civil.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}
