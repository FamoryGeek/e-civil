package com.e_civil.e_civil.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Setter
@Getter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("CITOYEN")
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
