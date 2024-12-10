package datos;
public abstract class Accion 
{
    private String codigo;
    private String titulo;
    private Miembro responsable;
    // Constructor
    public Accion(String codigo, String titulo, Miembro responsable) 
{
        this.codigo = codigo;
        this.titulo = titulo;
        this.responsable = responsable;
}
    // Getters y setters
    public String getCodigo() 
{
        return codigo;
}
    public void setCodigo(String codigo) 
{
        this.codigo = codigo;
}
    public String getTitulo() 
{
        return titulo;
}
    public void setTitulo(String titulo) 
{
        this.titulo = titulo;
}
    public Miembro getResponsable() 
{
        return responsable;
}
    public void setResponsable(Miembro responsable) 
{
        this.responsable = responsable;
}
    @Override
    public String toString() 
{
        return "Accion [codigo=" + codigo + ", titulo=" + titulo + ", responsable=" + responsable + "]";
}
}