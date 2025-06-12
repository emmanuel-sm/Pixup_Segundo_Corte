package org.sanchez.pixup.gui.consola;

import org.sanchez.pixup.dao.CancionDao;
import org.sanchez.pixup.dao.impl.CancionDaoImpl;
import org.sanchez.pixup.model.Cancion;
import org.sanchez.pixup.util.ReadUtil;

public class CancionCatalogo extends Catalogos<Cancion> {

    private static CancionCatalogo cancionCatalogo;
    private final CancionDao cancionDao = CancionDaoImpl.getInstance();

    private CancionCatalogo() {
        super();
    }

    public static CancionCatalogo getInstance() {
        if (cancionCatalogo == null) {
            cancionCatalogo = new CancionCatalogo();
        }
        return cancionCatalogo;
    }

    @Override
    public Cancion newT() {
        return new Cancion();
    }

    @Override
    public boolean processNewT(Cancion cancion) {
        System.out.println("Ingrese los datos de la canción:");
        System.out.print("Título: ");
        cancion.setTitulo(ReadUtil.read());

        System.out.print("Duración (en segundos): ");
        cancion.setDuracion(Integer.parseInt(ReadUtil.read()));

        System.out.print("ID del Disco: ");
        cancion.setDiscoId(Integer.parseInt(ReadUtil.read()));

        boolean guardado = cancionDao.save(cancion);
        if (guardado) {
            System.out.println("Canción guardada en la base de datos.");
        } else {
            System.out.println("Error al guardar la canción.");
        }

        return guardado;
    }

    @Override
    public void processEditT(Cancion cancion) {
        System.out.println("Editando canción con ID: " + cancion.getId());

        System.out.println("Título actual: " + cancion.getTitulo());
        System.out.print("Nuevo título (Enter para mantener el mismo): ");
        String nuevoTitulo = ReadUtil.read();
        if (!nuevoTitulo.isEmpty()) {
            cancion.setTitulo(nuevoTitulo);
        }

        System.out.println("Duración actual: " + cancion.getDuracion() + " segundos");
        System.out.print("Nueva duración (Enter para mantener la misma): ");
        String nuevaDuracion = ReadUtil.read();
        if (!nuevaDuracion.isEmpty()) {
            cancion.setDuracion(Integer.parseInt(nuevaDuracion));
        }

        System.out.println("ID del Disco actual: " + cancion.getDiscoId());
        System.out.print("Nuevo ID del Disco (Enter para mantener el mismo): ");
        String nuevoDiscoId = ReadUtil.read();
        if (!nuevoDiscoId.isEmpty()) {
            cancion.setDiscoId(Integer.parseInt(nuevoDiscoId));
        }

        boolean actualizado = cancionDao.update(cancion);
        if (actualizado) {
            System.out.println("Canción actualizada en la base de datos.");
        } else {
            System.out.println("Error al actualizar la canción.");
        }
    }

    public String toString(Cancion cancion) {
        return "Cancion{id=" + cancion.getId() + ", titulo='" + cancion.getTitulo() + "', duración=" + cancion.getDuracion() + " segundos}";
    }
}
