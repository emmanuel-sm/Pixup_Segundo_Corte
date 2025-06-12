package org.sanchez.pixup.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sanchez.pixup.dao.GeneroDao;
import org.sanchez.pixup.model.Genero;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeneroDaoImplTest {

    private GeneroDao generoDao;

    @BeforeEach
    void setUp() {
        generoDao = GeneroDaoImpl.getInstance();
    }

    @Test
    void getInstance() {
        assertNotNull(generoDao, "La instancia de GeneroDao no debería ser nula.");
    }

    @Test
    void findAll() {
        List<Genero> generos = generoDao.findAll();
        assertNotNull(generos, "La lista de géneros no debería ser nula.");
        assertFalse(generos.isEmpty(), "La lista de géneros no debería estar vacía.");

        System.out.println("Lista de géneros encontrados:");
        generos.forEach(genero ->
                System.out.println("ID: " + genero.getId() + " | Descripción: " + genero.getDescripcion()));
    }


    @Test
    void save() {
        Genero genero = new Genero();
        genero.setDescripcion("Rock");

        boolean resultado = generoDao.save(genero);
        assertTrue(resultado, "El género debería haberse guardado exitosamente.");

        List<Genero> generos = generoDao.findAll();
        assertTrue(generos.stream().anyMatch(g -> g.getDescripcion().equals("Rock")),
                "El género 'Rock' debería existir en la lista.");
    }

    @Test
    void update() {
        Genero genero = generoDao.findById(1);
        assertNotNull(genero, "El género con ID 1 debería existir.");

        genero.setDescripcion("Rock Alternativo");
        boolean resultado = generoDao.update(genero);
        assertTrue(resultado, "El género debería actualizarse correctamente.");

        Genero generoActualizado = generoDao.findById(1);
        assertEquals("Rock Alternativo", generoActualizado.getDescripcion(),
                "La descripción del género debería haberse actualizado.");
    }

    @Test
    void delete() {
        Genero genero = generoDao.findById(2);
        assertNotNull(genero, "El género con ID 2 debería existir antes de eliminarse.");

        boolean resultado = generoDao.delete(genero);
        assertTrue(resultado, "El género debería eliminarse correctamente.");

        Genero generoEliminado = generoDao.findById(2);
        assertNull(generoEliminado, "El género con ID 2 ya no debería existir después de la eliminación.");
    }

    @Test
    void findById() {
        Genero genero = generoDao.findById(1);
        assertNotNull(genero, "El género con ID 1 debería existir.");
        assertEquals(1, genero.getId(), "El ID del género debería coincidir.");

        System.out.println("Género encontrado:");
        System.out.println("ID: " + genero.getId());
        System.out.println("Descripción: " + genero.getDescripcion());
    }

}
