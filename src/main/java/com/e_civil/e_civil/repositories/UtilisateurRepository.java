package com.e_civil.e_civil.repositories;

import com.e_civil.e_civil.Enum.EnumRole;
import com.e_civil.e_civil.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmail(String email);
    Optional<Utilisateur> findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    List<Utilisateur> findByRole(EnumRole role);
}

