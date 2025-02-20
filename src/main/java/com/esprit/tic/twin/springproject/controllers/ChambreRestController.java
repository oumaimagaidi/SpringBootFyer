package com.esprit.tic.twin.springproject.controllers;

import com.esprit.tic.twin.springproject.entities.Chambre;
import com.esprit.tic.twin.springproject.entities.TypeChambre;
import com.esprit.tic.twin.springproject.services.IChambreService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chambre")
public class ChambreRestController {
    IChambreService chambreService;

    @PostMapping("/add-chambre")
    public Chambre addChambre(@RequestBody Chambre c) {
        return chambreService.addChambre(c);
    }

    @GetMapping("/retrieve-all-chambres")
    public List<Chambre> retrieveChambres() {
        return chambreService.retrieveAllChambres();
    }

    @DeleteMapping("/remove-chambre/{chambre-id}")
    public void removeBloc(@PathVariable("chambre-id") Long idChambre) {
        chambreService.removeChambre(idChambre);
    }

    @GetMapping("/retrieve-blocs-keywords/{nom-bloc}/{type-chambre}")
    public List<Chambre> retrieveBlocsByNameAndTypeChambre(@PathVariable("nom-bloc") String s, @PathVariable("type-chambre") TypeChambre t) {
        return chambreService.getChambresParNomBlocAndTypeChambre(s, t);
    }

    @GetMapping("/nbChambreParTypeEtBloc/{type}/{idBloc}")
    public long nbChambreParTypeEtBloc(@PathVariable("type") TypeChambre type, @PathVariable("idBloc") long idBloc) {
        return chambreService.nbChambreParTypeEtBloc(type, idBloc);
    }
}
