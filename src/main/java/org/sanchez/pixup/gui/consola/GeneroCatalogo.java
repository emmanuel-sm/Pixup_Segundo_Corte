package org.sanchez.pixup.gui.consola;

import org.sanchez.pixup.dao.GeneroDao;
import org.sanchez.pixup.dao.impl.GeneroDaoImpl;
import org.sanchez.pixup.model.Genero;
import org.sanchez.pixup.util.ReadUtil;

public class GeneroCatalogo extends Catalogos<Genero> {

    private static GeneroCatalogo generoCatalogo;
    private final GeneroDao generoDao = GeneroDaoImpl.getInstance();

    private GeneroCatalogo() {
        super();
    }

    public static GeneroCatalogo getInstance() {
        if (generoCatalogo == null) {
            generoCatalogo = new GeneroCatalogo();
        }
        return generoCatalogo;
    }

    @Override
    public Genero newT() {
        return new Genero();
    }

    @Override
    public boolean processNewT(Genero genero) {
        System.out.println("Ingrese la descripción del género:");
        System.out.print("Descripción: ");
        genero.setDescripcion(ReadUtil.read());

        boolean guardado = generoDao.save(genero);
        if (guardado) {
            System.out.println("Género guardado en la base de datos.");
        } else {
            System.out.println("Error al guardar el género.");
        }

        return guardado;
    }

    @Override
    public void processEditT(Genero genero) {
        System.out.println("Editando género con ID: " + genero.getId());

        System.out.println("Descripción actual: " + genero.getDescripcion());
        System.out.print("Nueva descripción (Enter para mantener la misma): ");
        String nuevaDescripcion = ReadUtil.read();
        if (!nuevaDescripcion.isEmpty()) {
            genero.setDescripcion(nuevaDescripcion);
        }

        boolean actualizado = generoDao.update(genero);
        if (actualizado) {
            System.out.println("Género actualizado en la base de datos.");
        } else {
            System.out.println("Error al actualizar el género.");
        }
    }

    public String toString(Genero genero) {
        return "Genero{id=" + genero.getId() + ", descripcion='" + genero.getDescripcion() + "'}";
    }
}
