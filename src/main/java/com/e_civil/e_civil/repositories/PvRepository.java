package com.e_civil.e_civil.repositories;

import com.e_civil.e_civil.models.Pv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PvRepository extends JpaRepository<Pv, Long> {

    List<Pv> findByPolicierId(Long policierId);
}
