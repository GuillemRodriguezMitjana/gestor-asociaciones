package datos;

import excepciones.ExcepcionListaAsociacionLlena;
import excepciones.ExcepcionMaximoAsociaciones;
import listas.ListaAsociaciones;


public abstract class Miembro {

    private String alias; // Alias del miembro.
    private String correo; // Correo del miembro.
    private String fechaAlta; // Fecha de alta del miembro.
    private String fechaBaja; // Fecha de baja del miembro (null si está activo).
    private ListaAsociaciones asociaciones; // Lista de asociaciones a las que pertenece.

    /**
     * Constructor que inicializa un miembro con alias y correo.
     *
     * @param alias  Alias del miembro.
     * @param correo Correo del miembro.
     */
    public Miembro(String alias, String correo) {
        this.alias = alias;
        this.correo = correo;
        this.fechaAlta = null;
        this.fechaBaja = null;
        this.asociaciones = new ListaAsociaciones(50);
    }

    // Getters

    /**
     * Devuelve el alias del miembro.
     *
     * @return Alias del miembro.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Devuelve el correo del miembro.
     *
     * @return Correo del miembro.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Devuelve la fecha de alta del miembro.
     *
     * @return Fecha de alta del miembro o null si no está registrada.
     */
    public String getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Devuelve la fecha de baja del miembro.
     *
     * @return Fecha de baja del miembro o null si está activo.
     */
    public String getFechaBaja() {
        return fechaBaja;
    }

    /**
     * Devuelve la lista de asociaciones a las que pertenece el miembro.
     *
     * @return Lista de asociaciones del miembro.
     */
    public ListaAsociaciones getAsociaciones() {
        return asociaciones;
    }

    // Setters

    /**
     * Establece el alias del miembro.
     *
     * @param alias Alias del miembro.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Establece el correo del miembro.
     *
     * @param correo Correo del miembro.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Establece la fecha de alta del miembro.
     *
     * @param fechaAlta Fecha de alta del miembro.
     */
    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * Establece la fecha de baja del miembro.
     *
     * @param fechaBaja Fecha de baja del miembro.
     */
    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /**
     * Verifica si el miembro está activo.
     *
     * @return `true` si el miembro está activo (fechaBaja es null), `false` en caso contrario.
     */
    public boolean estaActivo() {
        return fechaBaja == null;
    }

    /**
     * Agrega una asociación a la lista de asociaciones del miembro.
     * Lanza una excepción si ya pertenece al máximo número permitido de asociaciones.
     *
     * @param asociacion Asociación a agregar.
     * @throws ExcepcionMaximoAsociaciones    Si el miembro ya pertenece a 3 asociaciones.
     * @throws ExcepcionListaAsociacionLlena Si la lista de asociaciones está llena.
     */
    public void agregarAsociacion(Asociacion asociacion) throws ExcepcionMaximoAsociaciones, ExcepcionListaAsociacionLlena {
        if (asociaciones.getNElem() >= 3) {
            throw new ExcepcionMaximoAsociaciones("El miembro no puede pertenecer a más de 3 asociaciones.");
        }
        asociaciones.agregarAsociacion(asociacion);
    }

    /**
     * Devuelve un String.
     * 
     * @return Cadena que describe al miembro, incluyendo su alias, correo, fechas de alta y baja,
     * y las asociaciones a las que pertenece.
     */
    @Override
    public String toString() {
        String resultado = "Miembro [Alias: " + alias + 
                        ", Correo: " + correo + 
                        ", Fecha Alta: ";
        if (fechaAlta != null) {
            resultado += fechaAlta;
        } else {
            resultado += "No registrada";
        }

        resultado += ", Fecha Baja: ";
        if (fechaBaja != null) {
            resultado += fechaBaja;
        } else {
            resultado += "Activo";
        }

        resultado += ", Asociaciones: ";
        if (asociaciones.getNElem() > 0) {
            resultado += asociaciones.toString();
        } else {
            resultado += "Ninguna";
        }

        resultado += "]";
        return resultado;
    }
}
