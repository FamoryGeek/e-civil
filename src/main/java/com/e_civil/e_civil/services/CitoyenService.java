package com.e_civil.e_civil.services;

import com.e_civil.e_civil.Enum.EnumRole;
import com.e_civil.e_civil.dto.CitoyenRequest;
import com.e_civil.e_civil.dto.CitoyenResponse;
import com.e_civil.e_civil.models.Citoyen;
import com.e_civil.e_civil.models.Utilisateur;
import com.e_civil.e_civil.repositories.CitoyenRepository;
import com.e_civil.e_civil.repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CitoyenService {

    private CitoyenRepository citoyenRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CitoyenResponse create(CitoyenRequest citoyenRequest){
        if (utilisateurRepository.existsByEmail(citoyenRequest.getEmail())) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }
        Utilisateur utilisateur;
            Citoyen citoyen = new Citoyen();
            citoyen.setNom(citoyenRequest.getNom());
            citoyen.setPrenom(citoyenRequest.getPrenom());
            citoyen.setEmail(citoyenRequest.getEmail());
            citoyen.setNina(citoyenRequest.getNina());
            citoyen.setPassword(passwordEncoder.encode(citoyenRequest.getPassword()));
            citoyen.setRole(EnumRole.CITOYEN);
            utilisateur = citoyen;

        utilisateur = utilisateurRepository.save(utilisateur);
        return CitoyenResponse.builder()
                .id(utilisateur.getId())
                .email(utilisateur.getEmail())
                .role(utilisateur.getRole())
                .success(true)
                .message("Citoyen creer avec success")
                .build();
    }

    public List<Citoyen> findAll(){
        return citoyenRepository.findAll();
    }

    public Optional<Citoyen> findById(long id){
        return citoyenRepository.findById(id);
    }

    public Citoyen update(Citoyen citoyen, long id){
        Optional<Citoyen> citoyenOptional = citoyenRepository.findById(id);
        if(citoyenOptional.isPresent()){
            Citoyen citoyenUpdate = citoyenOptional.get();
            citoyenUpdate.setId(id);
            citoyenUpdate.setNom(citoyen.getNom());
            citoyenUpdate.setPrenom(citoyen.getPrenom());
            citoyenUpdate.setEmail(citoyen.getEmail());
            citoyenUpdate.setNina(citoyen.getNina());
            citoyenUpdate.setRole(citoyen.getRole());
            citoyenUpdate.setPassword(passwordEncoder.encode(citoyen.getPassword()));
            citoyenUpdate.setPvs(citoyen.getPvs());

            return citoyenRepository.save(citoyenUpdate);

        }
        return null;
    }

    public void deleteById(long id){
        Optional<Citoyen> citoyenOptional = citoyenRepository.findById(id);
        citoyenOptional.ifPresent((citoyen -> citoyenRepository.delete(citoyen)));
    }
}
