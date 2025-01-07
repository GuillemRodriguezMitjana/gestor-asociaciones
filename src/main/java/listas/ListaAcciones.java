package listas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import datos.Accion;
import datos.Asociacion;
import datos.Charla;
import datos.Demostracion;
import datos.Fecha;
import datos.Miembro;
import excepciones.ExcepcionMaximoValoraciones;

/**
 * Clase que representa una lista de acciones (charlas y demostraciones) 
 * para gestionar, agregar, buscar y almacenar dichas acciones.
 */
public class ListaAcciones {
    private static Accion[] lista;
    private static int nElem;

    /**
     * Constructor que inicializa la lista de acciones con un tamaño fijo de 100 elementos.
     */
    public ListaAcciones() {
        lista = new Accion[100]; // Tamaño fijo inicial
        nElem = 0;
    }

    /**
     * Agrega una nueva acción a la lista.
     * @param accion La acción a agregar.
     * @return true si la acción se agregó correctamente, false si la lista está llena.
     */
    public static boolean agregarAccion(Accion accion) {
        if (nElem < lista.length) {
            lista[nElem++] = accion;
            return true;
        }
        return false;
    }

    /**
     * Obtiene una acción de la lista dado su índice.
     * @param index El índice de la acción.
     * @return La acción en el índice dado, o null si el índice es inválido.
     */
    public Accion obtenerAccion(int index) {
        if (index >= 0 && index < nElem) {
            return lista[index];
        }
        return null;
    }

    /**
     * Obtiene el número de elementos en la lista.
     * @return El número de elementos en la lista.
     */
    public int getNElem() {
        return nElem;
    }

    /**
     * Muestra todas las acciones, opcionalmente filtradas por tipo (charla o demostración).
     * @param tipoFiltro El tipo de acción a mostrar ("charla" o "demostracion"). Si es null o vacío, muestra todas.
     */
    public static void mostrarAcciones(String tipoFiltro) {
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

    /**
     * Muestra las acciones asociadas a una asociación específica.
     * @param asociacion La asociación cuyas acciones se desean mostrar.
     */
    public static void mostrarAccionesPorAsociacion(Asociacion asociacion) {
        if (asociacion != null) {
            System.out.println("Acciones de la asociación: " + asociacion.getName());
            asociacion.getAcciones();
            ListaAcciones.mostrarAcciones("");
        } else {
            System.out.println("La asociación no existe o no tiene acciones registradas.");
        }
    }

    /**
     * Agrega una nueva charla asociada a una asociación.
     * @param asociacion La asociación a la que pertenece la nueva charla.
     */
    public static void agregarNuevaCharla(Asociacion asociacion) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Añadir nueva charla para la asociación: " + asociacion.getName());
            
            System.out.print("Título de la charla: ");
            String titulo = scanner.nextLine();
        
            System.out.print("Fecha de la charla (dd/mm/yyyy): ");
            String fecha1 = scanner.nextLine();
            Fecha fecha = Fecha.parse(fecha1);
        
            System.out.print("Responsable (alias): ");
            String responsableAlias = scanner.nextLine();
            Miembro responsable = asociacion.getMiembros().buscarMiembro(responsableAlias);
            if (responsable == null) {
                System.out.println("Responsable no encontrado en la asociación.");
            }
        
            String codigo = asociacion.getName().substring(0, 3).toUpperCase() + (nElem + 100);
        
            Charla nuevaCharla = new Charla(codigo, titulo, responsable, fecha);
        
            nuevaCharla.agregarImpartidor(responsable);
        
            System.out.print("Número de asistentes a la charla: ");
            int numAsistentes = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < numAsistentes; i++) {
                nuevaCharla.incrementarAsistentes();
            }
        
            System.out.println("Introduce las valoraciones de los asistentes (entre 0 y 10):");
            for (int i = 0; i < numAsistentes; i++) {
                System.out.print("Valoración del asistente " + (i + 1) + ": ");
                int valoracion = Integer.parseInt(scanner.nextLine());
                try {
                    nuevaCharla.agregarValoracion(valoracion);
                } catch (ExcepcionMaximoValoraciones e) {
                    System.out.println("Error al añadir valoración: " + e.getMessage());
                }
            }
        
            if (agregarAccion(nuevaCharla)) {
                System.out.println("Charla añadida con éxito: " + nuevaCharla);
            } else {
                System.out.println("Error: No se pudo añadir la charla.");
            }
        }
    }

    /**
     * Agrega una nueva demostración asociada a una asociación.
     * @param asociacion La asociación a la que pertenece la nueva demostración.
     */
    public static void agregarNuevaDemostracion(Asociacion asociacion) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Añadir nueva demostración para la asociación: " + asociacion.getName());
        
            System.out.print("Título de la demostración: ");
            String titulo = scanner.nextLine();
        
            System.out.print("Fecha de diseño (dd/mm/yyyy): ");
            String fechaStr = scanner.nextLine();
            Fecha fechaDiseño = Fecha.parse(fechaStr);
        
            System.out.print("Responsable (alias): ");
            String responsableAlias = scanner.nextLine();
            Miembro responsable = asociacion.getMiembros().buscarMiembro(responsableAlias);
        
            if (responsable == null) {
                System.out.println("Responsable no encontrado en la asociación.");
            }
        
            System.out.print("Costo de los materiales: ");
            double costeMateriales = Double.parseDouble(scanner.nextLine());
        
            String codigo = asociacion.getName().substring(0, 3).toUpperCase() + (nElem + 200);
        
            Demostracion nuevaDemostracion = new Demostracion(codigo, titulo, responsable, fechaDiseño, costeMateriales);
        
            System.out.print("¿Está activa? (true/false): ");
            boolean activa = Boolean.parseBoolean(scanner.nextLine());
            nuevaDemostracion.setActiva(activa);
        
            System.out.print("Número de veces ofrecida: ");
            int vecesOfrecida = Integer.parseInt(scanner.nextLine());
            nuevaDemostracion.setVecesOfrecida(vecesOfrecida);
        
            nuevaDemostracion.setResponsable(responsable);
        
            if (agregarAccion(nuevaDemostracion)) {
                System.out.println("Demostración añadida con éxito: " + nuevaDemostracion);
            } else {
                System.out.println("Error: No se pudo añadir la demostración.");
            }
        }
    }

    /**
     * Muestra las charlas que tienen más asistentes que un número mínimo especificado.
     * @param minimoAsistentes Número mínimo de asistentes para mostrar la charla.
     */
    public static void mostrarCharlasConMasAsistentes(int minimoAsistentes) {
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

    /**
     * Muestra la charla mejor valorada según el promedio de sus valoraciones.
     */
    public static void mostrarCharlaMejorValorada() {
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

    /**
     * Lee las acciones desde un archivo de texto y las agrega a la lista.
     */
    public void LlegirFitxer() {
        try {
            BufferedReader f = new BufferedReader(new FileReader("acciones.txt"));
            String linea = f.readLine();
            while (linea != null) {
                StringTokenizer coma = new StringTokenizer(linea, ";");

                String tipo = coma.nextToken();
                String codigo = coma.nextToken();
                String titulo = coma.nextToken();

                if (tipo.equalsIgnoreCase("Charla")) {
                    String fechaStr = coma.nextToken();
                    Fecha fecha = Fecha.parse(fechaStr);
                    String impartidor = coma.nextToken();
                    int numAsistentes = Integer.parseInt(coma.nextToken());

                    // Crear charla
                    Charla charla = new Charla(codigo, titulo, null, fecha);

                    // Procesar valoraciones
                    if (coma.hasMoreTokens()) {
                        String[] valoracionesStr = coma.nextToken().split(",");
                        for (int i = 0; i < valoracionesStr.length; i++) {
                            int valoracion = Integer.parseInt(valoracionesStr[i].trim());
                            charla.agregarValoracion(valoracion);
                        }
                    }

                    for (int i = 0; i < numAsistentes; i++) {
                        charla.incrementarAsistentes();
                    }

                    ListaAcciones.agregarAccion(charla);
                } else if (tipo.equalsIgnoreCase("Demostracion")) {
                    // Similar lógica para demostraciones
                    String fechaStr = coma.nextToken();
                    Fecha fechaDisenyo = Fecha.parse(fechaStr);
                    boolean esValida = Boolean.parseBoolean(coma.nextToken());
                    int vecesOfrecida = Integer.parseInt(coma.nextToken());
                    double costoMateriales = Double.parseDouble(coma.nextToken());

                    Demostracion demostracion = new Demostracion(codigo, titulo, null, fechaDisenyo, costoMateriales);
                    demostracion.setActiva(esValida);
                    demostracion.setVecesOfrecida(vecesOfrecida);

                    ListaAcciones.agregarAccion(demostracion);
                } 
                linea = f.readLine();
            }
            f.close();
        } catch (Exception e) {
            System.out.println("");
        }
    }

    /**
     * Escribe las acciones de la lista en un archivo de texto.
     */
    public void EscriureFitxer() {
        try {
            BufferedWriter f = new BufferedWriter(new FileWriter("acciones.txt"));
            for (int i = 0; i < nElem; i++) {
                Accion accion = lista[i];

                if (accion instanceof Charla) {
                    Charla charla = (Charla) accion;

                    f.write("Charla;" +
                            charla.getCodigo() + ";" +
                            charla.getTitulo() + ";" +
                            charla.getFechaCharla() + ";" +
                            charla.getNumAsistentes() + ";");

                    int[] valoraciones = charla.getValoraciones();
                    for (int j = 0; j < charla.getNumAsistentes(); j++) {
                        f.write(valoraciones[j] + (j < charla.getNumAsistentes() - 1 ? "," : ""));
                    }
                    f.newLine();

                } else if (accion instanceof Demostracion) {
                    Demostracion demostracion = (Demostracion) accion;

                    f.write("Demostracion;" +
                            demostracion.getCodigo() + ";" +
                            demostracion.getTitulo() + ";" +
                            demostracion.getFechaDiseño() + ";" +
                            demostracion.isActiva() + ";" +
                            demostracion.getVecesOfrecida() + ";" +
                            demostracion.getCosteMateriales() + "\n");
                }
            }
            f.close();
        } catch (IOException e) {
            System.out.println("S'ha produit un error en els arxius.");
        }
    }

    /**
     * Busca una acción por su código.
     * @param codigo El código de la acción.
     * @return La acción correspondiente al código, o null si no se encuentra.
     */
    public Accion buscarAccionPorCodigo(String codigo) {
        for (int i = 0; i < nElem; i++) {
            if (lista[i].getCodigo().equals(codigo)) {
                return lista[i];
            }
        }
        return null;
    }

    /**
     * Elimina una acción de la lista según su índice.
     * @param index El índice de la acción a eliminar.
     */
    public void eliminarAccion(int index) {
        if (index >= 0 && index < nElem) {
            for (int i = index; i < nElem - 1; i++) {
                lista[i] = lista[i + 1];
            }
            lista[nElem - 1] = null; 
            nElem--;
        }
    }

    /**
     * Devuelve una string.
     * @return Una cadena con la lista de acciones.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de Acciones:\n");
        for (int i = 0; i < nElem; i++) {
            sb.append(lista[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
