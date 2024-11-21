package datos;

import excepciones.EjemploDivisionPorZero;

public class Ejemplo {

    public int sumar(int a, int b) {
        return a + b;
    }

    public int restar(int a, int b) {
        return a - b;
    }

    public double dividir(int a, int b) {
        if (b == 0) {
                throw new EjemploDivisionPorZero();
        }
        return (double) a / b;
    }

}
