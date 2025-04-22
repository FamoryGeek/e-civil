package com.e_civil.e_civil.services;

import com.e_civil.e_civil.models.Citoyen;
import com.e_civil.e_civil.models.Utilisateur;
import com.e_civil.e_civil.repositories.CitoyenRepository;
import com.e_civil.e_civil.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    private CitoyenRepository citoyenRepository;

    public Optional<Utilisateur> login(String email, String password) {
        return utilisateurRepository.findByEmailAndPassword(email, password);
    }

    public Optional<Utilisateur> findByEmail(String email) {
        return utilisateurRepository.findByEmail(email); // Le repo doit avoir cette méthode
    }

    public Utilisateur save(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Citoyen saveCitoyen(Citoyen citoyen) {
        return citoyenRepository.save(citoyen);
    }

}
