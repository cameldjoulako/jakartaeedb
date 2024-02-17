package com.homedeve.tenis.core.repository;

import com.homedeve.tenis.core.HibernateUtil;
import com.homedeve.tenis.core.entity.Joueur;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.*;
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

            //session.flush(); //ou
            tx.commit(); // Ici on laisse Hibernate de s'occuper du flush

            System.out.println("Joueur lu");
        }
        catch (Exception t){

            if (tx!=null) {
                tx.rollback();
            }
            t.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public Joueur renome(Long id, String name) {

        Joueur joueur = null;
        Session session = null;
        Transaction tx = null;
        try {
            //recuperation d'une session hibernate
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            //recuperation d'une ligne et transformation en objet
            joueur = session.get(Joueur.class, id);

            joueur.setNom(name);
            tx.commit();

            System.out.println("nom du joueur mmodifier");
        }
        catch (Exception t){

            if (tx!=null) {
                tx.rollback();
            }
            t.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }

        return joueur;
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

        Joueur joueur = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        joueur = session.get(Joueur.class, id);

         session.delete(joueur);


        System.out.println("Joueur supprimer ! ");



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

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query<Joueur> query =  session.createQuery("select j from Joueur j", Joueur.class );
        List<Joueur> joueurs = query.getResultList();
        /*ransaction tx = null;
        Connection conn = null;*/

       /* Joueur joueur = null;

        List<Joueur> joueurs = new ArrayList<>();

        PreparedStatement preparedStatement = null;*/
        /*try {
            *//*preparedStatement = conn.prepareStatement("SELECT * FROM JOUEUR");

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                joueur = new Joueur();
                joueur.setId(id);
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));

                joueurs.add(joueur);

            }*//*


            System.out.println("Joueurs lu");
            return joueurs;


        } finally {
            *//*try {
                if(conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }*//*
        }*/

        return joueurs;
    }

    public List<Joueur> list() {
        return null;
    }
}
