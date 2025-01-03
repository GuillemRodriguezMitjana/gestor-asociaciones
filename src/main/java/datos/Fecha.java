package datos;
public class Fecha {
    private int dia;
    private int mes;
    private int año;

    public Fecha(int dia, int mes, int año) {
        if (!esFechaValida(dia, mes, año)) {
            throw new IllegalArgumentException("Fecha no válida: " + dia + "/" + mes + "/" + año);
        }
        this.dia = dia;
        this.mes = mes;
        this.año = año;
    }

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

    private static boolean esBisiesto(int año) {
        return (año % 4 == 0 && año % 100 != 0) || (año % 400 == 0);
    }

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

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAño() {
        return año;
    }

    public int compareTo(Fecha otra) {
        if (this.año != otra.año) {
            return this.año - otra.año;
        }
        if (this.mes != otra.mes) {
            return this.mes - otra.mes;
        }
        return this.dia - otra.dia;
    }
    
    public boolean before(Fecha otra) {
        return this.compareTo(otra) < 0;
    }
    
    public boolean after(Fecha otra) {
        return this.compareTo(otra) > 0;
    }
    

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", dia, mes, año);
    }
}