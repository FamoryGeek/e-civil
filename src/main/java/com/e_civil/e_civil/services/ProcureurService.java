package com.e_civil.e_civil.services;

import com.e_civil.e_civil.Enum.EnumRole;
import com.e_civil.e_civil.dto.AdminResponse;
import com.e_civil.e_civil.dto.ProcureurRequest;
import com.e_civil.e_civil.dto.ProcureurResponse;
import com.e_civil.e_civil.models.Admin;
import com.e_civil.e_civil.models.Procureur;
import com.e_civil.e_civil.models.Utilisateur;
import com.e_civil.e_civil.repositories.ProcureurRepository;
import com.e_civil.e_civil.repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProcureurService {
    private ProcureurRepository procureurRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public ProcureurResponse create(ProcureurRequest procureurRequest){
        if (utilisateurRepository.existsByEmail(procureurRequest.getEmail())) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }
        Utilisateur utilisateur;

            Procureur procureur = new Procureur();
            procureur.setNom(procureurRequest.getNom());
            procureur.setPrenom(procureurRequest.getPrenom());
            procureur.setEmail(procureurRequest.getEmail());
            procureur.setTribunal(procureurRequest.getTribunal());
            procureur.setPassword(passwordEncoder.encode(procureurRequest.getPassword()));
            procureur.setRole(EnumRole.PROCUREUR);
            utilisateur = procureur;

        utilisateur = utilisateurRepository.save(utilisateur);
        return ProcureurResponse.builder()
                .id(utilisateur.getId())
                .email(utilisateur.getEmail())
                .role(utilisateur.getRole())
                .success(true)
                .message("Procureur creer avec success")
                .build();
    }

    public List<Procureur> findAll(){
        return procureurRepository.findAll();
    }

    public Optional<Procureur> findById(Long id){
        return procureurRepository.findById(id);
    }

    public Procureur update(Procureur procureur, Long id){
        Optional<Procureur> procureurOptional = procureurRepository.findById(id);
        if(procureurOptional.isPresent()){
            Procureur procureurUpdate = procureurOptional.get();
            procureurUpdate.setId(id);
            procureurUpdate.setNom(procureur.getNom());
            procureurUpdate.setPrenom(procureur.getPrenom());
            procureurUpdate.setTribunal(procureur.getTribunal());
            procureurUpdate.setEmail(procureur.getEmail());
            procureurUpdate.setPassword(passwordEncoder.encode(procureur.getPassword()));
            procureurUpdate.setRole(procureur.getRole());
            return procureurRepository.save(procureurUpdate);

        }
        return null;
    }

    public void delete(Long id){
        Optional<Procureur> procureurOptional = procureurRepository.findById(id);
        procureurOptional.ifPresent((procureur -> procureurRepository.delete(procureur)));
    }
}
