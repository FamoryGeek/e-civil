package com.e_civil.e_civil.services;

import com.e_civil.e_civil.Enum.EnumRole;
import com.e_civil.e_civil.dto.AdminRequest;
import com.e_civil.e_civil.dto.AdminResponse;
import com.e_civil.e_civil.dto.RegisterResponse;
import com.e_civil.e_civil.models.Admin;
import com.e_civil.e_civil.models.Citoyen;
import com.e_civil.e_civil.models.Utilisateur;
import com.e_civil.e_civil.repositories.AdminRepository;
import com.e_civil.e_civil.repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {
    private AdminRepository adminRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;


    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Optional<Admin> findById(Long id) {
        return adminRepository.findById(id);
    }

    @Transactional
    public AdminResponse create(AdminRequest adminRequest) {
        if (utilisateurRepository.existsByEmail(adminRequest.getEmail())) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }
        Utilisateur utilisateur;
        if(adminRequest.getRole() == EnumRole.ADMIN){
            Admin admin = new Admin();
            admin.setNom(adminRequest.getNom());
            admin.setPrenom(adminRequest.getPrenom());
            admin.setEmail(adminRequest.getEmail());
            admin.setPassword(passwordEncoder.encode(adminRequest.getPassword()));
            admin.setRole(EnumRole.ADMIN);
            utilisateur = admin;
        }else {
            throw new RuntimeException("Cet role n'est pas permit");
        }
        utilisateur = utilisateurRepository.save(utilisateur);
        return AdminResponse.builder()
                .id(utilisateur.getId())
                .email(utilisateur.getEmail())
                .role(utilisateur.getRole())
                .success(true)
                .message("Admin creer avec success")
                .build();
    }
    public Admin update(Admin admin,Long id) {
        Optional<Admin> adminOptional = adminRepository.findById(id);
        if (adminOptional.isPresent()) {
            Admin adminUpdate = adminOptional.get();
            adminUpdate.setNom(admin.getNom());
            adminUpdate.setPrenom(admin.getPrenom());
            adminUpdate.setEmail(admin.getEmail());
            adminUpdate.setPassword(passwordEncoder.encode(admin.getPassword()));
            adminUpdate.setRole(admin.getRole());
            return adminRepository.save(adminUpdate);
        }
        return null;
    }

    public void  delete(Long id) {
        Optional<Admin> adminOptional = adminRepository.findById(id);
        adminOptional.ifPresent(admin -> adminRepository.delete(admin));
    }

}
