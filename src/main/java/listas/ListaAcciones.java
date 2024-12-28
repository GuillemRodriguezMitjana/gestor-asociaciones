package listas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import datos.Accion;
import datos.Charla;
import datos.Demostracion;
import datos.Fecha;

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
