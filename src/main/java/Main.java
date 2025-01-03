import java.util.*;

import datos.Accion;
import datos.Alumno;
import datos.Asociacion;
import datos.Charla;
import datos.Demostracion;
import datos.Miembro;
import datos.Profesor;
import excepciones.ExcepcionMaximoValoraciones;
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
        ListaMiembros lista_miembros = new ListaMiembros();
        // lista_miembros.LlegitFitxer(); falta fer funcio

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
                    opcio3(lista_associaciones);
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
                    opcio8(lista_associaciones);
                    break;
                case 9:
                    opcio9();
                    break;
                case 10:
                    opcio10();
                    break;
                case 11:
                    opcio11(lista_acciones);
                    break;
        
                case 12:
                    opcio12(lista_associaciones);
                    break;
                case 13:
                    opcio13();
                    break;
                case 14:
                    System.out.print("Introdueix el codi de la xerrada a valorar: ");
                    String codiXerrada = teclat.nextLine();
                    Charla charla = (Charla) lista_acciones.buscarAccionPorCodigo(codiXerrada);

                    if (charla != null) {
                        opcio14(charla);
                    } else {
                        System.out.println("No s'ha trobat cap xerrada amb aquest codi.");
                    }
                    break;
                case 15:
                    opcio15();
                    break;
                case 16:
                    opcio16();
                    break;
                case 17:
                    opcio17(lista_acciones);
                    break;  
            }
            mostrarMenu();
            opcio = Integer.parseInt(teclat.nextLine());
        }
        if (opcio == 18) 
        {
            opcio18(lista_acciones, lista_associaciones, lista_miembros);
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

public static void opcio3(ListaAsociaciones listaAsociaciones) 
{
    System.out.println("Selecciona el tipus de membre actiu a mostrar:");
    System.out.println("1. Professors");
    System.out.println("2. Alumnes");
    System.out.println("3. Les dues");
    System.out.print("Introduce una opción (1-3): ");
    int filtro = Integer.parseInt(teclat.nextLine());

    boolean miembrosEncontrados = false;

    for (int i = 0; i < listaAsociaciones.getNElem(); i++) {
        Asociacion asociacion = listaAsociaciones.obtenerAsociacion(i);
        ListaMiembros miembros = asociacion.getMiembros();

        for (int j = 0; j < miembros.getNElem(); j++) {
            Miembro miembro = miembros.obtenerMiembro(j);

            if (miembro.estaActivo() && 
                ((filtro == 1 && miembro instanceof Profesor) ||
                 (filtro == 2 && miembro instanceof Alumno) ||
                 (filtro == 3))) {
                System.out.println(miembro);
                miembrosEncontrados = true;
            }
        }
    }

    if (!miembrosEncontrados) {
        System.out.println("No s'han trobat membres actius.");
    }
}

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

public static void opcio8 (ListaAsociaciones listaAsociaciones) {
    System.out.print("Introdueix el nom de l'associació: ");
    String nombreAsociacion = teclat.nextLine();

    Asociacion asociacion = listaAsociaciones.buscarAsociacion(nombreAsociacion);
    if (asociacion == null) {
        System.out.println("Associació no trobada.");
        return;
    }

    System.out.print("Introdueix l'alias del membre: ");
    String alias = teclat.nextLine();

    Miembro miembro = null;
    int i = 0;
    while (miembro == null && i < listaAsociaciones.getNElem()) {
        Asociacion assoc = listaAsociaciones.obtenerAsociacion(i);
        ListaMiembros miembros = assoc.getMiembros();

        int j = 0;
        while (miembro == null && j < miembros.getNElem()) {
            Miembro m = miembros.obtenerMiembro(j);
            if (m.getAlias().equals(alias)) {
                miembro = m;
            }
            j++;
        }
        i++;
    }

    if (miembro == null) {
        System.out.print("Introdueix el correu electrònic del nou membre: ");
        String correo = teclat.nextLine();

        System.out.println("Selecciona el tipus de membre:");
        System.out.println("1. Professor");
        System.out.println("2. Alumne");
        System.out.print("Introdueix una opció (1-2): ");
        int tipoMiembro = Integer.parseInt(teclat.nextLine());

        if (tipoMiembro == 1) {
            System.out.print("Introdueix la data d'alta: ");
            String fechaAlta = teclat.nextLine();
            System.out.print("Introdueix el departament: ");
            String departamento = teclat.nextLine();
            System.out.print("Introdueix el número de despatx: ");
            int numDespacho = Integer.parseInt(teclat.nextLine());

            miembro = new Profesor(alias, correo, fechaAlta, departamento, numDespacho);
        } else if (tipoMiembro == 2) {
            System.out.print("Introdueix la data d'alta: ");
            String fechaAlta = teclat.nextLine();
            System.out.print("Introdueix la titulació: ");
            String titulacion = teclat.nextLine();

            miembro = new Alumno(alias, correo, fechaAlta, titulacion);
        } else {
            System.out.println("Tipus de membre no vàlid.");
            return;
        }

        asociacion.getMiembros().agregarMiembro(miembro);
        System.out.println("Nou membre creat i afegit a l'associació.");
    } else {
        try {
            miembro.agregarAsociacion(asociacion);
            asociacion.getMiembros().agregarMiembro(miembro);
            System.out.println("El membre ha estat donat d'alta correctament a l'associació.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

public static void opcio9() {}

public static void opcio10() {}

public static void opcio11(ListaAcciones listaAcciones) {
    System.out.println("Consultant demostracions no actives...\n");

    double costoTotal = 0.0;
    int demostracionesNoActivas = 0;

    for (int i = 0; i < listaAcciones.getNElem(); i++) {
        Accion accion = listaAcciones.obtenerAccion(i);

        if (accion instanceof Demostracion) {
            Demostracion demostracion = (Demostracion) accion;

            if (!demostracion.isActiva()) {
                 demostracionesNoActivas++;

                System.out.println("Demostració no activa trobada:");
                System.out.println("- Codi: " + demostracion.getCodigo());
                System.out.println("- Títol: " + demostracion.getTitulo());
                System.out.println("- Data de diseny: " + demostracion.getFechaDiseño());
                System.out.println("- Cops : " + demostracion.getVecesOfrecida());
                System.out.println("- Cost de materials: " + demostracion.getCosteMateriales() + "\n");

                costoTotal += demostracion.getCosteMateriales();
            }
        }
    }

    if (demostracionesNoActivas == 0) {
        System.out.println("No s'han' demostracions no actives.");
    } else {
        System.out.println("Cost total de les demostracions no actives: " + costoTotal);
    }
}


public static void opcio12(ListaAsociaciones listaAsociaciones) 
{
    Miembro personaMasActiva = null;
    int maxAsociaciones = 0;
    String fechaMasAntigua = null;

    for (int i = 0; i < listaAsociaciones.getNElem(); i++) {
        Asociacion asociacion = listaAsociaciones.obtenerAsociacion(i);
        ListaMiembros miembros = asociacion.getMiembros();

        for (int j = 0; j < miembros.getNElem(); j++) {
            Miembro miembro = miembros.obtenerMiembro(j);
            int numAsociaciones = miembro.getAsociaciones().getNElem();
            String fechaAlta = miembro.getFechaAlta();

            if (numAsociaciones > maxAsociaciones ||
                (numAsociaciones == maxAsociaciones && (fechaMasAntigua == null || fechaAlta.compareTo(fechaMasAntigua) < 0))) {

                personaMasActiva = miembro;
                maxAsociaciones = numAsociaciones;
                fechaMasAntigua = fechaAlta;
            }
        }
    }

    if (personaMasActiva != null) {
        System.out.println("La persona més activa és: " + personaMasActiva);
    } else {
        System.out.println("No s'ha trobat cap persona activa.");
    }
}


public static void opcio13() {}

public static void opcio14(Charla charla) {
    System.out.print("Introdueix la teva valoració de la xerrada (0 a 10): ");
    int valoracio = Integer.parseInt(teclat.nextLine());

    if (valoracio < 0 || valoracio > 10) {
        System.out.println("Valoració no vàlida. Ha de ser un valor entre 0 i 10.");
        return;
    }

    try {
        charla.agregarValoracion(valoracio);
        System.out.println("Gràcies per valorar la xerrada! La valoració mitjana actual és: " + charla.obtenerPromedioValoraciones());
    } catch (ExcepcionMaximoValoraciones e) {
        System.out.println("No es poden afegir més valoracions: " + e.getMessage());
    }
}


public static void opcio15() {}

public static void opcio16() {}

public static void opcio17(ListaAcciones listaAcciones) {
    System.out.println("Donar de baixa demostracions no actives dissenyades abans d'una data específica.\n");
    System.out.print("Introdueix la data límit (dd/MM/yyyy): ");
    String fechaLimiteStr = teclat.nextLine();

 
    Fecha fechaLimite = Fecha.parse(fechaLimiteStr);

    boolean eliminacionesRealizadas = false;

    // Buscar no actives
    for (int i = 0; i < listaAcciones.getNElem(); i++) {
        Accion accion = listaAcciones.obtenerAccion(i);

        if (accion instanceof Demostracion) {
            Demostracion demostracion = (Demostracion) accion;

            if (!demostracion.isActiva() && demostracion.getFechaDiseño().before(fechaLimite)) {
                // Eliminar la demostració
                listaAcciones.eliminarAccion(i);
                i--; // Ajustar l'index 

                // Info
                System.out.println("Demostració eliminada:");
                System.out.println("- Codi: " + demostracion.getCodigo());
                System.out.println("- Títol: " + demostracion.getTitulo());
                System.out.println("- Data de disseny: " + demostracion.getFechaDiseño() + "\n");

                eliminacionesRealizadas = true;
            }
        }
    }

    // Missatge
    if (!eliminacionesRealizadas) {
        System.out.println("No s'han trobat demostracions no actives a eliminar abans de la data indicada.");
    } else {
        System.out.println("S'han eliminat les demostracions no actives dissenyades abans de " + fechaLimite + ".");
    }
}



public static void opcio18(ListaAcciones listaAcciones, ListaAsociaciones listaAccionesa, ListaMiembros listaMiembros) {}
}
