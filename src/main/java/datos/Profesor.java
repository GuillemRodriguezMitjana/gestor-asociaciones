package datos;

public class Profesor extends Miembro {
    
    private String departamento; 
    private int numDespacho;

    public Profesor(String alias, String correo, String fechaAlta, String departamento, int numDespacho) {
        super(alias, correo);
        this.setFechaAlta(fechaAlta); // Usamos el setter de fechaAlta
        this.departamento = departamento;
        this.numDespacho = numDespacho;
    }

    public String getDepartamento() {
        return departamento;
    }

    public int getNumDespacho() {
        return numDespacho;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setNumDespacho(int numDespacho) {
        this.numDespacho = numDespacho;
    }

    @Override
    public String toString() {
        return "Profesor [Alias: " + getAlias() + ", Correo: " + getCorreo() + ", Fecha Alta: " + getFechaAlta()
                + ", Departamento: " + departamento + ", Despacho: " + numDespacho + "]";
    }
}
