package datos;

import excepciones.ExcepcionMaximoValoraciones;
import listas.ListaMiembros;


public class Charla extends Accion {

    
    private Fecha fechaCharla; 
    private ListaMiembros miembrosImpartidores; // Lista de miembros que imparten la charla (máximo 3)
    private int numAsistentes; 
    private int[] valoraciones; // Lista de valoraciones de los asistentes (escala [0-10])
    private int numValoraciones;

    /**
     * Constructor de la clase Charla.
     *
     * @param codigo       Código único de la charla.
     * @param titulo       Título de la charla.
     * @param responsable  Miembro responsable de la charla.
     * @param fechaCharla  Fecha en la que se realizará la charla.
     */
    public Charla(String codigo, String titulo, Miembro responsable, Fecha fechaCharla) {
        super(codigo, titulo, responsable);
        this.fechaCharla = fechaCharla;
        this.miembrosImpartidores = new ListaMiembros();
        this.numAsistentes = 0;
        this.valoraciones = new int[50];
        this.numValoraciones = 0;
    }

    /**
     * Agrega un miembro como impartidor de la charla.
     * Solo se pueden agregar hasta 3 miembros como impartidores.
     *
     * @param miembro El miembro a agregar como impartidor.
     */
    public void agregarImpartidor(Miembro miembro) {
        if (miembrosImpartidores.getNElem() < 3) {
            miembrosImpartidores.agregarMiembro(miembro);
        } else {
            System.out.println("No se pueden agregar más de 3 miembros como impartidores.");
        }
    }

    /**
     * Obtiene la lista de miembros que imparten la charla.
     *
     * @return Lista de miembros impartidores.
     */
    public ListaMiembros getMiembrosImpartidores() {
        return miembrosImpartidores;
    }

    /**
     * Incrementa el número de asistentes a la charla en 1.
     */
    public void incrementarAsistentes() {
        numAsistentes++;
    }

    /**
     * Obtiene el número total de asistentes a la charla.
     *
     * @return Número de asistentes.
     */
    public int getNumAsistentes() {
        return numAsistentes;
    }

    /**
     * Obtiene la lista de valoraciones de los asistentes.
     *
     * @return Array de valoraciones.
     */
    public int[] getValoraciones() {
        return valoraciones;
    }

    /**
     * Agrega una valoración de un asistente a la charla.
     * La valoración debe estar entre 0 y 10.
     *
     * @param valoracion Valoración del asistente.
     * @throws ExcepcionMaximoValoraciones Si se alcanza el límite máximo de valoraciones.
     */
    public void agregarValoracion(int valoracion) throws ExcepcionMaximoValoraciones {
        if (valoracion >= 0 && valoracion <= 10) {
            if (numValoraciones < valoraciones.length) {
                valoraciones[numValoraciones] = valoracion;
                numValoraciones++;
            } else {
                throw new ExcepcionMaximoValoraciones("Máximo valoraciones alcanzado.");
            }
        } else {
            System.out.println("La valoración debe estar entre 0 y 10.");
        }
    }

    /**
     * Calcula el promedio de las valoraciones recibidas.
     *
     * @return Promedio de las valoraciones, o 0.0 si no hay valoraciones.
     */
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

    /**
     * Obtiene la fecha en la que se realizará la charla.
     *
     * @return Fecha de la charla.
     */
    public Fecha getFechaCharla() {
        return fechaCharla;
    }

    /**
     * Establece la fecha en la que se realizará la charla.
     *
     * @param fechaCharla Nueva fecha para la charla.
     */
    public void setFechaCharla(Fecha fechaCharla) {
        this.fechaCharla = fechaCharla;
    }

    /**
     * Devuelve un string.
     *
     * @return Cadena con los detalles de la charla, incluyendo el código, título,
     * fecha, miembros impartidores, número de asistentes y promedio de valoraciones.
     */
    @Override
    public String toString() {
        return "Charla [codigo=" + getCodigo() + 
               ", titulo=" + getTitulo() + 
               ", fechaCharla=" + fechaCharla + 
               ", impartidores=" + miembrosImpartidores.toString() +
               ", asistentes=" + numAsistentes +
               ", promedioValoraciones=" + obtenerPromedioValoraciones() + "]";
    }
}
