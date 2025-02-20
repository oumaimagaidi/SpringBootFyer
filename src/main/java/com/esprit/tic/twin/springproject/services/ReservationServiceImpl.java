package com.esprit.tic.twin.springproject.services;

import com.esprit.tic.twin.springproject.entities.Chambre;
import com.esprit.tic.twin.springproject.entities.Etudiant;
import com.esprit.tic.twin.springproject.entities.Reservation;
import com.esprit.tic.twin.springproject.entities.TypeChambre;
import com.esprit.tic.twin.springproject.repositories.ChambreRepository;
import com.esprit.tic.twin.springproject.repositories.EtudiantRepository;
import com.esprit.tic.twin.springproject.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService {
    ReservationRepository reservationRepository;
    ChambreRepository chambreRepository;
    EtudiantRepository etudiantRepository;

    @Override
    public List<Reservation> retrieveAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation addReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public Reservation updateReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public Reservation retrieveReservation(String idReservation) {
        return reservationRepository.findById(idReservation).orElse(null);
    }

    @Override
    public void removeReservation(String idReservation) {
        reservationRepository.deleteById(idReservation);
    }

    @Override
    public Reservation affecterReservationAChambre(String idReservation, Long idChambre) {
        Reservation reservation = reservationRepository.findByIdReservation(idReservation);
        Chambre chambre = chambreRepository.findByIdChambre(idChambre);

        chambre.getReservations().add(reservation);
        chambreRepository.save(chambre);

        return null;
    }

    @Override
    public List<Reservation> getReservationParAnneeUniversitaire(LocalDate dateDebut, LocalDate dateFin) {
        return reservationRepository.getReservationParAnneeUniversitaire(dateDebut, dateFin);
    }

    /*@Scheduled(fixedRate = 60000)
    public void nbPlacesDisponiblesParChambreAnneeEnCours() {
        LocalDate currentDate = LocalDate.now();
        LocalDate dateDebut = LocalDate.of(currentDate.getYear(), 12, 31);
        LocalDate dateFin = LocalDate.of(currentDate.getYear(), 1, 1);
        List<Chambre> chambresDisponibles = chambreRepository.findAll();
        chambresDisponibles.forEach(
                chambre -> {
                    AtomicReference<Integer> nbChambresOcupes = new AtomicReference<>(0);
                    if(chambre.getReservations()!=null) {
                        Set<Reservation> reservations = chambre.getReservations();
                        reservations.stream().forEach(
                                reservation -> {
                                    if (reservation. && reservation.getAnneeUniversitaire().toInstant().isAfter(dateDebut) && reservation)
                                        nbChambresOcupes.getAndSet(nbChambresOcupes);
                                }
                        );
                    }
                    if(chambre.getTypeC().equals(TypeChambre.SIMPLE)) {
                        log.info("")
                    }
                }
        );
    }*/

    @Override
    public Reservation ajouterReservationEtAssignerAChambreEtAEtudiant(Reservation res, Long numChambre, Long cin) {
        Chambre chambre = chambreRepository.findByNumeroChambre(numChambre);
        Etudiant etudiant = etudiantRepository.findByCin(cin);
        res.setIdReservation(chambre.getNumeroChambre() + cin.toString() + LocalDate.now().getYear());

        /* La condition */

        LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        LocalDate endDate = LocalDate.of(LocalDate.now().getYear(), 12, 31);


        /* ============ */

        Reservation r = reservationRepository.save(res);
        chambre.getReservations().add(r);
        chambreRepository.save(chambre);
        Set<Reservation> newReservations = new HashSet<>();
        if(etudiant.getReservations()!=null)
            newReservations = etudiant.getReservations();
        newReservations.add(res);
        etudiant.setReservations(newReservations);
        etudiantRepository.save(etudiant);

        return res;
    }

    @Scheduled(fixedRate = 5000)
    public void nbPlacesDisponibleParChambreAnneeEnCours() {
        LocalDate currentDate = LocalDate.now();
        LocalDate dateDebut = LocalDate.of(currentDate.getYear(), 12, 31);
        LocalDate dateFin = LocalDate.of(currentDate.getYear(), 1, 1);
        List<Chambre> chambresDisponibles = chambreRepository.findAll();
        chambresDisponibles.forEach(
                chambre -> {

                }
        );
    }
}
