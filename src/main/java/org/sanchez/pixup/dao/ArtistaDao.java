package org.sanchez.pixup.dao;

import org.sanchez.pixup.model.Artista;

import java.util.List;

public interface ArtistaDao {
    List<Artista> findAll( );
    boolean save( Artista artista);
    boolean update( Artista artista );
    boolean delete( Artista artista );
    Artista findById(int id );
}
