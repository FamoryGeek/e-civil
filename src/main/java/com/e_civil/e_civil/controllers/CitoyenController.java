package com.e_civil.e_civil.controllers;

import com.e_civil.e_civil.models.Citoyen;
import com.e_civil.e_civil.services.CitoyenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("citoyen")
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
    public Citoyen createCitoyen(@RequestBody Citoyen citoyen){
        return citoyenService.create(citoyen);
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
