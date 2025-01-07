package listas;

import java.io.Serializable;
import java.util.Scanner;

import datos.Asociacion;
import datos.Accion;
import datos.Charla;
import excepciones.ExcepcionAsociacionNoEncontrada;
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
}
