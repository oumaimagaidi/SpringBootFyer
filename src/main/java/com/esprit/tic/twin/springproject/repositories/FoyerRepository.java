package com.esprit.tic.twin.springproject.repositories;

import com.esprit.tic.twin.springproject.entities.Foyer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoyerRepository extends JpaRepository<Foyer, Long> {
    List<Foyer> findByUniversiteNomUniversite(String nom);
    List<Foyer> findByBlocsNomBloc(String s);
    List<Foyer> findByUniversiteNomUniversiteAndBlocsNomBloc(String nom1, String nom2);
}
