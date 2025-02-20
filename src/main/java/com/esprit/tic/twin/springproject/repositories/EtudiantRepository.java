package com.esprit.tic.twin.springproject.repositories;

import com.esprit.tic.twin.springproject.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Etudiant findByNomEtAndPrenomEt(String nomEt, String prenomEt);
    Etudiant findByCin(long cin);
}
