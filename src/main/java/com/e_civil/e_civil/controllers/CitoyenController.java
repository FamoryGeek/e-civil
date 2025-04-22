package com.e_civil.e_civil.controllers;

import com.e_civil.e_civil.dto.CitoyenRequest;
import com.e_civil.e_civil.dto.CitoyenResponse;
import com.e_civil.e_civil.models.Citoyen;
import com.e_civil.e_civil.services.CitoyenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/citoyen")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class CitoyenController {
    private CitoyenService citoyenService;

    @GetMapping
    public List<Citoyen> getAllCitoyen(){
        return citoyenService.findAll();
    }

    @GetMapping("{id}")
    public Optional<Citoyen> getCitoyenById(@PathVariable long id){
        return citoyenService.findById(id);
    }

    @PostMapping
    public ResponseEntity<CitoyenResponse> createCitoyen(@Valid @RequestBody CitoyenRequest citoyenRequest){
        try {
            CitoyenResponse response = citoyenService.create(citoyenRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            CitoyenResponse errorResponse = CitoyenResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PatchMapping("{id}")
    public Citoyen updateCitoyen(@RequestBody Citoyen citoyen,@PathVariable Long id){
            return  citoyenService.update(citoyen,id);
    }

    @DeleteMapping("{id}")
    public void deleteCitoyen(@PathVariable Long id){
        citoyenService.deleteById(id);
    }
}
