package com.esprit.tic.twin.springproject.services;

import com.esprit.tic.twin.springproject.entities.Bloc;
import com.esprit.tic.twin.springproject.entities.Chambre;
import com.esprit.tic.twin.springproject.repositories.BlocRepository;
import com.esprit.tic.twin.springproject.repositories.ChambreRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class BlocServiceImpl implements IBlocService{
    BlocRepository blocRepository;
    ChambreRepository chambreRepository;
    @Override
    public List<Bloc> retrieveAllBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc addBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public Bloc updateBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public Bloc retrieveBloc(Long idBloc) {
        return blocRepository.findById(idBloc).orElse(null);
    }

    @Override
    public void removeBloc(Long idBloc) {
        blocRepository.deleteById(idBloc);
    }

    @Override
    public List<Bloc> retrieveBlocsJPQL(String s) {
        return blocRepository.retrieveBlocs(s);
    }

    @Override
    public List<Bloc> retrieveBlocsKeywords(String s) {
        return blocRepository.findByFoyerUniversiteNomUniversite(s);
    }

    @Override
    public Bloc affecterChambresABloc(List<Long> numChambre, String nomBloc) {
        Bloc bloc = blocRepository.findByNomBloc(nomBloc);
        //That's not good! Bad behaviours.. Posting SQL queries inside a loop is bad in terms of optimization.
        numChambre.stream().forEach(
                numeroChambre -> {
                    Chambre c = chambreRepository.findByNumeroChambre(numeroChambre);
                    c.setBloc(bloc);
                    chambreRepository.save(c);
                }
        );
        return bloc;
    }

    @Scheduled(fixedRate = 60000)
    public void listeChambresParBloc() {
        List<Bloc> blocs = blocRepository.findAll();
        blocs.forEach(bloc -> {
            log.info("bloc : " + bloc.getNomBloc() + " ayant une capacité de : " + bloc.getCapaciteBloc());
            log.info("Liste des chambres du bloc " + bloc.getNomBloc() + " :");
            List<Chambre> chambres = chambreRepository.findByBloc_NomBloc(bloc.getNomBloc());
            chambres.forEach(chambre -> {
                log.info("chambre numéro " + chambre.getNumeroChambre() + " de type " + chambre.getTypeC());
            });
        });
    }
}
