package datos;

import listas.ListaMiembros;
import listas.ListaAcciones;

import java.io.Serializable;


public class Asociacion implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String correo;
    private String[] titulaciones; // Array para las titulaciones
    private ListaMiembros miembros;
    private Miembro presidente;
    private Miembro secretario;
    private Miembro tesorero;
    private ListaAcciones acciones;

    /**
     * Constructor para crear una asociación con un nombre, correo y titulaciones.
     *
     * @param name         Nombre de la asociación.
     * @param correo       Correo electrónico de contacto.
     * @param titulaciones Array de titulaciones asociadas.
     */
    public Asociacion(String name, String correo, String[] titulaciones) {
        this.name = name;
        this.correo = correo;
        this.titulaciones = titulaciones;
        this.miembros = new ListaMiembros();
        this.acciones = new ListaAcciones();
    }

    /**
     * Obtiene el nombre de la asociación.
     *
     * @return El nombre de la asociación.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de la asociación.
     *
     * @param name El nuevo nombre de la asociación.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el correo de contacto de la asociación.
     *
     * @return El correo de contacto.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo de contacto de la asociación.
     *
     * @param correo El nuevo correo de contacto.
     */
    public void setCorreoContacto(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene las titulaciones asociadas a la asociación.
     *
     * @return Un array de titulaciones.
     */
    public String[] getTitulaciones() {
        return titulaciones;
    }

    /**
     * Establece las titulaciones asociadas a la asociación.
     *
     * @param titulaciones Un array de nuevas titulaciones.
     */
    public void setTitulaciones(String[] titulaciones) {
        this.titulaciones = titulaciones;
    }

    /**
     * Obtiene la lista de miembros de la asociación.
     *
     * @return La lista de miembros.
     */
    public ListaMiembros getMiembros() {
        return miembros;
    }

    /**
     * Obtiene el presidente de la asociación.
     *
     * @return El miembro que actúa como presidente.
     */
    public Miembro getPresidente() {
        return presidente;
    }

    /**
     * Establece el presidente de la asociación.
     *
     * @param presidente El miembro que será el presidente.
     */
    public void setPresidente(Miembro presidente) {
        this.presidente = presidente;
    }

    /**
     * Obtiene el secretario de la asociación.
     *
     * @return El miembro que actúa como secretario.
     */
    public Miembro getSecretario() {
        return secretario;
    }

    /**
     * Establece el secretario de la asociación.
     *
     * @param secretario El miembro que será el secretario.
     */
    public void setSecretario(Miembro secretario) {
        this.secretario = secretario;
    }

    /**
     * Obtiene el tesorero de la asociación.
     *
     * @return El miembro que actúa como tesorero.
     */
    public Miembro getTesorero() {
        return tesorero;
    }

    /**
     * Establece el tesorero de la asociación.
     *
     * @param tesorero El miembro que será el tesorero.
     */
    public void setTesorero(Miembro tesorero) {
        this.tesorero = tesorero;
    }

    /**
     * Obtiene la lista de acciones asociadas a la asociación.
     *
     * @return La lista de acciones.
     */
    public ListaAcciones getAcciones() {
        return acciones;
    }

    /**
     * Devuelve un String
     *
     * @return Una descripción de la asociación, incluyendo su nombre,
     * correo, titulaciones, y los alias de los miembros con cargos.
     */
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
