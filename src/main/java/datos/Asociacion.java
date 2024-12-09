package datos;

public class Asociacion {
    private String name;
    private String correo;
    private String[] titulaciones; // Array para las titulaciones
    private ListaMiembros miembros;
    private Miembro presidente;
    private Miembro secretario;
    private Miembro tesorero;
    private ListaAcciones acciones;

    public Asociacion(String name, String correo, String[] titulaciones) {
        this.name = name;
        this.correo = correo;
        this.titulaciones = titulaciones;
        this.miembros = new ListaMiembros();
        this.acciones = new ListaAcciones();    
    }

    // Métodos getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreoContacto(String correo) {
        this.correo = correo;
    }

    public String[] getTitulaciones() {
        return titulaciones;
    }

    public void setTitulaciones(String[] titulaciones) {
        this.titulaciones = titulaciones;
    }

    public ListaMiembros getMiembros() {
        return miembros;
    }

    public Miembro getPresidente() {
        return presidente;
    }

    public void setPresidente(Miembro presidente) {
        this.presidente = presidente;
    }

    public Miembro getSecretario() {
        return secretario;
    }

    public void setSecretario(Miembro secretario) {
        this.secretario = secretario;
    }

    public Miembro getTesorero() {
        return tesorero;
    }

    public void setTesorero(Miembro tesorero) {
        this.tesorero = tesorero;
    }

    public ListaAcciones getAcciones() {
        return acciones;
    }
}
