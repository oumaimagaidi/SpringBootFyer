package com.esprit.tic.twin.springproject.services;

import com.esprit.tic.twin.springproject.entities.Tache;
import java.util.List;

public interface ITacheService {
    List<Tache> retrieveAllTaches();
    Tache addTache(Tache t);
    Tache updateTache(Tache t);
    Tache retrieveTache(Long idTache);
    void removeTache(Long idTache);
    List<Tache> addTasksAndAffectToEtudiant(List<Tache> taches, String nomEt, String prenomEt);
}
