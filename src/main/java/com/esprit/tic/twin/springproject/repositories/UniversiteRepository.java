package com.esprit.tic.twin.springproject.repositories;

import com.esprit.tic.twin.springproject.entities.Universite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniversiteRepository extends JpaRepository<Universite, Long> {
    Universite findByNomUniversite(String s);
}
