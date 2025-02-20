package com.esprit.tic.twin.springproject.repositories;

import com.esprit.tic.twin.springproject.entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface TacheRepository extends JpaRepository<Tache, Long> {
    @Query("select sum(t.tarifHoraire * t.duree) from Tache t where t.dateTache between :t1 and :t2 and t.etudiantOrdinaire.idEtudiant=:idEtudiant")
    Float sommeTacheAnneeEncours(@Param("t1") LocalDate t1, @Param("t2") LocalDate t2, @Param("idEtudiant") Long idEtudiant);
}
