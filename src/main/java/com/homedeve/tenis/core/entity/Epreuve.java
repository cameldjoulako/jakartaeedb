package com.homedeve.tenis.core.entity;

import jakarta.persistence.*;

@Entity
public class Epreuve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Short annee;
    @ManyToOne
    @JoinColumn(name="ID_TOURNOI")
    private Tournoi tournoi;
    @Column(name="TYPE_EPREUVE")
    private Character TypeEpreuve;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getAnnee() {
        return annee;
    }

    public void setAnnee(Short annee) {
        this.annee = annee;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    public Character getTypeEpreuve() {
        return TypeEpreuve;
    }

    public void setTypeEpreuve(Character typeEpreuve) {
        TypeEpreuve = typeEpreuve;
    }

    public String getNom() {
        return null;
    }

    public String getPrenom() {
        return null;
    }
}
