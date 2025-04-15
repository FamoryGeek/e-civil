package com.e_civil.e_civil.services;

import com.e_civil.e_civil.models.Policier;
import com.e_civil.e_civil.repositories.PolicierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicierService {

    @Autowired
    private PolicierRepository policierRepository;

    public List<Policier> getAllPoliciers() {
        return policierRepository.findAll();
    }

    public Optional<Policier> getPolicierById(Long id) {
        return policierRepository.findById(id);
    }

    public Policier createPolicier(Policier policier) {
        return policierRepository.save(policier);
    }

    public Policier updatePolicier(Long id, Policier updatedPolicier) {
        return policierRepository.findById(id).map(policier -> {
            policier.setNom(updatedPolicier.getNom());
            policier.setPrenom(updatedPolicier.getPrenom());
            policier.setArrondissement(updatedPolicier.getArrondissement());
            return policierRepository.save(policier);
        }).orElse(null);
    }

    public void deletePolicier(Long id) {
        policierRepository.deleteById(id);
    }
}
