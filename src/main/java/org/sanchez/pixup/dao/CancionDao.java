package org.sanchez.pixup.dao;

import org.sanchez.pixup.model.Artista;
import org.sanchez.pixup.model.Cancion;

import java.util.List;

public interface CancionDao {
    List <Cancion> findAll();
    boolean save(Cancion cancion);
    boolean update(Cancion cancion);
    boolean delete(Cancion cancion);
    Cancion findById(int id);
}
