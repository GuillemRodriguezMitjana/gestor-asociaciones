package datos;

import listas.ListaAsociaciones;

public abstract class Miembro {

    private String alias;
    private String correo;
    private String fechaAlta;
    private String fechaBaja;
    private ListaAsociaciones asociaciones;

    public Miembro(String alias, String correo) {
        this.alias = alias;
        this.correo = correo;
        this.fechaAlta = null;
        this.fechaBaja = null;
        this.asociaciones = new ListaAsociaciones();
    }

    // Getters
    public String getAlias() {
        return alias;
    }

    public String getCorreo() {
        return correo;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public String getFechaBaja() {
        return fechaBaja;
    }

    public ListaAsociaciones getAsociaciones() {
        return asociaciones;
    }

    // Setters
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    // Métodos adicionales
    public boolean estaActivo() {
        return fechaBaja == null;
    }

    public void agregarAsociacion(Asociacion asociacion) throws Exception {
        if (asociaciones.getNElem() >= 3) {
            throw new Exception("El miembro no puede pertenecer a más de 3 asociaciones.");
        }
        asociaciones.agregarAsociacion(asociacion);
    }

    @Override
    public String toString() {
        return "Miembro [Alias: " + alias + ", Correo: " + correo + ", Fecha Alta: " + fechaAlta + ", Fecha Baja: "
                + fechaBaja + "]";
    }

}
