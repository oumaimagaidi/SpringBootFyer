package com.esprit.tic.twin.springproject.repositories;

import com.esprit.tic.twin.springproject.entities.Bloc;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlocRepository extends JpaRepository<Bloc, Long> {
    List<Bloc> findByFoyerUniversiteNomUniversite(String s);

    @Query("SELECT b FROM Bloc b WHERE b.foyer.universite.nomUniversite = :name")
    List<Bloc> retrieveBlocs(@Param("name") String s);

    Bloc findByNomBloc(String s);
}
