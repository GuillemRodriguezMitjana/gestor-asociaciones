package datos;


public class Alumno extends Miembro {

    private String titulacion;
    private int añosEtse;
    private boolean graduado;

    /**
     * Constructor de la clase Alumno.
     *
     * @param alias       Alias único del alumno.
     * @param correo      Correo electrónico del alumno.
     * @param fechaAlta   Fecha de alta del alumno en formato String.
     * @param titulacion  Titulación del alumno.
     */
    public Alumno(String alias, String correo, String fechaAlta, String titulacion) {
        super(alias, correo);
        this.setFechaAlta(fechaAlta);
        this.titulacion = titulacion;
        this.añosEtse = 0; // Inicializado a 0.
        this.graduado = false; // Inicializado como no graduado.
    }

    /**
     * Obtiene la titulación del alumno.
     *
     * @return La titulación del alumno.
     */
    public String getTitulacion() {
        return titulacion;
    }

    /**
     * Obtiene los años que el alumno lleva en ETSE.
     *
     * @return Los años en ETSE.
     */
    public int getAñosEtse() {
        return añosEtse;
    }

    /**
     * Verifica si el alumno está graduado.
     *
     * @return {@code true} si el alumno está graduado, de lo contrario {@code false}.
     */
    public boolean isGraduado() {
        return graduado;
    }

    /**
     * Obtiene la fecha de baja del alumno.
     * Este método llama al método heredado de la clase {@code Miembro}.
     *
     * @return La fecha de baja o {@code null} si el alumno está activo.
     */
    @Override
    public String getFechaBaja() {
        return super.getFechaBaja();
    }

    /**
     * Establece la titulación del alumno.
     *
     * @param titulacion La nueva titulación del alumno.
     */
    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }

    /**
     * Establece los años que el alumno lleva en ETSE.
     *
     * @param añosEtse Los nuevos años en ETSE.
     */
    public void setAñosEtse(int añosEtse) {
        this.añosEtse = añosEtse;
    }

    /**
     * Establece si el alumno está graduado.
     *
     * @param graduado {@code true} si el alumno está graduado, de lo contrario {@code false}.
     */
    public void setGraduado(boolean graduado) {
        this.graduado = graduado;
    }

    /**
     * Devuelve string
     *
     * @return Una cadena que describe los atributos del alumno.
     */
    @Override
    public String toString() {
        return "Alumno [Alias: " + getAlias() + ", Correo: " + getCorreo() + 
               ", Fecha Alta: " + getFechaAlta() + ", Titulacion: " + titulacion + 
               ", Años ETSE: " + añosEtse + ", Graduado: " + graduado + "]";
    }
}
