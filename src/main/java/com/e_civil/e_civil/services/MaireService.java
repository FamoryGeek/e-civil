package com.e_civil.e_civil.services;

import com.e_civil.e_civil.models.Maire;
import com.e_civil.e_civil.repositories.MaireRepository;
import com.e_civil.e_civil.repositories.ProcureurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MaireService {
    private MaireRepository maireRepository;

    public Maire create(Maire maire){
        return maireRepository.save(maire);
    }

    public List<Maire> findAll(){
        return maireRepository.findAll();
    }

    public Optional<Maire> findById(Long id){
        return maireRepository.findById(id);
    }

    public Maire update(Maire maire, Long id){
        Optional<Maire> maireOptional = maireRepository.findById(id);
        if(maireOptional.isPresent()){
            Maire maireUpdate = maireOptional.get();
            maireUpdate.setId(id);
            maireUpdate.setNom(maire.getNom());
            maireUpdate.setPrenom(maire.getPrenom());
            maireUpdate.setMairie(maire.getMairie());
            maireUpdate.setEmail(maire.getEmail());
            maireUpdate.setPassword(maire.getPassword());
            maireUpdate.setRole(maire.getRole());

            return maireRepository.save(maireUpdate);
        }
        return null;
    }

    public void delete(Long id){
        Optional<Maire> maireOptional = maireRepository.findById(id);
        maireOptional.ifPresent(maire -> maireRepository.delete(maire));
    }
}
