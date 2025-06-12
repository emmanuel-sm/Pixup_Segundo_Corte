package org.sanchez.pixup.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sanchez.pixup.dao.ArtistaDao;
import org.sanchez.pixup.hibernate.HibernateUtil;
import org.sanchez.pixup.model.Artista;
import org.sanchez.pixup.model.Disquera;

import java.util.List;

public class ArtistaDaoImpl implements ArtistaDao {

    private static volatile ArtistaDao artistaDao;

    private ArtistaDaoImpl() {}

    public static ArtistaDao getInstance() {
        if (artistaDao == null) {
            artistaDao = new ArtistaDaoImpl();
        }
        return artistaDao;
    }

    @Override
    public List<Artista> findAll() {
        List<Artista> artistas = null;
        Session session = HibernateUtil.getSession();
        artistas = session.createQuery("from Artista", Artista.class).getResultList();
        session.close();
        return artistas;
    }

    @Override
    public boolean save(Artista artista) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(artista);
        session.getTransaction().commit();
        session.close();
        return true;
    }


    @Override
    public boolean update(Artista artista) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(artista);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Artista artista) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.remove(artista);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Artista findById(int id) {
        Session session = HibernateUtil.getSession();
        Artista artista = session.get(Artista.class, id);
        session.close();
        return artista;
    }
}
