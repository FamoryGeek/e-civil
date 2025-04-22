package com.e_civil.e_civil.controllers;

import com.e_civil.e_civil.dto.AdminResponse;
import com.e_civil.e_civil.dto.ProcureurRequest;
import com.e_civil.e_civil.dto.ProcureurResponse;
import com.e_civil.e_civil.models.Procureur;
import com.e_civil.e_civil.services.ProcureurService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/procureur")
@CrossOrigin("*")
@AllArgsConstructor
public class ProcureurController {
    private ProcureurService procureurService;

    @GetMapping
    public List<Procureur> getAllProcureurs() {
        return procureurService.findAll();
    }

    @GetMapping("{id}")
    public Optional<Procureur> getProcureurById(@PathVariable Long id) {
        return procureurService.findById(id);
    }

    @PostMapping
    public ResponseEntity<ProcureurResponse> createProcureur(@Valid @RequestBody ProcureurRequest procureurRequest) {
        try {
            ProcureurResponse response = procureurService.create(procureurRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            ProcureurResponse errorResponse = ProcureurResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PatchMapping("{id}")
    public Procureur updateProcureur(@PathVariable Long id, @RequestBody Procureur procureur) {
        return procureurService.update(procureur, id);
    }

    @DeleteMapping("{id}")
    public void deleteProcureur(@PathVariable Long id) {
        procureurService.delete(id);
    }

}
