package org.sanchez.pixup.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sanchez.pixup.dao.ArtistaDao;
import org.sanchez.pixup.model.Artista;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArtistaDaoImplTest {

    private ArtistaDao artistaDao;

    @BeforeEach
    void setUp() {
        artistaDao = ArtistaDaoImpl.getInstance();
    }

    @Test
    void getInstance() {
        assertNotNull(artistaDao, "La instancia de ArtistaDao no debería ser nula.");
    }

    @Test
    void findAll() {
        List<Artista> artistas = artistaDao.findAll();
        assertNotNull(artistas, "La lista de artistas no debería ser nula.");
        assertFalse(artistas.isEmpty(), "La lista de artistas no debería estar vacía.");

        System.out.println("Lista de artistas encontrados:");
        artistas.forEach(artista -> System.out.println("ID: " + artista.getId() + " | Nombre: " + artista.getNombre()));
    }


    @Test
    void save() {
        Artista artista = new Artista();
        artista.setNombre("Coldplay");

        boolean resultado = artistaDao.save(artista);
        assertTrue(resultado, "El artista debería haberse guardado exitosamente.");

        List<Artista> artistas = artistaDao.findAll();
        assertTrue(artistas.stream().anyMatch(a -> a.getNombre().equals("Coldplay")),
                "El artista recién guardado debería existir en la lista.");
    }

    @Test
    void update() {
        Artista artista = artistaDao.findById(1);
        assertNotNull(artista, "El artista con ID 1 debería existir.");

        artista.setNombre("Maluma");
        boolean resultado = artistaDao.update(artista);
        assertTrue(resultado, "El artista debería actualizarse correctamente.");

        Artista artistaActualizado = artistaDao.findById(1);
        assertEquals("Maluma", artistaActualizado.getNombre(),
                "El nombre del artista debería haberse actualizado.");
    }

    @Test
    void delete() {
        Artista artista = artistaDao.findById(2);
        assertNotNull(artista, "El artista con ID 2 debería existir antes de eliminarse.");

        boolean resultado = artistaDao.delete(artista);
        assertTrue(resultado, "El artista debería eliminarse correctamente.");

        Artista artistaEliminado = artistaDao.findById(2);
        assertNull(artistaEliminado, "El artista con ID 4 ya no debería existir después de la eliminación.");
    }

    @Test
    void findById() {
        Artista artista = artistaDao.findById(1);
        assertNotNull(artista, "El artista con ID 1 debería existir.");
        assertEquals(1, artista.getId(), "El ID del artista debería coincidir.");

        System.out.println("Artista encontrado:");
        System.out.println("ID: " + artista.getId());
        System.out.println("Nombre: " + artista.getNombre());
    }

}
