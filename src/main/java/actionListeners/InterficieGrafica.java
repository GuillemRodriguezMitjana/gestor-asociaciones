package actionListeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import listas.ListaAcciones;
import listas.ListaAsociaciones;
import datos.Demostracion;
import excepciones.*;
import datos.Asociacion;

public class InterficieGrafica extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel panelFiltro;
    private JPanel panelDemostraciones;
    private JTextArea detalleDemostracion;
    private JComboBox<String> filtroAsociaciones;
    private JButton botonMostrar;
    private JList<String> listaDemostraciones;
    private DefaultListModel<String> modeloLista;

    private ListaAcciones listaAcciones;
    private ListaAsociaciones listaAsociaciones;

    public InterficieGrafica(String titulo, ListaAcciones acciones, ListaAsociaciones asociaciones) {
        super(titulo);
        this.listaAcciones = acciones;
        this.listaAsociaciones = asociaciones;

        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        // Panel de Filtro
        panelFiltro = new JPanel(new FlowLayout());
        filtroAsociaciones = new JComboBox<>();
        filtroAsociaciones.addItem("Todas");

        for (int i = 0; i < listaAsociaciones.getNElem(); i++) {
            Asociacion asociacion = null;
            try {
                asociacion = listaAsociaciones.obtenerAsociacion(i);
            } catch (ExcepcionIndiceFueraDeRango e) {
                System.out.println("...");
            }
            filtroAsociaciones.addItem(asociacion.getName());
        }

        botonMostrar = new JButton("Mostrar Demostraciones Activas");
        botonMostrar.addActionListener(new MostrarDemostracionesListener());

        panelFiltro.add(new JLabel("Filtrar por Asociación:"));
        panelFiltro.add(filtroAsociaciones);
        panelFiltro.add(botonMostrar);

        // Panel de Demostraciones
        panelDemostraciones = new JPanel(new BorderLayout());
        modeloLista = new DefaultListModel<>();
        listaDemostraciones = new JList<>(modeloLista);
        listaDemostraciones.addListSelectionListener(e -> mostrarDetalle());

        detalleDemostracion = new JTextArea(10, 30);
        detalleDemostracion.setEditable(false);
        JScrollPane scrollDetalle = new JScrollPane(detalleDemostracion);

        panelDemostraciones.add(new JScrollPane(listaDemostraciones), BorderLayout.CENTER);
        panelDemostraciones.add(scrollDetalle, BorderLayout.SOUTH);
    }

    private void configurarVentana() {
        this.setLayout(new BorderLayout(10, 10));
        this.add(panelFiltro, BorderLayout.NORTH);
        this.add(panelDemostraciones, BorderLayout.CENTER);
        this.setSize(600, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void mostrarDetalle() {
        int index = listaDemostraciones.getSelectedIndex();
        if (index != -1) {
            String seleccion = modeloLista.getElementAt(index);
            String codigo = seleccion.split(" - ")[0];
            Demostracion demo = (Demostracion) listaAcciones.buscarAccionPorCodigo(codigo);

            if (demo != null) {
                detalleDemostracion.setText("Código: " + demo.getCodigo() + "\n" +
                                           "Título: " + demo.getTitulo() + "\n" +
                                           "Fecha de Diseno: " + demo.getFechaDiseño() + "\n" +
                                           "Costo Materiales: " + demo.getCosteMateriales() + "\n" +
                                           "Veces Ofrecida: " + demo.getVecesOfrecida() + "\n" +
                                           "Activa: " + demo.isActiva() + "\n");
            }
        }
    }

    private class MostrarDemostracionesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            modeloLista.clear();
            String seleccionFiltro = (String) filtroAsociaciones.getSelectedItem();

            for (int i = 0; i < listaAcciones.getNElem(); i++) {
                if (listaAcciones.obtenerAccion(i) instanceof Demostracion) {
                    Demostracion demo = (Demostracion) listaAcciones.obtenerAccion(i);

                    /*
                    if (demo.isActiva() && ("Todas".equals(seleccionFiltro) ||
                        seleccionFiltro.equals(demo.getAsociacion().getName()))) {
                        modeloLista.addElement(demo.getCodigo() + " - " + demo.getTitulo());
                    }*/
                }
            }
        }
    }

    public static void main(String[] args) {
        
        ListaAcciones acciones = new ListaAcciones();
        acciones.LlegirFitxer();

        ListaAsociaciones asociaciones = new ListaAsociaciones(50);
        asociaciones.guardarDatos();

        new InterficieGrafica("Gestor de Demostracions", acciones, asociaciones);
    }
}
