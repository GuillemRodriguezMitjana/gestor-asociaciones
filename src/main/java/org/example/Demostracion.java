package datos;

import java.util.Date;

public class Demostracion extends Accion 
{

    private Date fechaDiseño;
    private boolean activa;
    private int vecesOfrecida;
    private double costeMateriales;

    // Constructor
    public Demostracion(String codigo, String titulo, Miembro responsable, Date fechaDiseño, double costeMateriales) 
    {
        super(codigo, titulo, responsable);
        this.fechaDiseño = fechaDiseño;
        this.activa = true;
        this.vecesOfrecida = 0;
        this.costeMateriales = costeMateriales;
    }

    public void ofrecerDemostracion() 
    {
        if (activa) {
            vecesOfrecida++;
        } else {
            System.out.println("La demostración no está activa y no se puede ofrecer.");
        }
    }

    // Métodos para activar o desactivar la demostración
    public void desactivarDemostracion() 
    {
        activa = false;
    }

    public void activarDemostracion() 
    {
        activa = true;
    }

    // Getters y setters
    public Date getFechaDiseño() 
    {
        return fechaDiseño;
    }

    public void setFechaDiseño(Date fechaDiseño) 
    {
        this.fechaDiseño = fechaDiseño;
    }

    public boolean isActiva() 
    {
        return activa;
    }

    public void setActiva(boolean activa) 
    {
        this.activa = activa;
    }

    public int getVecesOfrecida() 
    {
        return vecesOfrecida;
    }

    public void setVecesOfrecida(int vecesOfrecida) 
    {
        this.vecesOfrecida = vecesOfrecida;
    }

    public double getCosteMateriales() 
    {
        return costeMateriales;
    }

    public void setCosteMateriales(double costeMateriales) 
    {
        this.costeMateriales = costeMateriales;
    }

    @Override
    public String toString() 
    {
        return "Demostracion [codigo=" + getCodigo() + ", titulo=" + getTitulo() +
                ", fechaDiseño=" + fechaDiseño +
                ", activa=" + activa +
                ", vecesOfrecida=" + vecesOfrecida +
                ", costeMateriales=" + costeMateriales + "]";
    }
}