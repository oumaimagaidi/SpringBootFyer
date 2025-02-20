package com.esprit.tic.twin.springproject.controllers;

import com.esprit.tic.twin.springproject.entities.Etudiant;
import com.esprit.tic.twin.springproject.services.IEtudiantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantRestController {
    IEtudiantService etudiantService;

    @PostMapping("/add-etudiant")
    public Etudiant addEtudiant(@RequestBody Etudiant e) {
        return etudiantService.addEtudiant(e);
    }

    @GetMapping("/retrieve-all-etudiants")
    public List<Etudiant> getEtudiants() {
        return etudiantService.retrieveAllEtudiants();
    }

    @DeleteMapping("/remove-etudiant/{etudiant-id}")
    public void removeEtudiant(@PathVariable("etudiant-id") Long idEtudiant) {
        etudiantService.removeEtudiant(idEtudiant);
    }

    @PutMapping("/affecterEtudiantsAReservations/{etudiant-nom}/{etudiant-prenom}/{reservation-id}")
    public void affecterReservationsAEtudiants(@PathVariable("etudiant-nom") String nomEt, @PathVariable("etudiant-prenom") String prenomEt, @PathVariable("reservation-id") String idReservation) {
        etudiantService.affecterEtudiantAReservation(nomEt, prenomEt, idReservation);
    }

    @GetMapping("/calcul-montant")
    public HashMap<String, Float> calculNouveauMontant() {
        return etudiantService.calculNouveauMontantInscriptionDesEtudiants();
    }
}
