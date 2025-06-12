package org.sanchez.pixup.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sanchez.pixup.dao.GeneroDao;
import org.sanchez.pixup.hibernate.HibernateUtil;
import org.sanchez.pixup.model.Genero;

import java.util.List;

public class GeneroDaoImpl implements GeneroDao{
    private static volatile GeneroDao generoDao;

    private GeneroDaoImpl() {}

    public static GeneroDao getInstance() {
        if (generoDao == null) {
            synchronized (GeneroDaoImpl.class) {
                if (generoDao == null) {
                    generoDao = new GeneroDaoImpl();
                }
            }
        }
        return generoDao;
    }

    @Override
    public List<Genero> findAll() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        List<Genero> generos = session.createQuery("FROM Genero", Genero.class).getResultList();
        tx.commit();
        session.close();
        return generos;
    }

    @Override
    public boolean save(Genero genero) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(genero);
        session.getTransaction().commit();
        session.close();
        return true;
    }


    @Override
    public boolean update(Genero genero) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(genero);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Genero genero) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.remove(genero);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Genero findById(int id) {
        Session session = HibernateUtil.getSession();
        Genero genero = session.get(Genero.class, id);
        session.close();
        return genero;
    }
}
