package org.sanchez.pixup.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sanchez.pixup.dao.CancionDao;
import org.sanchez.pixup.hibernate.HibernateUtil;
import org.sanchez.pixup.model.Cancion;


import java.util.Collections;
import java.util.List;

public class CancionDaoImpl implements CancionDao {
    private static volatile CancionDao cancionDao;
    private CancionDaoImpl(){}

    public static CancionDao getInstance() {
        if(cancionDao == null){
            cancionDao = new CancionDaoImpl();
        }        return cancionDao;
    }

    @Override
    public List<Cancion> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            List<Cancion> canciones = session.createQuery("from Cancion", Cancion.class).getResultList();
            System.out.println("Canciones recuperadas: " + canciones);
            return canciones != null ? canciones : Collections.emptyList();
        } catch (Exception e) {
            System.out.println("Error en findAll(): " + e.getMessage());
            return Collections.emptyList();
        }
    }


    @Override
    public boolean save(Cancion cancion) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();

            if (cancion.getId() == null) {
                session.persist(cancion);
            } else {
                session.merge(cancion);
            }

            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error al guardar la canción: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Cancion cancion) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(cancion);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Cancion cancion) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.remove(cancion);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Cancion findById(int id) {
            Session session = HibernateUtil.getSession();
            Cancion cancion = session.get(Cancion.class,id);
            return cancion;
    }
}
