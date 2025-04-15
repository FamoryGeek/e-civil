package com.e_civil.e_civil.controllers;

import com.e_civil.e_civil.models.Policier;
import com.e_civil.e_civil.services.PolicierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policiers")
public class PolicierController {

    @Autowired
    private PolicierService policierService;

    @GetMapping
    public List<Policier> getAllPoliciers() {
        return policierService.getAllPoliciers();
    }

    @GetMapping("/{id}")
    public Policier getPolicierById(@PathVariable Long id) {
        return policierService.getPolicierById(id).orElse(null);
    }

    @PostMapping
    public Policier createPolicier(@RequestBody Policier policier) {
        return policierService.createPolicier(policier);
    }

    @PutMapping("/{id}")
    public Policier updatePolicier(@PathVariable Long id, @RequestBody Policier policier) {
        return policierService.updatePolicier(id, policier);
    }

    @DeleteMapping("/{id}")
    public void deletePolicier(@PathVariable Long id) {
        policierService.deletePolicier(id);
    }
}
