package datos;


public abstract class Accion {

    private String codigo;
    private String titulo;
    private Miembro responsable;

    /**
     * Constructor de la clase Accion.
     *
     * @param codigo       Código único que identifica la acción.
     * @param titulo       Título descriptivo de la acción.
     * @param responsable  Miembro responsable de la acción.
     */
    public Accion(String codigo, String titulo, Miembro responsable) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.responsable = responsable;
    }

    /**
     * Obtiene el código de la acción.
     *
     * @return El código de la acción.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código de la acción.
     *
     * @param codigo El nuevo código de la acción.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el título de la acción.
     *
     * @return El título de la acción.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título de la acción.
     *
     * @param titulo El nuevo título de la acción.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el miembro responsable de la acción.
     *
     * @return El miembro responsable de la acción.
     */
    public Miembro getResponsable() {
        return responsable;
    }

    /**
     * Establece el miembro responsable de la acción.
     *
     * @param responsable El nuevo miembro responsable de la acción.
     */
    public void setResponsable(Miembro responsable) {
        this.responsable = responsable;
    }

    /**
     * Devuelve string
     *
     * @return Una cadena que describe la acción, incluyendo el código, título
     *         y el responsable.
     */
    @Override
    public String toString() {
        return "Accion [codigo=" + codigo + ", titulo=" + titulo + ", responsable=" + responsable + "]";
    }
}
