package listas;

import datos.Miembro;

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
}
