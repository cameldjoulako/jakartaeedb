package com.homedeve.tenis.core.repository;

import com.homedeve.tenis.core.entity.Joueur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JoueurRepositoryImpl {

    public void create(Joueur joueur) {
        Connection conn = null;

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("INSERT INTO JOUEUR (NOM,PRENOM,SEXE) VALUES(?, ?, ?)");

            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());

            preparedStatement.executeUpdate();

            System.out.println("Joueur créé");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void update(Joueur joueur) {
        Connection conn = null;

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=? WHERE ID=?");

            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());

            preparedStatement.executeUpdate();

            System.out.println("Joueur créé");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
