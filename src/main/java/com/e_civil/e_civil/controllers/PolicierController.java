package com.e_civil.e_civil.controllers;

import com.e_civil.e_civil.dto.PolicierRequest;
import com.e_civil.e_civil.dto.PolicierResponse;
import com.e_civil.e_civil.models.Policier;
import com.e_civil.e_civil.services.PolicierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policier")
@CrossOrigin("*")
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
    public ResponseEntity<PolicierResponse> createPolicier(@Valid @RequestBody PolicierRequest policierRequest) {
        try {
            PolicierResponse response = policierService.createPolicier(policierRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            PolicierResponse errorResponse = PolicierResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
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
