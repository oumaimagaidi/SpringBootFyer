package com.esprit.tic.twin.springproject.controllers;

import com.esprit.tic.twin.springproject.entities.Universite;
import com.esprit.tic.twin.springproject.services.IUniversiteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversiteRestController {
    IUniversiteService universiteService;

    @PostMapping("/add-universite")
    public Universite addUniversite(@RequestBody Universite u) {
        return universiteService.addUniversite(u);
    }

    @GetMapping("/retrieve-all-universites")
    public List<Universite> getUniversites() {
        return universiteService.retrieveAllUniversites();
    }

    @PutMapping("/update-universite")
    public Universite updateUniversite(@RequestBody Universite e) {
        return universiteService.updateUniversite(e);
    }

    @PutMapping("/AffecterFoyerAUniversite/{id-foyer}/{nom-universite}")
    public Universite affecterFoyerAUniversite(@PathVariable("id-foyer") Long idFoyer, @PathVariable("nom-universite") String nomUniversite) {
        return universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);
    }

    @PutMapping("/DesffecterFoyerAUniversite/{id-foyer}")
    public Universite desaffecterFoyerAUniversite(@PathVariable("id-foyer") Long idFoyer) {
        return universiteService.desaffecterFoyerAUniversite(idFoyer);
    }

    @DeleteMapping("/remove-universite/{universite-id}")
    public void removeUniversite(@PathVariable("universite-id") Long idUniversrite) {
        universiteService.removeUniversite(idUniversrite);
    }
}
