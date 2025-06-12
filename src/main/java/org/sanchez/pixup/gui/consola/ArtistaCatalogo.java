package org.sanchez.pixup.gui.consola;

import org.sanchez.pixup.model.Artista;
import org.sanchez.pixup.util.ReadUtil;

public class ArtistaCatalogo extends Catalogos<Artista> {

    private static ArtistaCatalogo artistaCatalogo;

    public static ArtistaCatalogo getInstance() {
        if (artistaCatalogo == null) {
            artistaCatalogo = new ArtistaCatalogo();
        }
        return artistaCatalogo;
    }

    @Override
    public Artista newT() {
        return new Artista();
    }

    @Override
    public boolean processNewT(Artista artista) {
        System.out.println("Ingrese los datos del artista:");
        System.out.print("Nombre: ");
        artista.setNombre(ReadUtil.read());
        return true;
    }

    @Override
    public void processEditT(Artista artista) {
        System.out.println("Editando artista con ID: " + artista.getId());
        System.out.println("Nombre actual: " + artista.getNombre());
        System.out.print("Nuevo nombre (Enter para mantener el mismo): ");
        String nuevoNombre = ReadUtil.read();
        if (!nuevoNombre.isEmpty()) {
            artista.setNombre(nuevoNombre);
        }
    }
    public String toString(Artista artista) {
        return "Artista(id=" + artista.getId() + ", nombre='" + artista.getNombre() + "')";
    }

}