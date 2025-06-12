package org.sanchez.pixup.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sanchez.pixup.dao.DisqueraDao;
import org.sanchez.pixup.hibernate.HibernateUtil;
import org.sanchez.pixup.model.Disquera;

import java.util.List;

public class DisqueraDaoImpl implements DisqueraDao {

    private static volatile DisqueraDao disqueraDao;

    private DisqueraDaoImpl() {}

    public static DisqueraDao getInstance() {
        if (disqueraDao == null) {
            synchronized (DisqueraDaoImpl.class) {
                if (disqueraDao == null) {
                    disqueraDao = new DisqueraDaoImpl();
                }
            }
        }
        return disqueraDao;
    }

    @Override
    public List<Disquera> findAll() {
        List<Disquera> disqueras = null;
        Session session = HibernateUtil.getSession();
        disqueras = session.createQuery("from Disquera", Disquera.class).getResultList();
        session.close();
        return disqueras;
    }

    @Override
    public boolean save(Disquera disquera) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(disquera);
        session.getTransaction().commit();
        session.close();
        return true;
    }


    @Override
    public boolean update(Disquera disquera) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(disquera);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Disquera disquera) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.remove(disquera);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Disquera findById(int id) {
        Session session = HibernateUtil.getSession();
        Disquera disquera = session.get(Disquera.class, id);
        session.close();
        return disquera;
    }
}