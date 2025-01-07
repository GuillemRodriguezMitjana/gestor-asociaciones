import java.util.*;

import datos.Accion;
import datos.Alumno;
import datos.Asociacion;
import datos.Charla;
import datos.Demostracion;
import datos.Miembro;
import datos.Profesor;
import excepciones.ExcepcionAsociacionNoEncontrada;
import excepciones.ExcepcionIndiceFueraDeRango;
import excepciones.ExcepcionListaAsociacionLlena;
import excepciones.ExcepcionMaximoValoraciones;
import listas.ListaMiembros;
import listas.ListaAcciones;
import listas.ListaAsociaciones;
import datos.Fecha;



public class Main {
    
    /*private static void mostrarlistaAcciones(ListaAcciones llista) {
        System.out.println(llista.toString());
    }
    private static void mostrarlistaAsociaciones(ListaAsociaciones llista) {
        System.out.println(llista.toString());
    }
    private static void mostrarListaMiembros(ListaMiembros llista) {
      System.out.println(llista.toString());
    }  */

    static Scanner teclat = new Scanner(System.in);
    public static void main(String[] args) throws ExcepcionIndiceFueraDeRango, ExcepcionAsociacionNoEncontrada{

        ListaAsociaciones lista_associaciones = new ListaAsociaciones(50);
        lista_associaciones.guardarDatos("asociaciones.ser");
        ListaAcciones lista_acciones = new ListaAcciones();
        lista_acciones.LlegirFitxer();
        ListaMiembros lista_miembros = new ListaMiembros();
        lista_miembros.LlegirFitxer();


        // falta cargar datos desde ficheros

        int opcio;
        mostrarMenu();
        opcio = Integer.parseInt(teclat.nextLine());
        while(opcio != 18){
            switch (opcio) {
                case 1:
                    opcio1(lista_associaciones);
                    break;
                case 2:
                    opcio2(lista_associaciones);
                    break;
                case 3:
                    opcio3(lista_associaciones);
                    break;
                case 4:
                    opcio4(lista_acciones);
                    break;
                case 5:
                    try {
                        opcio5(lista_associaciones);
                    } catch (ExcepcionAsociacionNoEncontrada e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    opcio6(lista_acciones);
                    break;
                case 7:
                    opcio7(lista_associaciones, lista_miembros);
                    break;
                case 8:
                    opcio8(lista_associaciones);
                    break;
                case 9:
                    opcio9(lista_associaciones);
                    break;
                
                case 10:
                    opcio10(lista_associaciones);
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
                    opcio16(lista_acciones, lista_associaciones);
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
            System.out.println("Sortint del programa! :)");
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

    public static void opcio1(ListaAsociaciones listaAsociaciones) throws ExcepcionIndiceFueraDeRango {
        if (listaAsociaciones.getNElem() == 0) {
            System.out.println("No hay asociaciones registradas.");
        } else {
            System.out.println("Lista de Asociaciones:");
            for (int i = 0; i < listaAsociaciones.getNElem(); i++) {
                if (i >= 0 && i < listaAsociaciones.getNElem()) { 
                    System.out.println(listaAsociaciones.obtenerAsociacion(i));
                } else {
                    System.out.println("Índice fuera de rango: " + i);
                }
            }
        }
    }
    
    

    public static void opcio2(ListaAsociaciones listaAsociaciones) {
        try {
            // Mostrar asociaciones disponibles
            System.out.println("Associacions disponibles:");
            for (int i = 0; i < listaAsociaciones.getNElem(); i++) {
                try {
                    System.out.println("- " + listaAsociaciones.obtenerAsociacion(i).getName());
                } catch (ExcepcionIndiceFueraDeRango e) {
                    System.out.println("Error al mostrar asociaciones: " + e.getMessage());
                }
            }
    
            // Solicitar nombre de la asociación
            System.out.print("Introdueix el nom de l'associació: ");
            String nombreAsociacion = teclat.nextLine().trim();
    
            Asociacion asociacion = listaAsociaciones.buscarAsociacion(nombreAsociacion);
    
            if (asociacion != null) {
                // Mostrar solo miembros que son alumnos
                System.out.println("Mostrant alumnes de l'associació: " + asociacion.getName());
                ListaMiembros miembros = asociacion.getMiembros();
                boolean alumnosEncontrados = false;
    
                for (int i = 0; i < miembros.getNElem(); i++) {
                    Miembro miembro = miembros.obtenerMiembro(i);
                    if (miembro instanceof Alumno) {
                        System.out.println(miembro);
                        alumnosEncontrados = true;
                    }
                }
    
                if (!alumnosEncontrados) {
                    System.out.println("No s'han trobat alumnes en aquesta associació.");
                }
            } else {
                System.out.println("Associació no trobada.");
            }
        } catch (Exception e) {
            System.out.println("Error al procesar la opción: " + e.getMessage());
        }
    }
    

    public static void opcio3(ListaAsociaciones listaAsociaciones) throws ExcepcionIndiceFueraDeRango 
    {
        System.out.println("Selecciona el tipus de membre actiu a mostrar:");
        System.out.println("1. Professors");
        System.out.println("2. Alumnes");
        System.out.println("3. Les dues");
        System.out.print("Introduce una opción (1-3): ");
        int filtro = Integer.parseInt(teclat.nextLine());

        boolean miembrosEncontrados = false;

        for (int i = 0; i < listaAsociaciones.getNElem(); i++) {
            Asociacion asociacion = null;
            try {
                asociacion = listaAsociaciones.obtenerAsociacion(i);
            } catch (ExcepcionIndiceFueraDeRango e) {
                e.printStackTrace();
            }
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

    public static void opcio4(ListaAcciones listaAcciones) {
        System.out.println("Mostrar accions (1. Totes, 2. Charlas, 3. Demostracions)");
        int filtro = Integer.parseInt(teclat.nextLine());
    
        String tipoFiltro = "";
        switch (filtro) {
            case 1:
                tipoFiltro = ""; 
                break;
            case 2:
                tipoFiltro = "charla";
                break;
            case 3:
                tipoFiltro = "demostracion";
                break;
            default:
                System.out.println("Opció no vàlida.");
                return; // Salimos del método si la opción no es válida
        }
    
        // Llamada al método mostrarAcciones desde la instancia de listaAcciones
        listaAcciones.mostrarAcciones(tipoFiltro);
    }
    



    public static void opcio5(ListaAsociaciones listaAsociaciones) throws ExcepcionAsociacionNoEncontrada {
        System.out.print("Introdueix el nom de l'associació: ");
        String nombreAsociacion = teclat.nextLine();
    
        Asociacion asociacion = listaAsociaciones.buscarAsociacion(nombreAsociacion);
        ListaAcciones.mostrarAccionesPorAsociacion(asociacion);
    }

public static void opcio6(ListaAcciones listaAcciones) {
        System.out.print("Introduce la fecha de inicio (dd/MM/yyyy): ");
        String fechaInicioStr = teclat.nextLine();
        System.out.print("Introduce la fecha de fin (dd/MM/yyyy): ");
        String fechaFinStr = teclat.nextLine();

        try {
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

    public static void opcio7(ListaAsociaciones listaAsociaciones, ListaMiembros listaMiembros) {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("Introduce los datos de la nueva asociación:");
    
        System.out.print("Nombre de la asociación: ");
        String nombre = scanner.nextLine().trim();
    
        System.out.print("Correo de contacto: ");
        String correo = scanner.nextLine().trim();
    
        System.out.print("Titulaciones (separadas por comas): ");
        String[] titulaciones = scanner.nextLine().split(",");
    
        Asociacion nuevaAsociacion = new Asociacion(nombre, correo, titulaciones);
    
        System.out.println("Introduce los datos de los miembros con cargos (solo alumnos):");
    
        Miembro presidente = null;
        Miembro secretario = null;
        Miembro tesorero = null;
    
        // Validar y asignar presidente
        System.out.print("Alias del presidente: ");
        String aliasPresidente = scanner.nextLine().trim();
        presidente = listaMiembros.buscarMiembro(aliasPresidente);
        if (presidente == null || !(presidente instanceof Alumno)) {
            System.out.println("Error: El presidente debe ser un alumno válido.");
            return;
        }
    
        // Validar y asignar secretario
        System.out.print("Alias del secretario: ");
        String aliasSecretario = scanner.nextLine().trim();
        secretario = listaMiembros.buscarMiembro(aliasSecretario);
        if (secretario == null || !(secretario instanceof Alumno)) {
            System.out.println("Error: El secretario debe ser un alumno válido.");
            return;
        }
    
        // Validar y asignar tesorero
        System.out.print("Alias del tesorero: ");
        String aliasTesorero = scanner.nextLine().trim();
        tesorero = listaMiembros.buscarMiembro(aliasTesorero);
        if (tesorero == null || !(tesorero instanceof Alumno)) {
            System.out.println("Error: El tesorero debe ser un alumno válido.");
            return;
        }
    
        // Asignar cargos en la asociación
        nuevaAsociacion.setPresidente(presidente);
        nuevaAsociacion.setSecretario(secretario);
        nuevaAsociacion.setTesorero(tesorero);
    
        // Asegurarnos de agregar los miembros con cargos a la lista de miembros de la asociación
        ListaMiembros miembrosAsociacion = nuevaAsociacion.getMiembros();
        if (miembrosAsociacion.buscarMiembro(aliasPresidente) == null) {
            miembrosAsociacion.agregarMiembro(presidente);
        }
        if (miembrosAsociacion.buscarMiembro(aliasSecretario) == null) {
            miembrosAsociacion.agregarMiembro(secretario);
        }
        if (miembrosAsociacion.buscarMiembro(aliasTesorero) == null) {
            miembrosAsociacion.agregarMiembro(tesorero);
        }
    
        // Intentar agregar la asociación a la lista
        try {
            if (listaAsociaciones.agregarAsociacion(nuevaAsociacion)) {
                System.out.println("Asociación añadida con éxito: " + nuevaAsociacion);
            } else {
                System.out.println("Error: No se pudo añadir la asociación. La lista está llena.");
            }
        } catch (ExcepcionListaAsociacionLlena e) {
            System.out.println("Error al añadir la asociación: " + e.getMessage());
        }
    }
    
    
    


    public static void opcio8 (ListaAsociaciones listaAsociaciones) throws ExcepcionIndiceFueraDeRango {
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
            Asociacion assoc = null;
            try {
                assoc = listaAsociaciones.obtenerAsociacion(i);
            } catch (ExcepcionIndiceFueraDeRango e) {
                e.printStackTrace();
            }
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

public static void opcio9(ListaAsociaciones listaAsociaciones) {
        Scanner scanner = new Scanner(System.in); // Crear Scanner local sin cerrarlo
        System.out.print("Introdueix el nom de l'associació: ");
        String nombreAsociacion = scanner.nextLine();
    
        Asociacion asociacion = listaAsociaciones.buscarAsociacion(nombreAsociacion);
    
        if (asociacion != null) {
            ListaAcciones.agregarNuevaCharla(asociacion);
        } else {
            System.out.println("Associació no trobada.");
        }
    }
    public static void opcio10(ListaAsociaciones listaAsociaciones) {
        Scanner scanner = new Scanner(System.in); // Crear Scanner local sin cerrarlo
        System.out.print("Introdueix el nom de l'associació: ");
        String nombreAsociacion = scanner.nextLine();
    
        Asociacion asociacion = listaAsociaciones.buscarAsociacion(nombreAsociacion);
    
        if (asociacion != null) {
            ListaAcciones.agregarNuevaDemostracion(asociacion);
        } else {
            System.out.println("Associació no trobada.");
        }
    }
    

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


    public static void opcio12(ListaAsociaciones listaAsociaciones) throws ExcepcionIndiceFueraDeRango 
    {
        Miembro personaMasActiva = null;
        int maxAsociaciones = 0;
        String fechaMasAntigua = null;

        for (int i = 0; i < listaAsociaciones.getNElem(); i++) {
            Asociacion asociacion = null;
            try {
                asociacion = listaAsociaciones.obtenerAsociacion(i);
            } catch (ExcepcionIndiceFueraDeRango e) {
                e.printStackTrace();
            }
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


public static void opcio13() {
    System.out.print("Introdueix el nombre mínim d'assistents: ");
    int minAsistentes = Integer.parseInt(teclat.nextLine());
   ListaAcciones.mostrarCharlasConMasAsistentes(minAsistentes);
}

    

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


public static void opcio15() {
    ListaAcciones.mostrarCharlaMejorValorada();
}

    

public static void opcio16(ListaAcciones listaAcciones, ListaAsociaciones listaAsociaciones) throws ExcepcionIndiceFueraDeRango {
    Scanner scanner = new Scanner(System.in); // Crear Scanner local sin cerrarlo

    System.out.print("Introduce el alias de la persona: ");
    String alias = scanner.nextLine();

    Miembro miembro = null;
    int i = 0;

    // Buscar al miembro en todas las asociaciones
    while (i < listaAsociaciones.getNElem() && miembro == null) {
        Asociacion asociacion = listaAsociaciones.obtenerAsociacion(i);
        miembro = asociacion.getMiembros().buscarMiembro(alias);
        i++;
    }

    if (miembro == null) {
        System.out.println("Error: No se encontró ninguna persona con el alias proporcionado.");
    } else {
        System.out.println("Charlas impartidas por: " + alias);

        boolean charlasEncontradas = false;

        // Buscar charlas impartidas por el miembro
        for (int j = 0; j < listaAcciones.getNElem(); j++) {
            if (listaAcciones.obtenerAccion(j) instanceof Charla) {
                Charla charla = (Charla) listaAcciones.obtenerAccion(j);

                if (charla.getMiembrosImpartidores().buscarMiembro(alias) != null) {
                    System.out.println(charla);
                    charlasEncontradas = true;
                }
            }
        }

        if (!charlasEncontradas) {
            System.out.println("No se encontraron charlas impartidas por esta persona.");
        }
    }
}



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

    public static void opcio18(ListaAcciones listaAcciones, ListaAsociaciones listaAsociaciones, ListaMiembros listaMiembros) 
    {
        System.out.println("Guardando datos antes de salir...");
    
    // Guardar lista de asociaciones
    try {
        listaAsociaciones.guardarDatos("asociaciones.ser");
        System.out.println("Asociaciones guardadas correctamente.");
    } catch (Exception e) {
        System.out.println("Error al guardar asociaciones: " + e.getMessage());
    }
    // Guardar lista de acciones
    try {
        listaAcciones.LlegirFitxer();
        System.out.println("Acciones guardadas correctamente.");
    } catch (Exception e) {
        System.out.println("Error al guardar acciones: " + e.getMessage());
    }

    // Guardar lista de miembros
    try {
        listaMiembros.EscriureFitxer("miembros.txt");
        System.out.println("Miembros guardados correctamente.");
    } catch (Exception e) {
        System.out.println("Error al guardar miembros: " + e.getMessage());
    }

    System.out.println("Datos guardados correctamente. Saliendo del programa.");
}
    }

