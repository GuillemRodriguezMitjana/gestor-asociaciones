package src.actionListeners;
import javax.swing.*;

import src.listas.ListaAcciones;
import src.datos.Demostracion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.swing.JOptionPane;

public class AccionBoton implements ActionListener {

    private ListaAcciones listaAcciones;

    public AccionBoton(ListaAcciones listaAcciones) {
        this.listaAcciones = listaAcciones;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "Filtrar":
                filtrarDemostraciones();
                break;

            case "Detalles":
                mostrarDetallesDemostracion();
                break;

            default:
                JOptionPane.showMessageDialog(null, "Acción no reconocida: " + comando, "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
    /**
     * Filtra las demostraciones activas por asociación seleccionada.
     */
    private void filtrarDemostraciones() {
        String asociacion = JOptionPane.showInputDialog(null, "Introduce el nombre de la asociación para filtrar:");
        if (asociacion == null || asociacion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se introdujo ninguna asociación.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        var filtradas = listaAcciones.filtrarPorAsociacion(asociacion);

        if (filtradas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron demostraciones activas para la asociación especificada.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder mensaje = new StringBuilder("Demostraciones activas para " + asociacion + ":\n");
            for (Demostracion demo : filtradas) {
                mensaje.append(demo.getTitulo()).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensaje.toString(), "Demostraciones Filtradas", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Muestra los detalles completos de una demostración seleccionada.
     */
    private void mostrarDetallesDemostracion() {
        String codigo = JOptionPane.showInputDialog(null, "Introduce el código de la demostración:");
        if (codigo == null || codigo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se introdujo ningún código.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Demostracion demo = listaAcciones.buscarPorCodigo(codigo);
        if (demo == null) {
            JOptionPane.showMessageDialog(null, "No se encontró ninguna demostración con el código especificado.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, demo.toString(), "Detalles de la Demostración", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}