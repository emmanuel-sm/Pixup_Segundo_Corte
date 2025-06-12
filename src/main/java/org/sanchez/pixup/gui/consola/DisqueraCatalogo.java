package org.sanchez.pixup.gui.consola;

import org.sanchez.pixup.dao.DisqueraDao;
import org.sanchez.pixup.dao.impl.DisqueraDaoImpl;
import org.sanchez.pixup.model.Disquera;
import org.sanchez.pixup.util.ReadUtil;

public class DisqueraCatalogo extends Catalogos<Disquera> {

    private static DisqueraCatalogo disqueraCatalogo;
    private final DisqueraDao disqueraDao = DisqueraDaoImpl.getInstance();

    private DisqueraCatalogo() {
        super();
    }

    public static DisqueraCatalogo getInstance() {
        if (disqueraCatalogo == null) {
            disqueraCatalogo = new DisqueraCatalogo();
        }
        return disqueraCatalogo;
    }

    @Override
    public Disquera newT() {
        return new Disquera();
    }

    @Override
    public boolean processNewT(Disquera disquera) {
        System.out.println("Ingrese los datos de la disquera:");
        System.out.print("Nombre: ");
        disquera.setNombre(ReadUtil.read());

        boolean guardado = disqueraDao.save(disquera);
        if (guardado) {
            System.out.println("Disquera guardada en la base de datos.");
        } else {
            System.out.println("Error al guardar la disquera.");
        }

        return guardado;
    }

    @Override
    public void processEditT(Disquera disquera) {
        System.out.println("Editando disquera con ID: " + disquera.getId());

        System.out.println("Nombre actual: " + disquera.getNombre());
        System.out.print("Nuevo nombre (Enter para mantener el mismo): ");
        String nuevoNombre = ReadUtil.read();
        if (!nuevoNombre.isEmpty()) {
            disquera.setNombre(nuevoNombre);
        }

        boolean actualizado = disqueraDao.update(disquera);
        if (actualizado) {
            System.out.println("Disquera actualizada en la base de datos.");
        } else {
            System.out.println("Error al actualizar la disquera.");
        }
    }

    public String toString(Disquera disquera) {
        return "Disquera{id=" + disquera.getId() + ", nombre='" + disquera.getNombre() + "'}";
    }
}
