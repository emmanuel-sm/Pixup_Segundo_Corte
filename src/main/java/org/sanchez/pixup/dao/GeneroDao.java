package org.sanchez.pixup.dao;

import org.sanchez.pixup.model.Genero;

import java.util.List;

public interface GeneroDao {
    List<Genero> findAll();
    boolean save (Genero genero);
    boolean update (Genero genero);
    boolean delete (Genero genero);
    Genero findById(int id );
}
