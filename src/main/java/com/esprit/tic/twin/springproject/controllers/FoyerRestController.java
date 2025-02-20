package com.esprit.tic.twin.springproject.controllers;

import com.esprit.tic.twin.springproject.entities.Foyer;
import com.esprit.tic.twin.springproject.services.IFoyerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/foyer")
public class FoyerRestController {
    IFoyerService foyerService;

    @PostMapping("/add-foyer")
    public Foyer addFoyer(@RequestBody Foyer f) {
        return foyerService.addFoyer(f);
    }

    @PostMapping("/add-foyer-with-blocs")
    public Foyer addFoyerWithBloc(@RequestBody Foyer f) {
        return foyerService.addFoyerWithBloc(f);
    }

    @GetMapping("/retrieve-all-foyers")
    public List<Foyer> getFoyers() {
        return foyerService.retrieveAllFoyers();
    }

    @GetMapping("/retrieve-by-nomUniversite/{universite-nom}")
    public List<Foyer> getFoyer(@PathVariable("universite-nom") String nomUniversite) {
        return foyerService.retrieveFoyerByNomUniversity(nomUniversite);
    }

    @PutMapping("/update-foyer")
    public Foyer updateFoyer(@RequestBody Foyer e) {
        return foyerService.updateFoyer(e);
    }

    @DeleteMapping("/remove-foyer/{foyer-id}")
    public void removeFoyer(@PathVariable("foyer-id") Long idUniversrite) {
        foyerService.removeFoyer(idUniversrite);
    }
}
