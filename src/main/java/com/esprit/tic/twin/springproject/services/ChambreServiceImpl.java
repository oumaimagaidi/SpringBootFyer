package com.esprit.tic.twin.springproject.services;

import com.esprit.tic.twin.springproject.entities.Chambre;
import com.esprit.tic.twin.springproject.entities.TypeChambre;
import com.esprit.tic.twin.springproject.repositories.ChambreRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@AllArgsConstructor
public class ChambreServiceImpl implements IChambreService{
    ChambreRepository chambreRepository;

    @Override
    public List<Chambre> retrieveAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public Chambre updateChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public Chambre retrieveChambre(Long idChambre) {
        return chambreRepository.findById(idChambre).orElse(null);
    }

    @Override
    public void removeChambre(Long idChambre) {
        chambreRepository.deleteById(idChambre);
    }

    @Override
    public List<Chambre> getChambresParNomBlocAndTypeChambre(String s, TypeChambre t) {
        return chambreRepository.findByBlocNomBlocAndTypeC(s, t);
    }

    @Override
    public long nbChambreParTypeEtBloc(TypeChambre type, long idBloc) {
        return chambreRepository.nbChambreParTypeEtBloc(type, idBloc);
    }

    /* =================== Developed by me and works after I added (double) in front of nbTotalsChambres ====================  */
    /*@Scheduled(fixedRate = 3000)
    void pourcentageChambreParTypeChambre() {
        int nbTotalsChambres = chambreRepository.findAll().size();
        log.info("nbTotalsChambres : " + nbTotalsChambres);
        log.info("le pourcentage des chambres pour le type SIMPLE est égale à " + (chambreRepository.nbTypeChambre(TypeChambre.SIMPLE) / (double) nbTotalsChambres) * 100);
        log.info("le pourcentage des chambres pour le type DOUBLE est égale à " + (chambreRepository.nbTypeChambre(TypeChambre.DOUBLE) / (double) nbTotalsChambres) * 100);
        log.info("le pourcentage des chambres pour le type TRIPLE est égale à " + (chambreRepository.nbTypeChambre(TypeChambre.TRIPLE) / (double) nbTotalsChambres) * 100);
    }*/
    /* ====================================================================================================================== */

    @Scheduled(fixedRate = 60000)
    void pourcentageChambreParTypeChambre() {
        Integer nbTotalsChambres = chambreRepository.findAll().size();
        log.info("nbTotalsChambres : " + nbTotalsChambres);
        Arrays.stream(TypeChambre.values()).forEach(
                typeChambre -> {
                    Integer nbChambresParType = chambreRepository.nbTypeChambre(typeChambre);
                    Double pourcentageParType = (nbChambresParType.doubleValue() / nbTotalsChambres.doubleValue()) * 100;
                    log.info("le pourcentage des chambres pour le type " + typeChambre + " est égale à " + pourcentageParType);
                }
        );
    }
}
