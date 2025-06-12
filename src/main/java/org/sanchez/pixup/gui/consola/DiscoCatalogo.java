package org.sanchez.pixup.gui.consola;

import org.sanchez.pixup.dao.DiscoDao;
import org.sanchez.pixup.dao.impl.DiscoDaoImpl;
import org.sanchez.pixup.model.Disco;
import org.sanchez.pixup.util.ReadUtil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DiscoCatalogo extends Catalogos<Disco> {

    private static DiscoCatalogo discoCatalogo;
    private final DiscoDao discoDao = DiscoDaoImpl.getInstance();

    private DiscoCatalogo() {
        super();
    }

    public static DiscoCatalogo getInstance() {
        if (discoCatalogo == null) {
            discoCatalogo = new DiscoCatalogo();
        }
        return discoCatalogo;
    }

    @Override
    public Disco newT() {
        return new Disco();
    }

    @Override
    public boolean processNewT(Disco disco) {
        System.out.println("Ingrese los datos del disco:");
        System.out.print("Título: ");
        disco.setTitulo(ReadUtil.read());

        System.out.print("Precio: ");
        disco.setPrecio(Float.parseFloat(ReadUtil.read()));

        System.out.print("Existencia: ");
        disco.setExistencia(Integer.parseInt(ReadUtil.read()));

        System.out.print("Descuento (opcional, presione Enter si no aplica): ");
        String descuento = ReadUtil.read();
        disco.setDescuento(descuento.isEmpty() ? null : Float.parseFloat(descuento));

        System.out.print("Fecha de lanzamiento (YYYY-MM-DD): ");
        disco.setFechaLanzamiento(LocalDate.parse(ReadUtil.read()));

        System.out.print("URL de la imagen: ");
        disco.setImagen(ReadUtil.read());

        System.out.print("ID del Artista: ");
        disco.setArtistaId(Integer.parseInt(ReadUtil.read()));

        System.out.print("ID de la Disquera: ");
        disco.setDisqueraId(Integer.parseInt(ReadUtil.read()));

        System.out.print("ID del Género: ");
        disco.setGeneroId(Integer.parseInt(ReadUtil.read()));

        boolean guardado = discoDao.save(disco);
        if (guardado) {
            System.out.println("Disco guardado en la base de datos.");
        } else {
            System.out.println("Error al guardar el disco.");
        }

        return guardado;
    }

    @Override
    public void processEditT(Disco disco) {
        System.out.println("Editando disco con ID: " + disco.getId());

        System.out.println("Título actual: " + disco.getTitulo());
        System.out.print("Nuevo título (Enter para mantener el mismo): ");
        String nuevoTitulo = ReadUtil.read();
        if (!nuevoTitulo.isEmpty()) {
            disco.setTitulo(nuevoTitulo);
        }

        System.out.println("Precio actual: " + disco.getPrecio());
        System.out.print("Nuevo precio (Enter para mantener el mismo): ");
        String nuevoPrecio = ReadUtil.read();
        if (!nuevoPrecio.isEmpty()) {
            disco.setPrecio(Float.parseFloat(nuevoPrecio));
        }

        System.out.println("Existencia actual: " + disco.getExistencia());
        System.out.print("Nueva existencia (Enter para mantener el mismo): ");
        String nuevaExistencia = ReadUtil.read();
        if (!nuevaExistencia.isEmpty()) {
            disco.setExistencia(Integer.parseInt(nuevaExistencia));
        }

        System.out.println("Descuento actual: " + disco.getDescuento());
        System.out.print("Nuevo descuento (Enter para mantener el mismo): ");
        String nuevoDescuento = ReadUtil.read();
        if (!nuevoDescuento.isEmpty()) {
            disco.setDescuento(Float.parseFloat(nuevoDescuento));
        }

        System.out.println("Fecha de lanzamiento actual: " + disco.getFechaLanzamiento());
        System.out.print("Nueva fecha de lanzamiento (YYYY-MM-DD) (Enter para mantener la misma): ");
        String nuevaFecha = ReadUtil.read();

        if (!nuevaFecha.isEmpty()) {
            try {
                disco.setFechaLanzamiento(LocalDate.parse(nuevaFecha));
            } catch (DateTimeParseException e) {
                System.out.println("Error: Formato de fecha inválido. Use YYYY-MM-DD.");
            }
        }

        System.out.println("URL de imagen actual: " + disco.getImagen());
        System.out.print("Nueva URL de imagen (Enter para mantener la misma): ");
        String nuevaImagen = ReadUtil.read();
        if (!nuevaImagen.isEmpty()) {
            disco.setImagen(nuevaImagen);
        }

        System.out.println("ID del artista actual: " + disco.getArtistaId());
        System.out.print("Nuevo ID de artista (Enter para mantener el mismo): ");
        String nuevoArtistaId = ReadUtil.read();
        if (!nuevoArtistaId.isEmpty()) {
            disco.setArtistaId(Integer.parseInt(nuevoArtistaId));
        }

        System.out.println("ID de la disquera actual: " + disco.getDisqueraId());
        System.out.print("Nuevo ID de disquera (Enter para mantener el mismo): ");
        String nuevoDisqueraId = ReadUtil.read();
        if (!nuevoDisqueraId.isEmpty()) {
            disco.setDisqueraId(Integer.parseInt(nuevoDisqueraId));
        }

        System.out.println("ID del género actual: " + disco.getGeneroId());
        System.out.print("Nuevo ID de género (Enter para mantener el mismo): ");
        String nuevoGeneroId = ReadUtil.read();
        if (!nuevoGeneroId.isEmpty()) {
            disco.setGeneroId(Integer.parseInt(nuevoGeneroId));
        }

        boolean actualizado = discoDao.update(disco);
        if (actualizado) {
            System.out.println("Disco actualizado en la base de datos.");
        } else {
            System.out.println("Error al actualizar el disco.");
        }
    }

    public String toString(Disco disco) {
        return "Disco{id=" + disco.getId() + ", titulo='" + disco.getTitulo() + "', precio=" + disco.getPrecio() + "}";
    }
}
