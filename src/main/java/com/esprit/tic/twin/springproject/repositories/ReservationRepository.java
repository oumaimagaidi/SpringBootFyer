package com.esprit.tic.twin.springproject.repositories;

import com.esprit.tic.twin.springproject.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, String> {
    Reservation findByIdReservation(String idReservation);

    @Query("select r from Reservation r where r.anneeUniversitaire between :t1 and :t2")
    List<Reservation> getReservationParAnneeUniversitaire(@Param("t1") LocalDate dateDebut, @Param("t2") LocalDate dataFin);
}
