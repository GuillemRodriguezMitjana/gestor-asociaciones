package datos;
public class ListaMiembros {
    private Miembro[] lista;
    private int nElem;

    public ListaMiembros() {
        lista = new Miembro[100]; 
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

    public int getNElem() {
        return nElem;
    }
}
