package com.esprit.tic.twin.springproject.controllers;

import com.esprit.tic.twin.springproject.entities.Tache;
import com.esprit.tic.twin.springproject.services.ITacheService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tache")
public class TacheRestController {
    ITacheService tacheService;

    @PostMapping("/add-tache")
    public Tache addTache(@RequestBody Tache tache) {
        return tacheService.addTache(tache);
    }

    @DeleteMapping("/remove-tache/{id-tache}")
    public void removeTache(@PathVariable("id-tache") Long idTache) {
        tacheService.removeTache(idTache);
    }

    @PostMapping("/addTacheEtAffecterAEtudiant/{nom-etudiant}/{prenom-etudiant}")
    public List<Tache> addTasksAndAffectToEtudiant(@RequestBody List<Tache> taches, @PathVariable("nom-etudiant") String nomEt, @PathVariable("prenom-etudiant") String prenomEt) {
        return tacheService.addTasksAndAffectToEtudiant(taches, nomEt, prenomEt);
    }
}
