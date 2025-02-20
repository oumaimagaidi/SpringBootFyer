package com.esprit.tic.twin.springproject.services;

import com.esprit.tic.twin.springproject.entities.Etudiant;

import java.util.HashMap;
import java.util.List;

public interface IEtudiantService {
    List<Etudiant> retrieveAllEtudiants();
    Etudiant addEtudiant(Etudiant e);
    Etudiant updateEtudiant(Etudiant e);
    Etudiant retrieveEtudiant(Long idEtudiant);
    void removeEtudiant(Long idEtudiant);
    Etudiant affecterEtudiantAReservation(String nomEt, String prenomEt, String idReservation);
    HashMap<String, Float> calculNouveauMontantInscriptionDesEtudiants();
}
