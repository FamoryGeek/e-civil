package com.e_civil.e_civil.services;

import com.e_civil.e_civil.Enum.EnumRole;
import com.e_civil.e_civil.dto.PolicierRequest;
import com.e_civil.e_civil.dto.PolicierResponse;
import com.e_civil.e_civil.models.Policier;
import com.e_civil.e_civil.models.Utilisateur;
import com.e_civil.e_civil.repositories.PolicierRepository;
import com.e_civil.e_civil.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PolicierService {

    @Autowired
    private  PolicierRepository policierRepository;
    @Autowired
    private  UtilisateurRepository utilisateurRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    public List<Policier> getAllPoliciers() {
        return policierRepository.findAll();
    }

    public Optional<Policier> getPolicierById(Long id) {
        return policierRepository.findById(id);
    }

    @Transactional
    public PolicierResponse createPolicier(PolicierRequest policierRequest) {
        if (utilisateurRepository.existsByEmail(policierRequest.getEmail())) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }
        Utilisateur utilisateur;

            Policier policier = new Policier();
            policier.setNom(policierRequest.getNom());
            policier.setPrenom(policierRequest.getPrenom());
            policier.setEmail(policierRequest.getEmail());
            policier.setArrondissement(policierRequest.getArrondissement());
            policier.setPassword(passwordEncoder.encode(policierRequest.getPassword()));
            policier.setRole(EnumRole.POLICIER);
            utilisateur = policier;

        utilisateur = utilisateurRepository.save(utilisateur);
        return PolicierResponse.builder()
                .id(utilisateur.getId())
                .email(utilisateur.getEmail())
                .role(utilisateur.getRole())
                .success(true)
                .message("Policier creer avec success")
                .build();
    }

    public Policier updatePolicier(Long id, Policier updatedPolicier) {
        return policierRepository.findById(id).map(policier -> {
            policier.setNom(updatedPolicier.getNom());
            policier.setPrenom(updatedPolicier.getPrenom());
            policier.setArrondissement(updatedPolicier.getArrondissement());
            policier.setEmail(updatedPolicier.getEmail());
            policier.setPassword(passwordEncoder.encode(updatedPolicier.getPassword()));
            policier.setRole(updatedPolicier.getRole());
            return policierRepository.save(policier);
        }).orElse(null);
    }

    public void deletePolicier(Long id) {
        policierRepository.deleteById(id);
    }
}
