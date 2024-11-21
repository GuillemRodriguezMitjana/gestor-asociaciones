package listas;

import datos.Ejemplo;

public class ListaEjemplos {

    private final Ejemplo[] ejemplos;
    private int numElem;

    public ListaEjemplos(int capacidadInicial) {
        ejemplos = new Ejemplo[capacidadInicial];
        numElem = 0;
    }

    public void añadirEjemplo(Ejemplo ejemplo) {
        if (numElem == ejemplos.length) {
            throw new IndexOutOfBoundsException("La lista de ejemplos está llena");
        }
        ejemplos[numElem] = ejemplo;
        numElem++;
    }

    public Ejemplo obtenerEjemplo(int i) {
        if (i < 0 || i >= ejemplos.length) {
            throw new IndexOutOfBoundsException("No se ha encontrado el elemento");
        }
        return ejemplos[i];
    }

    public int obtenerNumElementos() {
        return numElem;
    }

}
