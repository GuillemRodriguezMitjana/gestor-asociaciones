package listas;

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
}