package listas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
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
    
                    System.out.println("Charla creada: " + charla);
                    this.agregarAccion(charla);
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
    
                    System.out.println("Demostración creada: " + demostracion);
                    this.agregarAccion(demostracion);
                } else {
                    System.out.println("Tipo de acción desconocido: " + tipo);
                }
    
                linea = f.readLine();
            }
            f.close();
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    
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

    public Accion buscarAccionPorCodigo(String codigo) {
        for (int i = 0; i < nElem; i++) {
            if (lista[i].getCodigo().equals(codigo)) {
                return lista[i];
            }
        }
        return null;
    }

    public void eliminarAccion(int index) {
        if (index >= 0 && index < nElem) {
            for (int i = index; i < nElem - 1; i++) {
                lista[i] = lista[i + 1];
            }
            lista[nElem - 1] = null; 
            nElem--;
        }
    }
    

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
