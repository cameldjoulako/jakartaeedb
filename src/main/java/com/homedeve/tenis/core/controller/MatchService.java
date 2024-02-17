package com.homedeve.tenis.core.controller;

import com.homedeve.tenis.core.HibernateUtil;
import com.homedeve.tenis.core.dto.MatchDto;
import com.homedeve.tenis.core.entity.Match;
import com.homedeve.tenis.core.repository.JoueurRepositoryImpl;
import com.homedeve.tenis.core.repository.MatchRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {

    public void deleteMatch(long identifiant) {
    }

    public void tapisVert(long identifiant) {
    }

    public void createMatch(MatchDto dto) {

        Session session = null;

        Transaction tx = null;

        Match match = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();


            match = MatchRepository.getById();


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

    public MatchDto getMatch(long identifiant) {
        return null;
    }
}
