package com.esprit.tic.twin.springproject.services;

import com.esprit.tic.twin.springproject.entities.Universite;

import java.util.List;

public interface IUniversiteService {
    List<Universite> retrieveAllUniversites();
    Universite addUniversite(Universite u);
    Universite updateUniversite(Universite u);
    Universite retrieveUniversite(Long idUniversite);
    void removeUniversite(Long idUniversite);
    Universite affecterFoyerAUniversite(Long idFoyer, String nomUniversite);
    Universite desaffecterFoyerAUniversite(Long idFoyer);
}
