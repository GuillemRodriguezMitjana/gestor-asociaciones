package listas;


import java.io.Serializable;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.EOFException;

import datos.Asociacion;
import excepciones.ExcepcionIndiceFueraDeRango;
import excepciones.ExcepcionListaAsociacionLlena;


public class ListaAsociaciones implements Serializable {

    private static final long serialVersionUID = 1L;

    private Asociacion[] lista;
    private int nElem;

    /**
     * Constructor que inicializa la lista de asociaciones con un tamaño fijo.
     * @param dim Tamaño máximo de la lista de asociaciones.
     */
    public ListaAsociaciones(int dim) {
        lista = new Asociacion[100]; // Tamaño fijo inicial
        nElem = 0;
    }

    /**
     * Agrega una nueva asociación a la lista.
     * @param asociacion La asociación a agregar.
     * @return true si la asociación se agregó correctamente.
     * @throws ExcepcionListaAsociacionLlena Si la lista ya está llena.
     */
    public boolean agregarAsociacion(Asociacion asociacion) throws ExcepcionListaAsociacionLlena {
        if (nElem < lista.length) {
            lista[nElem++] = asociacion;
            return true;
        }
        throw new ExcepcionListaAsociacionLlena("La lista de asociaciones está llena.");
    }

    /**
     * Obtiene una asociación de la lista dado su índice.
     * @param index El índice de la asociación.
     * @return La asociación en el índice dado.
     * @throws ExcepcionIndiceFueraDeRango Si el índice está fuera del rango permitido.
     */
    public Asociacion obtenerAsociacion(int index) throws ExcepcionIndiceFueraDeRango {
        if (index >= 0 && index < nElem) {
            return lista[index];
        }
        throw new ExcepcionIndiceFueraDeRango("El índice " + index + " está fuera del rango permitido.");
    }

    /**
     * Busca una asociación en la lista por su nombre.
     * @param nombre El nombre de la asociación a buscar.
     * @return La asociación encontrada, o null si no existe.
     */
    public Asociacion buscarAsociacion(String nombre) {
        for (int i = 0; i < nElem; i++) {
            if (lista[i].getName().equalsIgnoreCase(nombre)) {
                return lista[i];
            }
        }
        return null;
    }

    /**
     * Obtiene el número de elementos en la lista.
     * @return El número de asociaciones en la lista.
     */
    public int getNElem() {
        return nElem;
    }

    /**
     * Devuelve una representación en cadena de la lista de asociaciones.
     * @return Una cadena que contiene los nombres de todas las asociaciones.
     */
    @Override
    public String toString() {
        String resultado = "[";
        for (int i = 0; i < nElem; i++) {
            resultado += lista[i].getName();
            if (i < nElem - 1) {
                resultado += ", ";
            }
        }
        resultado += "]";
        return resultado;
    }

    /**
     * Guarda las asociaciones en un archivo.
     * @param filename Nombre del archivo donde se guardarán los datos.
     */
    public void guardarDatos(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (int i = 0; i < nElem; i++) {
                oos.writeObject(lista[i]);
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    /**
     * Carga las asociaciones desde un archivo.
     * @param filename Nombre del archivo desde donde se cargarán los datos.
     * @throws IOException            Si ocurre un error de entrada/salida.
     * @throws ClassNotFoundException Si ocurre un error al leer el objeto.
     */
    public void cargarDatos(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            boolean finArchivo = false;
            while (!finArchivo) {
                try {
                    Asociacion asociacion = (Asociacion) ois.readObject();
                    agregarAsociacion(asociacion);
                } catch (EOFException e) {
                    finArchivo = true;
                } catch (ExcepcionListaAsociacionLlena e) {
                    System.out.println("La lista está llena: " + e.getMessage());
                    break;
                }
            }
            System.out.println("Datos cargados correctamente desde " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("El archivo " + filename + " no existe.");

        }
        } 
    }
