package com.esprit.tic.twin.springproject.controllers;

import com.esprit.tic.twin.springproject.entities.Reservation;
import com.esprit.tic.twin.springproject.services.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationRestController {
    IReservationService reservationService;

    @PostMapping("/add-reservation")
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }

    @DeleteMapping("/remove-reservation/{id-reservation}")
    public void removeReservation(@PathVariable("id-reservation") String idReservation) {
        reservationService.removeReservation(idReservation);
    }

    @PostMapping("/affecterReservationAChambre/{idReservation}/{idChambre}")
    public Reservation affecterReservation(@PathVariable("idReservation") String idReservation, @PathVariable("idChambre") Long idChambre) {
        return reservationService.affecterReservationAChambre(idReservation, idChambre);
    }

    @GetMapping("/retrieveReservationsBetweenTwoDates/{dateDebut}/{dateFin}")
    public List<Reservation> retrieveReservationsBetweenTwoDates(@PathVariable("dateDebut") LocalDate dateDebut, @PathVariable("dateFin") LocalDate dateFin) {
        return reservationService.getReservationParAnneeUniversitaire(dateDebut, dateFin);
    }
}
