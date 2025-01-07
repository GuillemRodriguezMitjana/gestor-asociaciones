package listas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import datos.Alumno;
import datos.Miembro;
import datos.Profesor;


public class ListaMiembros {

    private Miembro[] lista;
    private int nElem;

    /**
     * Constructor que inicializa la lista con una capacidad inicial de 100 miembros.
     */
    public ListaMiembros() {
        lista = new Miembro[100];  // Capacidad inicial de 100 miembros
        nElem = 0;
    }

    /**
     * Agrega un miembro a la lista.
     * @param miembro El miembro a agregar.
     * @return true si se agregó correctamente, false si la lista está llena.
     */
    public boolean agregarMiembro(Miembro miembro) {
        if (nElem < lista.length) {
            lista[nElem++] = miembro;
            return true;
        }
        return false;
    }

    /**
     * Obtiene un miembro de la lista dado su índice.
     * @param index El índice del miembro a obtener.
     * @return El miembro en el índice dado, o null si el índice es inválido.
     */
    public Miembro obtenerMiembro(int index) {
        if (index >= 0 && index < nElem) {
            return lista[index];
        }
        return null;
    }

    /**
     * Busca un miembro por su alias.
     * @param alias El alias del miembro a buscar.
     * @return El miembro encontrado, o null si no existe.
     */
    public Miembro buscarMiembro(String alias) {
        for (int i = 0; i < nElem; i++) {
            if (lista[i].getAlias().equalsIgnoreCase(alias)) {
                return lista[i];
            }
        }
        return null;
    }

    /**
     * Obtiene el número de elementos en la lista.
     * @return El número de miembros en la lista.
     */
    public int getNElem() {
        return nElem;
    }

    /**
     * Lee los datos de los miembros desde un archivo.
     */
    public void LlegirFitxer() {
        try (BufferedReader f = new BufferedReader(new FileReader("miembros.txt"))) {
            String linea = f.readLine();
            while (linea != null) {
                String[] partes = linea.split(";");

                String alias = partes[0];
                String correo = partes[1];
                String tipo = partes[2];

                Miembro miembro;
                if (tipo.equalsIgnoreCase("Profesor")) {
                    String fechaAlta = partes[3];
                    String departamento = partes[4];
                    int numDespacho = Integer.parseInt(partes[5]);
                    String fechaBaixa = null;
                    if (!partes[6].equalsIgnoreCase("null")) {
                        fechaBaixa = partes[6];
                    }

                    miembro = new Profesor(alias, correo, fechaAlta, departamento, numDespacho);
                    ((Profesor) miembro).setFechaBaixa(fechaBaixa);

                } else if (tipo.equalsIgnoreCase("Alumno")) {
                    String fechaAlta = partes[3];
                    String titulacion = partes[4];
                    boolean graduat = Boolean.parseBoolean(partes[5]);
                    String fechaBaixa = null;
                    if (!partes[6].equalsIgnoreCase("null")) {
                        fechaBaixa = partes[6];
                    }

                    miembro = new Alumno(alias, correo, fechaAlta, titulacion);
                    ((Alumno) miembro).setGraduado(graduat);
                    ((Alumno) miembro).setFechaBaja(fechaBaixa);

                } else {
                    throw new IllegalArgumentException("Tipo de miembro desconocido: " + tipo);
                }

                this.agregarMiembro(miembro);
                linea = f.readLine();
            }
        } catch (Exception e) {
            System.out.println("S'ha produit un error en els arxius: ");
        }
    }

    /**
     * Escribe los datos de los miembros en un archivo.
     * @param nombreArchivo El nombre del archivo donde se guardarán los datos.
     */
    public void EscriureFitxer(String nombreArchivo) {
        try (BufferedWriter f = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (int i = 0; i < nElem; i++) {
                Miembro miembro = lista[i];

                if (miembro instanceof Profesor) {
                    Profesor profesor = (Profesor) miembro;
                    String fechaBaixa = "null";
                    if (profesor.getFechaBaixa() != null) {
                        fechaBaixa = profesor.getFechaBaixa();
                    }

                    f.write(profesor.getAlias() + ";" +
                            profesor.getCorreo() + ";" +
                            "Profesor;" +
                            profesor.getFechaAlta() + ";" +
                            profesor.getDepartamento() + ";" +
                            profesor.getNumDespacho() + ";" +
                            fechaBaixa + "\n");

                } else if (miembro instanceof Alumno) {
                    Alumno alumno = (Alumno) miembro;
                    String fechaBaixa = "null";
                    if (alumno.getFechaBaja() != null) {
                        fechaBaixa = alumno.getFechaBaja();
                    }

                    f.write(alumno.getAlias() + ";" +
                            alumno.getCorreo() + ";" +
                            "Alumno;" +
                            alumno.getFechaAlta() + ";" +
                            alumno.getTitulacion() + ";" +
                            alumno.isGraduado() + ";" +
                            fechaBaixa + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    /**
     * Devuelve un String.
     * @return Una cadena con los alias de los miembros en la lista.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < nElem; i++) {
            sb.append(lista[i].getAlias());
            if (i < nElem - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}