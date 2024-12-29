package listas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import datos.Asociacion;

public class ListaAsociaciones implements Serializable{

    private static final long serialVersionUID = 1L;

    private Asociacion[] lista;
    private int nElem;

    public ListaAsociaciones(int dim) {
        lista = new Asociacion[100]; // Tamaño fijo inicial
        nElem = 0;
    }

    public boolean agregarAsociacion(Asociacion asociacion) {
        if (nElem < lista.length) {
            lista[nElem++] = asociacion;
            return true;
        }
        return false;
    }

    public Asociacion obtenerAsociacion(int index) {
        if (index >= 0 && index < nElem) {
            return lista[index];
        }
        return null;
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
