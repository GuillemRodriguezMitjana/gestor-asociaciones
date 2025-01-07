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

public class ListaAsociaciones implements Serializable{

    private static final long serialVersionUID = 1L;

    private Asociacion[] lista;
    private int nElem;

    public ListaAsociaciones(int dim) {
        lista = new Asociacion[100]; // Tamaño fijo inicial
        nElem = 0;
    }

    public boolean agregarAsociacion(Asociacion asociacion) throws ExcepcionListaAsociacionLlena {
        if (nElem < lista.length) {
            lista[nElem++] = asociacion;
            return true;
        }
        throw new ExcepcionListaAsociacionLlena("La lista de asociaciones está llena.");
    }

    public Asociacion obtenerAsociacion(int index) throws ExcepcionIndiceFueraDeRango {
        if (index >= 0 && index < nElem) {
            return lista[index];
        }
        throw new ExcepcionIndiceFueraDeRango("El índice " + index + " está fuera del rango permitido.");
    }

    public Asociacion buscarAsociacion(String nombre) {
        for (int i = 0; i < nElem; i++) {
            if (lista[i].getName().equalsIgnoreCase(nombre)) {
                return lista[i];
            }
        }
        return null;
    }
    

    public int getNElem() {
        return nElem;
    }
    
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
    public void guardarDatos(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (int i = 0; i < nElem; i++) {
                oos.writeObject(lista[i]);
            }
            System.out.println("Datos guardados correctamente en " + filename);
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }
    
    public void cargarDatos(String filename) {
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
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }
}

