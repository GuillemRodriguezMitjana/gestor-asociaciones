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

    public ListaMiembros() {
        lista = new Miembro[100];  // Capacidad inicial de 100 miembros
        nElem = 0;
    }

    public boolean agregarMiembro(Miembro miembro) {
        if (nElem < lista.length) {
            lista[nElem++] = miembro;
            return true;
        }
        return false;
    }
    
    public Miembro obtenerMiembro(int index) {
        if (index >= 0 && index < nElem) {
            return lista[index];
        }
        return null;
    }

    public Miembro buscarMiembro(String alias) {
        for (int i = 0; i < nElem; i++) {
            if (lista[i].getAlias().equalsIgnoreCase(alias)) {
                return lista[i];
            }
        }
        return null;
    }

    public int getNElem() {
        return nElem;
    }
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
    


}
