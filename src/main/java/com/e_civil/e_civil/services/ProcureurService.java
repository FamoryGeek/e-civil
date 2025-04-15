package com.e_civil.e_civil.services;

import com.e_civil.e_civil.models.Procureur;
import com.e_civil.e_civil.repositories.ProcureurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProcureurService {
    private ProcureurRepository procureurRepository;


    public Procureur create(Procureur procureur){
        return procureurRepository.save(procureur);
    }

    public List<Procureur> findAll(){
        return procureurRepository.findAll();
    }

    public Optional<Procureur> findById(Long id){
        return procureurRepository.findById(id);
    }

    public Procureur update(Procureur procureur, Long id){
        Optional<Procureur> procureurOptional = procureurRepository.findById(id);
        if(procureurOptional.isPresent()){
            Procureur procureurUpdate = procureurOptional.get();
            procureurUpdate.setId(id);
            procureurUpdate.setNom(procureur.getNom());
            procureurUpdate.setPrenom(procureur.getPrenom());
            procureurUpdate.setTribunal(procureur.getTribunal());
            procureurUpdate.setEmail(procureur.getEmail());
            procureurUpdate.setPassword(procureur.getPassword());
            procureurUpdate.setRole(procureur.getRole());
            return procureurRepository.save(procureurUpdate);

        }
        return null;
    }

    public void delete(Long id){
        Optional<Procureur> procureurOptional = procureurRepository.findById(id);
        procureurOptional.ifPresent((procureur -> procureurRepository.delete(procureur)));
    }
}
