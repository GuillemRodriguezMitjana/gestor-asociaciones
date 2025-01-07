package datos;


public class Demostracion extends Accion {

    private Fecha fechaDiseño; 
    private boolean activa; 
    private int vecesOfrecida; 
    private double costeMateriales; 
    private Asociacion asociacion;

    /**
     * Constructor de la clase Demostracion.
     *
     * @param codigo           Código único de la demostración.
     * @param titulo           Título de la demostración.
     * @param responsable      Miembro responsable de la demostración.
     * @param fechaDiseño      Fecha en la que se diseñó la demostración.
     * @param costeMateriales  Coste de los materiales necesarios para la demostración.
     */
    public Demostracion(String codigo, String titulo, Miembro responsable, Fecha fechaDiseño, double costeMateriales) {
        super(codigo, titulo, responsable);
        this.fechaDiseño = fechaDiseño;
        this.activa = true;
        this.vecesOfrecida = 0;
        this.costeMateriales = costeMateriales;
    }

    /**
     * Obtiene la asociación a la que pertenece la demostración.
     *
     * @return La asociación asociada a la demostración.
     */
    public Asociacion getAsociacion() {
        return asociacion;
    }

    /**
     * Asigna una asociación a la demostración.
     *
     * @param asociacion Asociación que se asignará a la demostración.
     */
    public void setAsociacion(Asociacion asociacion) {
        this.asociacion = asociacion;
    }

    /**
     * Incrementa el contador de veces que se ha ofrecido la demostración si está activa.
     * Muestra un mensaje si la demostración no está activa.
     */
    public void ofrecerDemostracion() {
        if (activa) {
            vecesOfrecida++;
        } else {
            System.out.println("La demostración no está activa y no se puede ofrecer.");
        }
    }

    /**
     * Desactiva la demostración, marcándola como no activa.
     */
    public void desactivarDemostracion() {
        activa = false;
    }

    /**
     * Activa la demostración, permitiendo que se pueda ofrecer.
     */
    public void activarDemostracion() {
        activa = true;
    }

    /**
     * Obtiene la fecha de diseño de la demostración.
     *
     * @return Fecha de diseño.
     */
    public Fecha getFechaDiseño() {
        return fechaDiseño;
    }

    /**
     * Establece la fecha de diseño de la demostración.
     *
     * @param fechaDiseño Nueva fecha de diseño.
     */
    public void setFechaDiseño(Fecha fechaDiseño) {
        this.fechaDiseño = fechaDiseño;
    }

    /**
     * Indica si la demostración está activa.
     *
     * @return `true` si está activa, `false` en caso contrario.
     */
    public boolean isActiva() {
        return activa;
    }

    /**
     * Establece el estado de la demostración (activa o no activa).
     *
     * @param activa `true` para activar, `false` para desactivar.
     */
    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    /**
     * Obtiene el número de veces que se ha ofrecido la demostración.
     *
     * @return Número de veces ofrecida.
     */
    public int getVecesOfrecida() {
        return vecesOfrecida;
    }

    /**
     * Establece el número de veces que se ha ofrecido la demostración.
     *
     * @param vecesOfrecida Número de veces ofrecida.
     */
    public void setVecesOfrecida(int vecesOfrecida) {
        this.vecesOfrecida = vecesOfrecida;
    }

    /**
     * Obtiene el coste de los materiales necesarios para la demostración.
     *
     * @return Coste de materiales.
     */
    public double getCosteMateriales() {
        return costeMateriales;
    }

    /**
     * Establece el coste de los materiales necesarios para la demostración.
     *
     * @param costeMateriales Nuevo coste de materiales.
     */
    public void setCosteMateriales(double costeMateriales) {
        this.costeMateriales = costeMateriales;
    }

    /**
     * Devuelve un string 
     *
     * @return Detalles de la demostración, incluyendo código, título, fecha de diseño,
     * estado activo, veces ofrecida y coste de materiales.
     */
    @Override
    public String toString() {
        return "Demostracion [codigo=" + getCodigo() + ", titulo=" + getTitulo() +
                ", fechaDiseño=" + fechaDiseño +
                ", activa=" + activa +
                ", vecesOfrecida=" + vecesOfrecida +
                ", costeMateriales=" + costeMateriales + "]";
    }
}
