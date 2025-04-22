package com.e_civil.e_civil.services;

import com.e_civil.e_civil.Enum.EnumRole;
import com.e_civil.e_civil.dto.MaireRequest;
import com.e_civil.e_civil.dto.MaireResponse;
import com.e_civil.e_civil.models.Maire;
import com.e_civil.e_civil.models.Utilisateur;
import com.e_civil.e_civil.repositories.MaireRepository;
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
public class MaireService {
    private MaireRepository maireRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MaireResponse create(MaireRequest maireRequest){
        if (utilisateurRepository.existsByEmail(maireRequest.getEmail())) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }
        Utilisateur utilisateur;
        if(maireRequest.getRole() == EnumRole.MAIRE){
            Maire maire = new Maire();
            maire.setNom(maireRequest.getNom());
            maire.setPrenom(maireRequest.getPrenom());
            maire.setEmail(maireRequest.getEmail());
            maire.setPassword(passwordEncoder.encode(maireRequest.getPassword()));
            maire.setRole(EnumRole.MAIRE);
            utilisateur = maire;
        }else {
            throw new RuntimeException("Cet role n'est pas permit");
        }
        utilisateur = utilisateurRepository.save(utilisateur);
        return MaireResponse.builder()
                .id(utilisateur.getId())
                .email(utilisateur.getEmail())
                .role(utilisateur.getRole())
                .success(true)
                .message("Maire creer avec success")
                .build();
    }

    public List<Maire> findAll(){
        return maireRepository.findAll();
    }

    public Optional<Maire> findById(Long id){
        return maireRepository.findById(id);
    }

    public Maire update(Maire maire, Long id){
        Optional<Maire> maireOptional = maireRepository.findById(id);
        if(maireOptional.isPresent()){
            Maire maireUpdate = maireOptional.get();
            maireUpdate.setId(id);
            maireUpdate.setNom(maire.getNom());
            maireUpdate.setPrenom(maire.getPrenom());
            maireUpdate.setMairie(maire.getMairie());
            maireUpdate.setEmail(maire.getEmail());
            maireUpdate.setPassword(passwordEncoder.encode(maire.getPassword()));
            maireUpdate.setRole(maire.getRole());

            return maireRepository.save(maireUpdate);
        }
        return null;
    }

    public void delete(Long id){
        Optional<Maire> maireOptional = maireRepository.findById(id);
        maireOptional.ifPresent(maire -> maireRepository.delete(maire));
    }
}
