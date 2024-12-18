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

    public String getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }

    public int getAñosEtse() {
        return añosEtse;
    }

    public void setAñosEtse(int añosEtse) {
        this.añosEtse = añosEtse;
    }

    public boolean getGraduado() {
        return graduado;
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
