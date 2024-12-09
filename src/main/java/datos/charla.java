package datos;

import listas.ListaMiembros;

public class Charla extends Accion {

    // Atributos específicos de Charla
    private String fechaCharla; // Fecha en la que se realizará la charla
    private ListaMiembros miembrosImpartidores; // Lista de miembros que imparten la charla (máximo 3)
    private int numAsistentes; // Número de asistentes a la charla
    private int[] valoraciones; // Lista de valoraciones de los asistentes (escala [0-10])
    private int numValoraciones;

    // Constructor
    public Charla(String codigo, String titulo, Miembro responsable, String fechaCharla) {
        super(codigo, titulo, responsable);
        this.fechaCharla = fechaCharla;
        this.miembrosImpartidores = new ListaMiembros();
        this.numAsistentes = 0;
        this.valoraciones = new int[50];
        this.numValoraciones = 0;
    }

    // Métodos específicos de Charla

    // Agregar un miembro impartidor (máximo 3)
    public void agregarImpartidor(Miembro miembro) {
        if (miembrosImpartidores.getNElem() < 3) {
            miembrosImpartidores.agregarMiembro(miembro);
        } else {
            System.out.println("No se pueden agregar más de 3 miembros como impartidores.");
        }
    }

    public ListaMiembros getMiembrosImpartidores() {
        return miembrosImpartidores;
    }

    // Incrementar el número de asistentes
    public void incrementarAsistentes() {
        numAsistentes++;
    }

    public int getNumAsistentes() {
        return numAsistentes;
    }

    // Obtener la lista de valoraciones
    public int[] getValoraciones() {
        return valoraciones;
    }

    // Agregar una valoración (entre 0 y 10)
    public void agregarValoracion(int valoracion) {
        if (valoracion >= 0 && valoracion <= 10) {
            if (numValoraciones < valoraciones.length) {
                valoraciones[numValoraciones] = valoracion;
                numValoraciones++;
            }
        } else {
            System.out.println("La valoración debe estar entre 0 y 10.");
        }
    }

    // Calcular la media de las valoraciones
    public double obtenerPromedioValoraciones() {
        if (numValoraciones == 0) {
            return 0.0;
        }

        int suma = 0;
        for (int i = 0; i < numValoraciones; i++) {
            suma += valoraciones[i];
        }

        return (double) suma / numValoraciones;
    }

    // Getters y setters
    public String getFechaCharla() {
        return fechaCharla;
    }

    public void setFechaCharla(String fechaCharla) {
        this.fechaCharla = fechaCharla;
    }

    @Override
    public String toString() {
        return "Charla [codigo=" + getCodigo() + ", titulo=" + getTitulo() +
                ", fechaCharla=" + fechaCharla +
                ", impartidores=" + miembrosImpartidores +
                ", asistentes=" + numAsistentes +
                ", promedioValoraciones=" + obtenerPromedioValoraciones() + "]";
    }
}