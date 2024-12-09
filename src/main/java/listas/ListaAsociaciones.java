package listas;

import datos.Asociacion;

public class ListaAsociaciones {
    private Asociacion[] lista;
    private int nElem;

    public ListaAsociaciones() {
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
    String result = "Lista de Asociaciones:\n"; 
    for (int i = 0; i < nElem; i++) {
        result += "- " + lista[i].getName() + "\n"; 
    }
    return result; 
}
}
