package com.e_civil.e_civil.controllers;

import com.e_civil.e_civil.models.Demande;
import com.e_civil.e_civil.services.DemandeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("demande")
@AllArgsConstructor
public class DemandeController {
    private DemandeService demandeService;

    @GetMapping
    public List<Demande> getAllDemandes() {
        return demandeService.findAll();
    }

    @GetMapping("{id}")
    public Optional<Demande> getDemandeById(@PathVariable Long id) {
        return demandeService.findById(id);
    }

    @PostMapping
    public Demande createDemande(@RequestBody Demande demande) {
        return demandeService.create(demande);
    }

    @PatchMapping("{id}")
    public Demande updateDemande(@PathVariable Long id, @RequestBody Demande demande) {
        return demandeService.update(demande, id);
    }

    @DeleteMapping("{id}")
    public void deleteDemande(@PathVariable Long id) {
     demandeService.delete(id);
    }
}
