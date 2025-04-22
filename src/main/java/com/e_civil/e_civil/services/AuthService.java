package com.e_civil.e_civil.services;


import com.e_civil.e_civil.Enum.EnumRole;
import com.e_civil.e_civil.dto.LoginRequest;
import com.e_civil.e_civil.dto.LoginResponse;
import com.e_civil.e_civil.dto.RegisterRequest;
import com.e_civil.e_civil.dto.RegisterResponse;
import com.e_civil.e_civil.models.*;
import com.e_civil.e_civil.repositories.UtilisateurRepository;
import com.e_civil.e_civil.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        Utilisateur utilisateur = utilisateurRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return LoginResponse.builder()
                .token(jwt)
                .user(utilisateur)
                .role(utilisateur.getRole())
                .build();
    }

    @Transactional
    public RegisterResponse register(RegisterRequest registerRequest) {
        // Vérifier si l'email existe déjà
        if (utilisateurRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }

        // Créer l'utilisateur en fonction du rôle
        Utilisateur utilisateur;

        switch (registerRequest.getRole()) {
            case CITOYEN:
                Citoyen citoyen = new Citoyen();
                citoyen.setNom(registerRequest.getNom());
                citoyen.setPrenom(registerRequest.getPrenom());
                citoyen.setEmail(registerRequest.getEmail());
                citoyen.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
                citoyen.setRole(EnumRole.CITOYEN);
                citoyen.setNina(registerRequest.getNina());
                utilisateur = citoyen;
                break;

            case MAIRE:
                Maire maire = new Maire();
                maire.setNom(registerRequest.getNom());
                maire.setPrenom(registerRequest.getPrenom());
                maire.setEmail(registerRequest.getEmail());
                maire.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
                maire.setRole(EnumRole.MAIRE);
                maire.setMairie(registerRequest.getMairie());
                utilisateur = maire;
                break;

            case PROCUREUR:
                Procureur procureur = new Procureur();
                procureur.setNom(registerRequest.getNom());
                procureur.setPrenom(registerRequest.getPrenom());
                procureur.setEmail(registerRequest.getEmail());
                procureur.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
                procureur.setRole(EnumRole.PROCUREUR);
                procureur.setTribunal(registerRequest.getTribunal());
                utilisateur = procureur;
                break;

            case POLICIER:
                Policier policier = new Policier();
                policier.setNom(registerRequest.getNom());
                policier.setPrenom(registerRequest.getPrenom());
                policier.setEmail(registerRequest.getEmail());
                policier.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
                policier.setRole(EnumRole.POLICIER);
                policier.setArrondissement(registerRequest.getArrondissement());
                utilisateur = policier;
                break;

            default:
                throw new RuntimeException("Rôle non supporté");
        }

        // Sauvegarder l'utilisateur
        utilisateur = utilisateurRepository.save(utilisateur);

        // Retourner la réponse
        return RegisterResponse.builder()
                .id(utilisateur.getId())
                .email(utilisateur.getEmail())
                .role(utilisateur.getRole())
                .success(true)
                .message("Inscription réussie")
                .build();
    }
}

