package datos;

public class Alumno extends Miembro {

    private String titulacion;
    private int añosEtse;
    private boolean graduado;

    public Alumno(String alias, String correo, String fechaAlta, String titulacion) {
        super(alias, correo);
        this.setFechaAlta(fechaAlta);
        this.titulacion = titulacion;
        this.añosEtse = 0; 
        this.graduado = false; 
    }

    // Getters
    public String getTitulacion() {
        return titulacion;
    }

    public int getAñosEtse() {
        return añosEtse;
    }

    public boolean isGraduado() {
        return graduado;
    }

    /**
     * Devuelve la fecha de baja del alumno.
     * Este método llama al método heredado de Miembro.
     * @return Fecha de baja o null si el alumno está activo.
     */
    @Override
    public String getFechaBaja() {
        return super.getFechaBaja();
    }

    // Setters
    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }

    public void setAñosEtse(int añosEtse) {
        this.añosEtse = añosEtse;
    }

    public void setGraduado(boolean graduado) {
        this.graduado = graduado;
    }

    @Override
    public String toString() {
        return "Alumno [Alias: " + getAlias() + ", Correo: " + getCorreo() + ", Fecha Alta: " + getFechaAlta()
                + ", Titulacion: " + titulacion + ", añosETSE: " + añosEtse + "graduado: " + graduado + "]";
    }

}
