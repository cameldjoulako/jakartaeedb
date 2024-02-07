package com.homedeve.tenis.core.service;

import com.homedeve.tenis.core.entity.Joueur;
import com.homedeve.tenis.core.repository.JoueurRepositoryImpl;

public class JoueurService {
    private JoueurRepositoryImpl joueurRepository;
    public JoueurService(){
        this.joueurRepository = new JoueurRepositoryImpl();
    }

    public Joueur getJoueur(long identifiant) {

        return joueurRepository.getById(identifiant);
    }

    public void createJoueur(Joueur joueur) {
        joueurRepository.create(joueur);
    }

    public void renomme(long identifiant, String nom) {
    }

    public void changeSexe(long identifiant, char sexe) {
    }

    public void deleteJoueur(long identifiant) {
    }
}
