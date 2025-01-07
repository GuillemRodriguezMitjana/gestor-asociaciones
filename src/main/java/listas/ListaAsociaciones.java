package listas;

import java.io.Serializable;
import java.util.Scanner;

import datos.Asociacion;
import datos.Accion;
import datos.Charla;
import excepciones.ExcepcionAsociacionNoEncontrada;
import excepciones.ExcepcionIndiceFueraDeRango;
import excepciones.ExcepcionListaAsociacionLlena;

public class ListaAsociaciones implements Serializable{

    private static final long serialVersionUID = 1L;

    private Asociacion[] lista;
    private int nElem;

    public ListaAsociaciones(int dim) {
        lista = new Asociacion[100]; // Tamaño fijo inicial
        nElem = 0;
    }

    public boolean agregarAsociacion(Asociacion asociacion) throws ExcepcionListaAsociacionLlena {
        if (nElem < lista.length) {
            lista[nElem++] = asociacion;
            return true;
        }
        throw new ExcepcionListaAsociacionLlena("La lista de asociaciones está llena.");
    }

    public Asociacion obtenerAsociacion(int index) throws ExcepcionIndiceFueraDeRango {
        if (index >= 0 && index < nElem) {
            return lista[index];
        }
        throw new ExcepcionIndiceFueraDeRango("El índice " + index + " está fuera del rango permitido.");
    }

    public Asociacion buscarAsociacion(String nombre) {
        for (int i = 0; i < nElem; i++) {
            if (lista[i].getName().equalsIgnoreCase(nombre)) {
                return lista[i];
            }
        }
        return null;
    }
    

    public int getNElem() {
        return nElem;
    }
    
    @Override
    public String toString() {
        String resultado = "[";
        for (int i = 0; i < nElem; i++) {
            resultado += lista[i].getName();
            if (i < nElem - 1) {
                resultado += ", ";
            }
        }
        resultado += "]";
        return resultado;
    }
    


    // Métodos añadidos por tu compañero
    public void mostrarAssociacions() {
        if (nElem == 0) {
            System.out.println("No hay asociaciones registradas.");
        } else {
            System.out.println("Lista de Asociaciones:");
            for (int i = 0; i < nElem; i++) {
                System.out.println(lista[i]);
            }
        }
    }

    public void agregarNuevaAsociacion() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Introduce los datos de la nueva asociación:");
        
        System.out.print("Nombre de la asociación: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Correo de contacto: ");
        String correo = scanner.nextLine();
        
        System.out.print("Titulaciones (separadas por comas): ");
        String[] titulaciones = scanner.nextLine().split(",");
        
        Asociacion nuevaAsociacion = new Asociacion(nombre, correo, titulaciones);
        
        try {
            if (agregarAsociacion(nuevaAsociacion)) {
                System.out.println("Asociación añadida con éxito: " + nuevaAsociacion);
            }
        } catch (ExcepcionListaAsociacionLlena e) {
            System.out.println("Error al añadir la asociación: " + e.getMessage());
        }
        scanner.close();
    }
    

    public void mostrarCharlasPorMiembro(String alias) {
        boolean encontrado = false;
        
        for (int i = 0; i < nElem; i++) {
            Asociacion asociacion = lista[i];
            
            for (int j = 0; j < asociacion.getAcciones().getNElem(); j++) {
                Accion accion = asociacion.getAcciones().obtenerAccion(j);
                
                if (accion instanceof Charla) {
                    Charla charla = (Charla) accion;
                    if (charla.getResponsable().getAlias().equalsIgnoreCase(alias)) {
                        System.out.println("Charla encontrada en la asociación " + asociacion.getName() + ": " + charla);
                        encontrado = true;
                    }
                }
            }
        }
        
        if (!encontrado) {
            System.out.println("No se encontraron charlas para el miembro con alias: " + alias);
        }
    }

    public void salirAplicacion() {
        System.out.println("¡Gracias por usar la aplicación! Cerrando...");
        System.exit(0);
    }
}
