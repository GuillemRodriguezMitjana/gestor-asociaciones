package datos;

public class Profesor extends Miembro {

    private String departamento;
    private int numDespacho;
    private String fechaBaixa; // Fecha de baja del profesor (null si no está dado de baja)

    public Profesor(String alias, String correo, String fechaAlta, String departamento, int numDespacho) {
        super(alias, correo);
        this.setFechaAlta(fechaAlta); // Usamos el setter de fechaAlta
        this.departamento = departamento;
        this.numDespacho = numDespacho;
        this.fechaBaixa = null; // Por defecto, el profesor no está dado de baja
    }

    public String getDepartamento() {
        return departamento;
    }

    public int getNumDespacho() {
        return numDespacho;
    }

    public String getFechaBaixa() {
        return fechaBaixa;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setNumDespacho(int numDespacho) {
        this.numDespacho = numDespacho;
    }

    public void setFechaBaixa(String fechaBaixa) {
        this.fechaBaixa = fechaBaixa;
    }

    @Override
    public String toString() {
        return "Profesor [Alias: " + getAlias() + ", Correo: " + getCorreo() + ", Fecha Alta: " + getFechaAlta()
                + ", Departamento: " + departamento + ", Despacho: " + numDespacho + ", Fecha Baixa: " + fechaBaixa + "]";
    }
}
