package org.sanchez.pixup.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sanchez.pixup.dao.DiscoDao;
import org.sanchez.pixup.model.Disco;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiscoDaoImplTest {

    private DiscoDao discoDao;

    @BeforeEach
    void setUp() {
        discoDao = DiscoDaoImpl.getInstance();
    }

    @Test
    void getInstance() {
        assertNotNull(discoDao, "La instancia de DiscoDao no debería ser nula.");
    }

    @Test
    void findAll() {
        List<Disco> discos = discoDao.findAll();
        assertNotNull(discos, "La lista de discos no debería ser nula.");
        assertFalse(discos.isEmpty(), "La lista de discos no debería estar vacía.");

        System.out.println("Lista de discos encontrados:");
        discos.forEach(disco -> System.out.println("ID: " + disco.getId() + " | Título: " + disco.getTitulo()));

        assertTrue(discos.size() > 0, "Debe haber al menos un disco registrado.");
    }



    @Test
    void save() {
        Disco disco = new Disco();
        disco.setTitulo("Parachutes");
        disco.setPrecio(199.99f);
        disco.setExistencia(15);
        disco.setDescuento(10.0f);
        disco.setFechaLanzamiento(LocalDate.of(2000, 7, 10));
        disco.setImagen("imagen_parachutes.jpg");
        disco.setArtistaId(1);
        disco.setDisqueraId(1);
        disco.setGeneroId(1);

        boolean resultado = discoDao.save(disco);
        assertTrue(resultado, "El disco debería haberse guardado exitosamente.");

        List<Disco> discos = discoDao.findAll();
        assertTrue(discos.stream().anyMatch(d -> d.getTitulo().equals("Parachutes")),
                "El disco 'Parachutes' debería existir en la lista.");
    }

    @Test
    void update() {
        Disco disco = discoDao.findById(1);
        assertNotNull(disco, "El disco con ID 1 debería existir.");

        disco.setTitulo("Parachutes (Remastered)");
        boolean resultado = discoDao.update(disco);
        assertTrue(resultado, "El disco debería actualizarse correctamente.");

        Disco discoActualizado = discoDao.findById(1);
        assertEquals("Parachutes (Remastered)", discoActualizado.getTitulo(),
                "El título del disco debería haberse actualizado.");
    }

    @Test
    void delete() {
        Disco disco = discoDao.findById(2);
        assertNotNull(disco, "El disco con ID 2 debería existir antes de eliminarse.");

        boolean resultado = discoDao.delete(disco);
        assertTrue(resultado, "El disco debería eliminarse correctamente.");

        Disco discoEliminado = discoDao.findById(2);
        assertNull(discoEliminado, "El disco con ID 2 ya no debería existir después de la eliminación.");
    }

    @Test
    void findById() {
        Disco disco = discoDao.findById(1);
        assertNotNull(disco, "El disco con ID 1 debería existir.");
        assertEquals(1, disco.getId(), "El ID del disco debería coincidir.");

        System.out.println("Disco encontrado:");
        System.out.println("ID: " + disco.getId());
        System.out.println("Título: " + disco.getTitulo());
        System.out.println("Precio: $" + disco.getPrecio());
        System.out.println("Existencia: " + disco.getExistencia());
        System.out.println("Descuento: " + disco.getDescuento() + "%");
        System.out.println("Fecha de lanzamiento: " + disco.getFechaLanzamiento());
        System.out.println("Imagen URL: " + disco.getImagen());
        System.out.println("Artista ID: " + disco.getArtistaId());
        System.out.println("Disquera ID: " + disco.getDisqueraId());
        System.out.println("Género ID: " + disco.getGeneroId());
    }

}
