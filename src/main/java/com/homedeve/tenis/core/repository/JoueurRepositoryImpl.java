package com.homedeve.tenis.core.repository;

import com.homedeve.tenis.core.HibernateUtil;
import com.homedeve.tenis.core.entity.Joueur;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

    public void create(Joueur joueur) {

        Session session = null;
        Transaction tx = null;


        try {
            //recuperation d'une session hibernate
            session = HibernateUtil.getSessionFactory().openSession();

            tx = session.beginTransaction();

            //enregistrement en BD
            session.persist(joueur);
            session.flush();
            System.out.println("Joueur lu");
        }
        catch (Throwable t){
            t.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
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

        Joueur joueur = null;

        Session session = null;

        try {
           //recuperation d'une session hibernate
            session = HibernateUtil.getSessionFactory().openSession();
            //recuperation d'une ligne et transformation en objet
            joueur = session.get(Joueur.class, id);
            System.out.println("Joueur lu");
        }
        catch (Throwable t){
          t.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
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
