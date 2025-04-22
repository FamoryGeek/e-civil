package com.e_civil.e_civil.controllers;

import com.e_civil.e_civil.dto.MaireRequest;
import com.e_civil.e_civil.dto.MaireResponse;
import com.e_civil.e_civil.models.Maire;
import com.e_civil.e_civil.services.MaireService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/maire")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class MaireController {
    private MaireService maireService;

    @GetMapping
    public List<Maire> getAllMaires() {
        return maireService.findAll();
    }

    @GetMapping("{id}")
    public Optional<Maire> getMaireById(@PathVariable Long id) {
        return maireService.findById(id);
    }

    @PostMapping
    public ResponseEntity<MaireResponse> createMaire(@Valid @RequestBody MaireRequest maireRequest) {
        try {
            MaireResponse response = maireService.create(maireRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            MaireResponse errorResponse = MaireResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PatchMapping("{id}")
    public Maire updateMaire(@RequestBody Maire maire, @PathVariable Long id) {
        return maireService.update(maire, id);
    }

    @DeleteMapping("{id}")
    public void deleteMaire(@PathVariable Long id) {
        maireService.delete(id);
    }
}
