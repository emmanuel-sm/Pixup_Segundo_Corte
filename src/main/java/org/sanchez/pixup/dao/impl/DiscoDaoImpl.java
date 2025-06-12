package org.sanchez.pixup.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sanchez.pixup.dao.DiscoDao;
import org.sanchez.pixup.hibernate.HibernateUtil;
import org.sanchez.pixup.model.Disco;

import java.util.List;

public class DiscoDaoImpl implements DiscoDao {

    private static volatile DiscoDao discoDao;

    private DiscoDaoImpl() {}

    public static DiscoDao getInstance() {
        if (discoDao == null) {
            synchronized (DiscoDaoImpl.class) {
                if (discoDao == null) {
                    discoDao = new DiscoDaoImpl();
                }
            }
        }
        return discoDao;
    }

    @Override
    public List<Disco> findAll() {
        List<Disco> discos = null;
        Session session = HibernateUtil.getSession();
        discos = session.createQuery("from Disco", Disco.class).getResultList();
        session.close();
        return discos;
    }

    @Override
    public boolean save(Disco disco) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(disco);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Disco disco) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(disco);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Disco disco) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.remove(disco);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Disco findById(int id) {
        Session session = HibernateUtil.getSession();
        Disco disco = session.get(Disco.class, id);
        session.close();
        return disco;
    }
}
