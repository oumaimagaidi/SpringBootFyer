package com.esprit.tic.twin.springproject.services;

import com.esprit.tic.twin.springproject.entities.Etudiant;
import com.esprit.tic.twin.springproject.entities.Tache;
import com.esprit.tic.twin.springproject.entities.TypeEtudiant;
import com.esprit.tic.twin.springproject.repositories.EtudiantRepository;
import com.esprit.tic.twin.springproject.repositories.TacheRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TacheServiceImpl implements ITacheService{
    TacheRepository tacheRepository;
    EtudiantRepository etudiantRepository;

    @Override
    public List<Tache> retrieveAllTaches() {
        return tacheRepository.findAll();
    }

    @Override
    public Tache addTache(Tache t) {
        return tacheRepository.save(t);
    }

    @Override
    public Tache updateTache(Tache t) {
        return tacheRepository.save(t);
    }

    @Override
    public Tache retrieveTache(Long idTache) {
        return tacheRepository.findById(idTache).orElse(null);
    }

    @Override
    public void removeTache(Long idTache) {
        tacheRepository.deleteById(idTache);
    }

    @Override
    public List<Tache> addTasksAndAffectToEtudiant(List<Tache> taches, String nomEt, String prenomEt) {
        //ME aandi el ha9 na3mel parent.set(child) elaa me ykoun el child mawjoud fel base!! Ken msh mawjoud, na3mlou el save 9bal
        Etudiant etudiant = etudiantRepository.findByNomEtAndPrenomEt(nomEt, prenomEt);
        taches.stream().forEach(tache -> {
            tache.setEtudiantOrdinaire(etudiant);
            //tacheRepository.save(tache);
        });
        tacheRepository.saveAll(taches);
        return taches;
    }
}
