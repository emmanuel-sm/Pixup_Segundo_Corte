package org.sanchez.pixup.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sanchez.pixup.dao.CancionDao;
import org.sanchez.pixup.model.Cancion;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CancionDaoImplTest {

    private CancionDao cancionDao;

    @BeforeEach
    void setUp() {
        cancionDao = CancionDaoImpl.getInstance();
    }

    @Test
    void getInstance() {
        assertNotNull(cancionDao, "La instancia de CancionDao no debería ser nula.");
    }

    @Test
    void findAll() {
        List<Cancion> canciones = cancionDao.findAll();
        assertNotNull(canciones, "La lista de canciones no debería ser nula.");
        assertFalse(canciones.isEmpty(), "La lista de canciones no debería estar vacía.");


        System.out.println("Lista de canciones encontradas:");
        canciones.forEach(cancion -> System.out.println("ID: " + cancion.getId() + " | Título: " + cancion.getTitulo() +
                " | Duración: " + cancion.getDuracion() + " segundos | Disco ID: " + cancion.getDiscoId()));
    }


    @Test
    void save() {
        Cancion cancion = new Cancion();
        cancion.setTitulo("Fix You");
        cancion.setDuracion(267);
        cancion.setDiscoId(1);

        boolean resultado = cancionDao.save(cancion);
        assertTrue(resultado, "La canción debería haberse guardado exitosamente.");

        List<Cancion> canciones = cancionDao.findAll();
        assertTrue(canciones.stream().anyMatch(c -> c.getTitulo().equals("Fix You")),
                "La canción 'Fix You' debería existir en la lista.");
    }

    @Test
    void update() {
        Cancion cancion = cancionDao.findById(1);
        assertNotNull(cancion, "La canción con ID 1 debería existir.");

        cancion.setTitulo("Fix You (Actualizada)");
        boolean resultado = cancionDao.update(cancion);
        assertTrue(resultado, "La canción debería actualizarse correctamente.");

        Cancion cancionActualizada = cancionDao.findById(1);
        assertEquals("Fix You (Actualizada)", cancionActualizada.getTitulo(),
                "El título de la canción debe" +
                        " " +
                        "" +
                        "ría haberse actualizado.");
    }

    @Test
    void delete() {
        Cancion cancion = cancionDao.findById(2);
        assertNotNull(cancion, "La canción con ID 2 debería existir antes de eliminarse.");

        boolean resultado = cancionDao.delete(cancion);
        assertTrue(resultado, "La canción debería eliminarse correctamente.");

        Cancion cancionEliminada = cancionDao.findById(2);
        assertNull(cancionEliminada, "La canción con ID 2 ya no debería existir después de la eliminación.");
    }

    @Test
    void findById() {
        Cancion cancion = cancionDao.findById(1);
        assertNotNull(cancion, "La canción con ID 1 debería existir.");
        assertEquals(1, cancion.getId(), "El ID de la canción debería coincidir.");

        System.out.println("Canción encontrada:");
        System.out.println("ID: " + cancion.getId());
        System.out.println("Título: " + cancion.getTitulo());
        System.out.println("Duración: " + cancion.getDuracion() + " segundos");
        System.out.println("Disco ID: " + cancion.getDiscoId());
    }

}
