package listas;

import datos.Accion;
import datos.Asociacion;
import datos.Charla;
import datos.Miembro;
import java.util.Scanner;

public class ListaAcciones {

    private Accion[] lista;
    private int nElem;

    public ListaAcciones() {
        lista = new Accion[100]; // Tamaño fijo inicial
        nElem = 0;
    }

    public boolean agregarAccion(Accion accion) {
        if (nElem < lista.length) {
            lista[nElem++] = accion;
            return true;
        }
        return false;
    }

    public Accion obtenerAccion(int index) {
        if (index >= 0 && index < nElem) {
            return lista[index];
        }
        return null;
    }

    public int getNElem() {
        return nElem;
    }

    // Método para mostrar todas las acciones con filtro opcional
    public void mostrarAcciones(String tipoFiltro) {
        boolean encontrado = false;
        for (int i = 0; i < nElem; i++) {
            Accion accion = lista[i];
            if (tipoFiltro == null || tipoFiltro.isEmpty()) {
                System.out.println(accion);
                encontrado = true;
            } else {
                if (tipoFiltro.equalsIgnoreCase("charla") && accion instanceof datos.Charla) {
                    System.out.println(accion);
                    encontrado = true;
                } else if (tipoFiltro.equalsIgnoreCase("demostracion") && accion instanceof datos.Demostracion) {
                    System.out.println(accion);
                    encontrado = true;
                }
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron acciones del tipo: " + tipoFiltro);
        }
    }

    // Nuevo método: mostrar acciones de una asociación específica
    public void mostrarAccionesPorAsociacion(Asociacion asociacion) {
        if (asociacion != null) {
            System.out.println("Acciones de la asociación: " + asociacion.getName());
            asociacion.getAcciones().mostrarAcciones("");
        } else {
            System.out.println("La asociación no existe o no tiene acciones registradas.");
        }
    }

    // Nuevo método: añadir una nueva charla
    public void agregarNuevaCharla(Asociacion asociacion) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Añadir nueva charla para la asociación: " + asociacion.getName());
        
        System.out.print("Título de la charla: ");
        String titulo = scanner.nextLine();

        System.out.print("Fecha de la charla (dd/mm/yyyy): ");
        String fecha = scanner.nextLine();

        System.out.print("Responsable (alias): ");
        String responsableAlias = scanner.nextLine();
        Miembro responsable = asociacion.getMiembros().buscarMiembro(responsableAlias);
        scanner.close();
        if (responsable == null) {
            System.out.println("Responsable no encontrado en la asociación.");
            return;
        }

        // Generar código (3 primeras letras + número secuencial)
        String codigo = asociacion.getName().substring(0, 3).toUpperCase() + (nElem + 100);

        // Crear la nueva charla y añadirla
        Charla nuevaCharla = new Charla(codigo, titulo, responsable, fecha);
        if (agregarAccion(nuevaCharla)) {
            System.out.println("Charla añadida con éxito: " + nuevaCharla);
        } else {
            System.out.println("Error: No se pudo añadir la charla.");
        }
    }

    // Nuevo método: Mostrar charlas con más asistentes que un número indicado
    public void mostrarCharlasConMasAsistentes(int minimoAsistentes) {
        boolean encontrado = false;
        for (int i = 0; i < nElem; i++) {
            if (lista[i] instanceof Charla) {
                Charla charla = (Charla) lista[i];
                if (charla.getNumAsistentes() > minimoAsistentes) {
                    System.out.println(charla);
                    encontrado = true;
                }
            }
        }
        if (!encontrado) {
            System.out.println("No hay charlas con más de " + minimoAsistentes + " asistentes.");
        }
    }

    // Nuevo método: Mostrar la charla mejor valorada
    public void mostrarCharlaMejorValorada() {
        Charla mejorCharla = null;
        double mejorValoracion = 0;
        int maxValoraciones = 0;

        for (int i = 0; i < nElem; i++) {
            if (lista[i] instanceof Charla) {
                Charla charla = (Charla) lista[i];
                double promedio = charla.obtenerPromedioValoraciones();
                int valoraciones = charla.getValoraciones().length;

                if (promedio > mejorValoracion || (promedio == mejorValoracion && valoraciones > maxValoraciones)) {
                    mejorCharla = charla;
                    mejorValoracion = promedio;
                    maxValoraciones = valoraciones;
                }
            }
        }

        if (mejorCharla != null) {
            System.out.println("La charla mejor valorada es: \n" + mejorCharla);
        } else {
            System.out.println("No hay charlas registradas o valoradas.");
        }
    }
}
