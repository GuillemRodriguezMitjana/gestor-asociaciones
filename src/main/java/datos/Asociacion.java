package datos;

import listas.*;

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

    @Override
    public String toString() {
        String result = "Asociacion: " + name + "\n" +
                "Correo: " + correo + "\n" +
                "Titulaciones: ";

        if (titulaciones != null) {
            for (int i = 0; i < titulaciones.length; i++) {
                result += titulaciones[i];
                if (i < titulaciones.length - 1) {
                    result += ", ";
                }
            }
        }

        result += "\nPresidente: ";
        if (presidente != null) {
            result += presidente.getAlias();
        } else {
            result += "Ninguno";
        }

        result += "\nSecretario: ";
        if (secretario != null) {
            result += secretario.getAlias();
        } else {
            result += "Ninguno";
        }

        result += "\nTesorero: ";
        if (tesorero != null) {
            result += tesorero.getAlias();
        } else {
            result += "Ninguno";
        }

        return result;
    }

}
