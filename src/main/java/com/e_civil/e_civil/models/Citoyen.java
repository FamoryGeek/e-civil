package com.e_civil.e_civil.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Citoyen extends Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String nina;

    @ManyToMany
    @JoinTable(
            name = "citoyen_pv",
            joinColumns = @JoinColumn(name = "citoyen_id"),
            inverseJoinColumns = @JoinColumn(name = "pv_id")
    )
    private List<Pv> pvs;





}
