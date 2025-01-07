package datos;


public class Fecha {
    private int dia;
    private int mes; 
    private int año; 
    /**
     * Constructor que crea una fecha con un día, mes y año específicos.
     * Valida que la fecha sea válida.
     *
     * @param dia Día de la fecha.
     * @param mes Mes de la fecha.
     * @param año Año de la fecha.
     * @throws IllegalArgumentException Si la fecha no es válida.
     */
    public Fecha(int dia, int mes, int año) {
        if (!esFechaValida(dia, mes, año)) {
            throw new IllegalArgumentException("Fecha no válida: " + dia + "/" + mes + "/" + año);
        }
        this.dia = dia;
        this.mes = mes;
        this.año = año;
    }

    /**
     * Verifica si una fecha es válida según el día, mes y año proporcionados.
     *
     * @param dia Día de la fecha.
     * @param mes Mes de la fecha.
     * @param año Año de la fecha.
     * @return `true` si la fecha es válida, `false` en caso contrario.
     */
    public static boolean esFechaValida(int dia, int mes, int año) {
        if (mes < 1 || mes > 12) {
            return false;
        }
        int diasEnMes;
        switch (mes) {
            case 4: case 6: case 9: case 11:
                diasEnMes = 30;
                break;
            case 2:
                diasEnMes = (esBisiesto(año)) ? 29 : 28;
                break;
            default:
                diasEnMes = 31;
        }
        return dia > 0 && dia <= diasEnMes;
    }

    /**
     * Verifica si un año es bisiesto.
     *
     * @param año Año a verificar.
     * @return `true` si el año es bisiesto, `false` en caso contrario.
     */
    private static boolean esBisiesto(int año) {
        return (año % 4 == 0 && año % 100 != 0) || (año % 400 == 0);
    }

    /**
     * Convierte una cadena de texto en formato "dd/MM/yyyy" a un objeto `Fecha`.
     *
     * @param fechaStr Cadena de texto que representa una fecha.
     * @return Objeto `Fecha` correspondiente.
     * @throws IllegalArgumentException Si el formato de la fecha no es válido.
     */
    public static Fecha parse(String fechaStr) {
        String[] partes = fechaStr.split("/");
        if (partes.length != 3) {
            throw new IllegalArgumentException("Formato de fecha no válido: " + fechaStr);
        }
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int año = Integer.parseInt(partes[2]);
        return new Fecha(dia, mes, año);
    }

    /**
     * Obtiene el día de la fecha.
     *
     * @return Día de la fecha.
     */
    public int getDia() {
        return dia;
    }

    /**
     * Obtiene el mes de la fecha.
     *
     * @return Mes de la fecha.
     */
    public int getMes() {
        return mes;
    }

    /**
     * Obtiene el año de la fecha.
     *
     * @return Año de la fecha.
     */
    public int getAño() {
        return año;
    }

    /**
     * Compara esta fecha con otra y devuelve la diferencia.
     *
     * @param otra Fecha a comparar.
     * @return Un valor negativo si esta fecha es anterior, positivo si es posterior, y 0 si son iguales.
     */
    public int compareTo(Fecha otra) {
        if (this.año != otra.año) {
            return this.año - otra.año;
        }
        if (this.mes != otra.mes) {
            return this.mes - otra.mes;
        }
        return this.dia - otra.dia;
    }

    /**
     * Verifica si esta fecha es anterior a otra.
     *
     * @param otra Fecha a comparar.
     * @return `true` si esta fecha es anterior, `false` en caso contrario.
     */
    public boolean before(Fecha otra) {
        return this.compareTo(otra) < 0;
    }

    /**
     * Verifica si esta fecha es posterior a otra.
     *
     * @param otra Fecha a comparar.
     * @return `true` si esta fecha es posterior, `false` en caso contrario.
     */
    public boolean after(Fecha otra) {
        return this.compareTo(otra) > 0;
    }

    /**
     * Devuelve una representación un string de la fecha en formato "dd/MM/yyyy".
     *
     * @return Cadena que representa la fecha.
     */
    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", dia, mes, año);
    }
}
