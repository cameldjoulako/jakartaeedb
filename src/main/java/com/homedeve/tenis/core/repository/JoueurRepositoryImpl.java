package com.homedeve.tenis.core.repository;

import com.homedeve.tenis.core.entity.Joueur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

    public void create(Joueur joueur) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?serverTimezone=GMT", "homedeve", "root");

            preparedStatement = conn.prepareStatement("INSERT INTO JOUEUR (NOM,PRENOM,SEXE) VALUES(?, ?, ?)");

            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());

            preparedStatement.executeUpdate();

            System.out.println("Joueur créé");

        } catch (SQLException e) {
            e.printStackTrace();

            try {
                if(conn!=null) conn.rollback();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if(conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public void update(Joueur joueur) {
        Connection conn = null;

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=? WHERE ID=?");

            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setLong(3, joueur.getId());

            preparedStatement.executeUpdate();

            System.out.println("Joueur update");

        } catch (SQLException e) {
            e.printStackTrace();

            try {
                if(conn!=null) conn.rollback();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if(conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public void delete(Long id) {
        Connection conn = null;

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("DELETE FROM JOUEUR WHERE ID=?");


            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            System.out.println("Joueur Delete");

        } catch (SQLException e) {
            e.printStackTrace();

            try {
                if(conn!=null) conn.rollback();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if(conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public Joueur getById(Long id) {
        Connection conn = null;

        Joueur joueur = null;

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("SELECT NOM, PRENOM, SEXE FROM JOUEUR WHERE ID=?");


            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                joueur = new Joueur();

                joueur.setId(id);
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));
            }


            System.out.println("Selection ok");
            return joueur;


        } catch (SQLException e) {
            e.printStackTrace();

            try {
                if(conn!=null) conn.rollback();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if(conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return joueur;
    }

    public List<Joueur> list(Long id) {
        Connection conn = null;

        Joueur joueur = null;

        List<Joueur> joueurs = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM JOUEUR");

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                joueur = new Joueur();
                joueur.setId(id);
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));

                joueurs.add(joueur);

            }


            System.out.println("Joueurs lu");
            return joueurs;


        } catch (SQLException e) {
            e.printStackTrace();

            try {
                if(conn!=null) conn.rollback();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if(conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return joueurs;
    }

}
