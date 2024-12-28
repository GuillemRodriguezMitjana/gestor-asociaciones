import java.util.*;

import datos.Accion;
import datos.Alumno;
import datos.Asociacion;
import datos.Charla;
import datos.Miembro;
import datos.Profesor;
import listas.ListaMiembros;
import listas.ListaAcciones;
import listas.ListaAsociaciones;
import datos.Fecha;

public class Main {
    private static void mostrarlistaAcciones(ListaAcciones llista) {
        System.out.println(llista.toString());
    }
    private static void mostrarlistaAsociaciones(ListaAsociaciones llista) {
        System.out.println(llista.toString());
    }
    private static void mostrarListaMiembros(ListaMiembros llista) {
        System.out.println(llista.toString());
    }   
    static Scanner teclat = new Scanner(System.in);
    public static void main(String[] args){

        ListaAsociaciones lista_associaciones = new ListaAsociaciones(50);
        // lista_associaciones.LlegirFitxer(); falta fer el serializable
        ListaAcciones lista_acciones = new ListaAcciones();
        lista_acciones.LlegirFitxer();

        int opcio;
        mostrarMenu();
        opcio = Integer.parseInt(teclat.nextLine());
        while(opcio != 18){
            switch (opcio) {
                case 1:
                    opcio1();
                    break;
                case 2:
                    opcio2(lista_associaciones);
                    break;
                case 3:
                 opcio3();
                break;
                case 4:
                    opcio4();
                    break;
                case 5:
                    opcio5();
                    break;
                case 6:
                    opcio6(lista_acciones);
                    break;
                case 7:
                    opcio7();
                    break;
                case 8:
                    opcio8();
                    break;
                case 9:
                    opcio9();
                    break;
                case 10:
                    opcio10();
                    break;
                case 11:
                    opcio11();
                    break;
                case 12:
                    opcio12();
                    break;
                case 13:
                    opcio13();
                    break;
                case 14:
                    opcio14();
                    break;
                case 15:
                    opcio15();
                    break;
                case 16:
                    opcio16();
                    break;
                case 17:
                    opcio17();
                    break;
                case 18:
                    opcio18();
                    break;  
            }
        }
    }
    public static void mostrarMenu() {
        System.out.println("\nMenú Principal");
        System.out.println("1. Mostrar les dades de la llista d'associacions.");
        System.out.println("2. Mostrar les dades de la llista de membres que formen part d'una associació.");
        System.out.println("3. Mostrar les dades de la llista de membres actius.");
        System.out.println("4. Mostrar les dades de la llista d'accions.");
        System.out.println("5. Obtenir i mostrar la llista d'accions d'una associació concreta.");
        System.out.println("6. Obtenir i mostrar la llista de les xerrades d'una franja de dates.");
        System.out.println("7. Afegir una nova associació.");
        System.out.println("8. Alta d'un membre a una associació.");
        System.out.println("9. Afegir una nova xerrada.");
        System.out.println("10. Afegir una nova demostració.");
        System.out.println("11. Consultar demostracions no actives i calcular el cost total.");
        System.out.println("12. Calcular la persona més activa.");
        System.out.println("13. Consultar xerrades amb més d'un cert nombre d'assistents.");
        System.out.println("14. Valorar una xerrada.");
        System.out.println("15. Consultar la xerrada millor valorada.");
        System.out.println("16. Mostrar les dades de les xerrades d'una persona concreta.");
        System.out.println("17. Donar de baixa demostracions no actives abans d'una data.");
        System.out.println("18. Sortir de l'aplicació.");
    }

public static void opcio1() {}

public static void opcio2(ListaAsociaciones listaAsociaciones) 
{
    System.out.print("Introdueix el nom de l'associació': ");
        String nombreAsociacion = teclat.nextLine();

        Asociacion asociacion = listaAsociaciones.buscarAsociacion(nombreAsociacion);
        if (asociacion != null) {
            System.out.println("Selecciona el tipus de membre a mostrar:");
            System.out.println("1. Professors");
            System.out.println("2. Alumnes");
            System.out.println("3. Les dues");
            System.out.print("Introduce una opción (1-3): ");
            int filtro = Integer.parseInt(teclat.nextLine());

            ListaMiembros miembros = asociacion.getMiembros();
            boolean miembrosEncontrados = false;

            for (int i = 0; i < miembros.getNElem(); i++) {
                Miembro miembro = miembros.obtenerMiembro(i);
                if ((filtro == 1 && miembro instanceof Profesor) ||
                    (filtro == 2 && miembro instanceof Alumno) ||
                    (filtro == 3)) {
                    System.out.println(miembro);
                    miembrosEncontrados = true;
                }
            }
            if (!miembrosEncontrados) {
                System.out.println("No s'han trobat.");
            }
        } else {
            System.out.println("Associació no trobada.");
        }
    }
public static void opcio3() {}

public static void opcio4() {}

public static void opcio5() {}

public static void opcio6(ListaAcciones listaAcciones) {
    System.out.print("Introduce la fecha de inicio (dd/MM/yyyy): ");
    String fechaInicioStr = teclat.nextLine();
    System.out.print("Introduce la fecha de fin (dd/MM/yyyy): ");
    String fechaFinStr = teclat.nextLine();

    try {
        // Parseamos las fechas ingresadas por el usuario
        Fecha fechaInicio = Fecha.parse(fechaInicioStr);
        Fecha fechaFin = Fecha.parse(fechaFinStr);

        System.out.println("\nBuscando charlas entre " + fechaInicio + " y " + fechaFin + "...\n");

        boolean charlasEncontradas = false;

        for (int i = 0; i < listaAcciones.getNElem(); i++) {
            Accion accion = listaAcciones.obtenerAccion(i);

            if (accion instanceof Charla) {
                Charla charla = (Charla) accion;
                Fecha fechaCharla = charla.getFechaCharla();

                // Validar si la charla está dentro del rango
                if (!fechaCharla.before(fechaInicio) && !fechaCharla.after(fechaFin)) {
                    System.out.println("Charla encontrada:");
                    System.out.println(charla);
                    charlasEncontradas = true;
                }
            }
        }

        if (!charlasEncontradas) {
            System.out.println("No se encontraron charlas dentro de la franja de fechas indicada.");
        }
    } catch (Exception e) {
        System.out.println("Error al procesar las fechas: " + e.getMessage());
    }
}





public static void opcio7() {}

public static void opcio8() {}

public static void opcio9() {}

public static void opcio10() {}

public static void opcio11() {}

public static void opcio12() {}

public static void opcio13() {}

public static void opcio14() {}

public static void opcio15() {}

public static void opcio16() {}

public static void opcio17() {}

public static void opcio18() {}
}
