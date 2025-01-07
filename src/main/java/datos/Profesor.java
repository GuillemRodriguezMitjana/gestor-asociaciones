package datos;


public class Profesor extends Miembro {

    private String departamento; 
    private int numDespacho; 
    private String fechaBaixa; 

    /**
     * Constructor que inicializa un profesor con sus datos básicos.
     *
     * @param alias       Alias del profesor.
     * @param correo      Correo del profesor.
     * @param fechaAlta   Fecha de alta del profesor.
     * @param departamento Departamento al que pertenece el profesor.
     * @param numDespacho Número de despacho del profesor.
     */
    public Profesor(String alias, String correo, String fechaAlta, String departamento, int numDespacho) {
        super(alias, correo);
        this.setFechaAlta(fechaAlta); // Usamos el setter de fechaAlta de la clase base.
        this.departamento = departamento;
        this.numDespacho = numDespacho;
        this.fechaBaixa = null; // Por defecto, el profesor no está dado de baja.
    }

    // Getters

    /**
     * Devuelve el departamento al que pertenece el profesor.
     *
     * @return Departamento del profesor.
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Devuelve el número de despacho del profesor.
     *
     * @return Número de despacho.
     */
    public int getNumDespacho() {
        return numDespacho;
    }

    /**
     * Devuelve la fecha de baja del profesor.
     *
     * @return Fecha de baja o null si el profesor no está dado de baja.
     */
    public String getFechaBaixa() {
        return fechaBaixa;
    }

    // Setters

    /**
     * Establece el departamento del profesor.
     *
     * @param departamento Departamento del profesor.
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /**
     * Establece el número de despacho del profesor.
     *
     * @param numDespacho Número de despacho.
     */
    public void setNumDespacho(int numDespacho) {
        this.numDespacho = numDespacho;
    }

    /**
     * Establece la fecha de baja del profesor.
     *
     * @param fechaBaixa Fecha de baja.
     */
    public void setFechaBaixa(String fechaBaixa) {
        this.fechaBaixa = fechaBaixa;
    }

    /**
     * Devuelve un String
     *
     * @return Cadena que describe al profesor, incluyendo su alias, correo, fecha de alta,
     * departamento, número de despacho y fecha de baja.
     */
    @Override
    public String toString() {
        return "Profesor [Alias: " + getAlias() + ", Correo: " + getCorreo() + ", Fecha Alta: " + getFechaAlta()
                + ", Departamento: " + departamento + ", Despacho: " + numDespacho + ", Fecha Baixa: " + fechaBaixa + "]";
    }
}
