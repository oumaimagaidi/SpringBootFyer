package com.esprit.tic.twin.springproject.services;

import com.esprit.tic.twin.springproject.entities.Etudiant;
import com.esprit.tic.twin.springproject.entities.Reservation;
import com.esprit.tic.twin.springproject.entities.Tache;
import com.esprit.tic.twin.springproject.repositories.EtudiantRepository;
import com.esprit.tic.twin.springproject.repositories.ReservationRepository;
import com.esprit.tic.twin.springproject.repositories.TacheRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService{
    EtudiantRepository etudiantRepository;
    ReservationRepository reservationRepository;
    TacheRepository tacheRepository;

    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant addEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant retrieveEtudiant(Long idEtudiant) {
        return etudiantRepository.findById(idEtudiant).orElse(null);
    }

    @Override
    public void removeEtudiant(Long idEtudiant) {
        etudiantRepository.deleteById(idEtudiant);
    }

    @Override
    public Etudiant affecterEtudiantAReservation(String nomEt, String prenomEt, String idReservation) {
        //Same work every time we have many-to-many relation
        Etudiant e = etudiantRepository.findByNomEtAndPrenomEt(nomEt, prenomEt);
        Reservation r = reservationRepository.findByIdReservation(idReservation);

        //Etudiant is the parent cause mappedBy is in reservation so reservation is child!
        Set<Reservation> reservationsMisesAJour = new HashSet<Reservation>();
        if(e.getReservations()!=null) {
            reservationsMisesAJour = e.getReservations();
        }
        reservationsMisesAJour.add(r);
        e.setReservations(reservationsMisesAJour);
        etudiantRepository.save(e);

        return e;
    }

    @Override
    public HashMap<String, Float> calculNouveauMontantInscriptionDesEtudiants() {
        HashMap<String, Float> nouveauxMontantsInscription = new HashMap<>();

        etudiantRepository.findAll().forEach(etudiant -> {
            Float ancienMontant = etudiant.getMontantInscription();
            LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
            LocalDate endDate = LocalDate.of(LocalDate.now().getYear(), 12, 31);
            Float montantTachesAssignesAnneeEnCours = tacheRepository.sommeTacheAnneeEncours(startDate, endDate, etudiant.getIdEtudiant());
            Float nouveauMontant = ancienMontant;
            if (montantTachesAssignesAnneeEnCours!=null) {
                nouveauMontant = ancienMontant - montantTachesAssignesAnneeEnCours;
            }
            nouveauxMontantsInscription.put(etudiant.getNomEt() + " " + etudiant.getPrenomEt(), nouveauMontant);
        });

        return nouveauxMontantsInscription;
    }


}
