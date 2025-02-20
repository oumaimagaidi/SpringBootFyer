package com.esprit.tic.twin.springproject.controllers;

import com.esprit.tic.twin.springproject.entities.Bloc;
import com.esprit.tic.twin.springproject.services.IBlocService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bloc")
public class BlocRestController {
    IBlocService blocService;

    @PostMapping("/add-bloc")
    public Bloc addBloc(@RequestBody Bloc b) {
        return blocService.addBloc(b);
    }

    @GetMapping("/retrieve-all-blocs")
    public List<Bloc> retrieveBlocs() {
        return blocService.retrieveAllBlocs();
    }

    @GetMapping("/retrieve-blocs-jpql/{nom-universite}")
    public List<Bloc> retrieveBlocsByNomUniversiteJPQL(@PathVariable("nom-universite") String s) {
        return blocService.retrieveBlocsJPQL(s);
    }

    @GetMapping("/retrieve-blocs-keywords/{nom-universite}")
    public List<Bloc> retrieveBlocsByNomUniversiteKeywords(@PathVariable("nom-universite") String s) {
        return blocService.retrieveBlocsKeywords(s);
    }

    @PutMapping("/update-bloc")
    public Bloc updateBloc(@RequestBody Bloc b) {
        return blocService.updateBloc(b);
    }

    @DeleteMapping("/delete-bloc/{bloc-id}")
    public void removeBloc(@PathVariable("bloc-id") Long idBloc) {
        blocService.removeBloc(idBloc);
    }

    @PutMapping("/affecterChambresABloc/{bloc-nom}")
    public Bloc affecterChambresABloc(@RequestBody List<Long> numChambre, @PathVariable("bloc-nom") String nomBloc) {
        return blocService.affecterChambresABloc(numChambre, nomBloc);
    }
}