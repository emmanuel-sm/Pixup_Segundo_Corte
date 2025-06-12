package org.sanchez.pixup.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sanchez.pixup.dao.DisqueraDao;
import org.sanchez.pixup.model.Disquera;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DisqueraDaoImplTest {

    private DisqueraDao disqueraDao;

    @BeforeEach
    void setUp() {
        disqueraDao = DisqueraDaoImpl.getInstance();
    }

    @Test
    void getInstance() {
        assertNotNull(disqueraDao, "La instancia de DisqueraDao no debería ser nula.");
    }

    @Test
    void findAll() {
        List<Disquera> disqueras = disqueraDao.findAll();
        assertNotNull(disqueras, "La lista de disqueras no debería ser nula.");
        assertFalse(disqueras.isEmpty(), "La lista de disqueras no debería estar vacía.");

        System.out.println("Lista de disqueras encontradas:");
        disqueras.forEach(disquera ->
                System.out.println("ID: " + disquera.getId() + " | Nombre: " + disquera.getNombre()));
    }


    @Test
    void save() {
        Disquera disquera = new Disquera();
        disquera.setNombre("Sony Music");

        boolean resultado = disqueraDao.save(disquera);
        assertTrue(resultado, "La disquera debería haberse guardado exitosamente.");

        List<Disquera> disqueras = disqueraDao.findAll();
        assertTrue(disqueras.stream().anyMatch(d -> d.getNombre().equals("Sony Music")),
                "La disquera 'Sony Music' debería existir en la lista.");
    }

    @Test
    void update() {
        Disquera disquera = disqueraDao.findById(1);
        assertNotNull(disquera, "La disquera con ID 1 debería existir.");

        disquera.setNombre("Sony Music (Updated)");
        boolean resultado = disqueraDao.update(disquera);
        assertTrue(resultado, "La disquera debería actualizarse correctamente.");

        Disquera disqueraActualizada = disqueraDao.findById(1);
        assertEquals("Sony Music (Updated)", disqueraActualizada.getNombre(),
                "El nombre de la disquera debería haberse actualizado.");
    }

    @Test
    void delete() {
        Disquera disquera = disqueraDao.findById(2);
        assertNotNull(disquera, "La disquera con ID 2 debería existir antes de eliminarse.");

        boolean resultado = disqueraDao.delete(disquera);
        assertTrue(resultado, "La disquera debería eliminarse correctamente.");

        Disquera disqueraEliminada = disqueraDao.findById(2);
        assertNull(disqueraEliminada, "La disquera con ID 2 ya no debería existir después de la eliminación.");
    }

    @Test
    void findById() {
        Disquera disquera = disqueraDao.findById(1);
        assertNotNull(disquera, "La disquera con ID 1 debería existir.");
        assertEquals(1, disquera.getId(), "El ID de la disquera debería coincidir.");

        System.out.println("Disquera encontrada:");
        System.out.println("ID: " + disquera.getId());
        System.out.println("Nombre: " + disquera.getNombre());
    }

}
