package com.homedeve.tenis.core.service;

import com.homedeve.tenis.core.HibernateUtil;
import com.homedeve.tenis.core.entity.Joueur;
import com.homedeve.tenis.core.repository.JoueurRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public void renomme(long id, String nom) {
        joueurRepository.renome(id, nom);
    }

    public void changeSexe(long identifiant, char sexe) {
    }

    public void deleteJoueur(long id) {
        Session session = null;

        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            joueurRepository.delete(id);


            tx.commit();

            System.out.println("supp ok ");
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
}
