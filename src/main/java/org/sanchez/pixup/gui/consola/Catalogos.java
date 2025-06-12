package org.sanchez.pixup.gui.consola;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sanchez.pixup.negocio.LecturaAccion;
import org.sanchez.pixup.hibernate.HibernateUtil;
import org.sanchez.pixup.model.Catalogo;
import org.sanchez.pixup.util.ReadUtil;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class Catalogos<T extends Catalogo> extends LecturaAccion {

    protected boolean flag2;
    private final Class<T> classType;

    @SuppressWarnings("unchecked")
    public Catalogos() {
        this.classType = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void print() {
        try (Session session = HibernateUtil.getSession()) {
            List<T> list = session.createQuery("FROM " + classType.getSimpleName(), classType).list();
            if (list.isEmpty()) {
                System.out.println("No hay elementos en la base de datos.");
            } else {
                list.forEach(System.out::println);
            }
        }
    }

    public abstract T newT();
    public abstract boolean processNewT(T t);
    public abstract void processEditT(T t);

    public void add() {
        T t = newT();
        if (processNewT(t)) {
            try (Session session = HibernateUtil.getSession()) {
                Transaction transaction = session.beginTransaction();
                session.persist(t);
                transaction.commit();
                System.out.println("Elemento guardado en la base de datos.");
            } catch (Exception e) {
                System.out.println("Error al guardar el elemento: " + e.getMessage());
            }
        }
    }

    public void edit() {
        try (Session session = HibernateUtil.getSession()) {
            System.out.println("Ingrese el ID del elemento a editar");
            print();
            T t = session.get(classType, ReadUtil.readInt());
            if (t == null) {
                System.out.println("ID incorrecto, intentelo nuevamente.");
            } else {
                processEditT(t);
                Transaction transaction = session.beginTransaction();
                session.merge(t);
                transaction.commit();
                System.out.println("Elemento modificado en la base de datos.");
            }
        } catch (Exception e) {
            System.out.println("Error al modificar el elemento: " + e.getMessage());
        }
    }

    public void remove() {
        try (Session session = HibernateUtil.getSession()) {
            System.out.println("Ingrese el ID del elemento a borrar");
            print();
            T t = session.get(classType, ReadUtil.readInt());
            if (t == null) {
                System.out.println("ID incorrecto, intentelo nuevamente.");
            } else {
                Transaction transaction = session.beginTransaction();
                session.delete(t);
                transaction.commit();
                System.out.println("Elemento eliminado de la base de datos.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el elemento: " + e.getMessage());
        }
    }

    @Override
    public void procesaOpcion() {
        switch (opcion) {
            case 1: add(); break;
            case 2 : edit();break;
            case 3 : remove();break;
            case 4 : print();break;
            case 5 : System.out.println("Saliendo del menú...");
        }
    }

    @Override
    public void despliegaMenu() {
        System.out.println("Seleccione una opción:");
        System.out.println("1.- Agregar");
        System.out.println("2.- Editar");
        System.out.println("3.- Borrar");
        System.out.println("4.- Imprimir");
        System.out.println("5.- Salir");
    }

    @Override
    public int valorMinMenu() {
        return 1;
    }

    @Override
    public int valorMaxMenu() {
        return 5;
    }

}
