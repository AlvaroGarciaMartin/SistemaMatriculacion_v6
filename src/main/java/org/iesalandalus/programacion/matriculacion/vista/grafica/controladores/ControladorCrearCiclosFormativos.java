package org.iesalandalus.programacion.matriculacion.vista.grafica.controladores;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Grado;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.TiposGrado;
import org.iesalandalus.programacion.matriculacion.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.matriculacion.vista.grafica.utilidades.Dialogos;

public class ControladorCrearCiclosFormativos {

    @FXML
    private Button btnAceptarCrearCiclo;

    @FXML
    private ComboBox <TiposGrado> cbElegirGrado;

    @FXML
    private TextField tfFamiliaCiclo;


    @FXML
    private TextField tfHorasCiclo;

    @FXML
    private TextField tfNombreCiclo;

    @FXML
    private TextField tfcodigoCiclo;

    @FXML
    public void initialize() {
        cargarOpcionesGrados();
    }

    private ObservableList<TiposGrado> obsListadoOpcionesChoice=
            FXCollections.observableArrayList(TiposGrado.values());

    private void cargarOpcionesGrados() {
        cbElegirGrado.setItems(obsListadoOpcionesChoice);
    }

    @FXML
    void CrearCicloFormativo(ActionEvent event) {
        /*try {

            int codigo = Integer.parseInt(tfcodigoCiclo.getText());
            String familia = tfFamiliaCiclo.getText();
            Grado grado = cbElegirGrado.getValue();
            String nombre = tfNombreCiclo.getText();
            int horas = Integer.parseInt(tfHorasCiclo.getText());

            if (codigo < 0 || codigo > 9999 || familia.trim().isBlank() || grado.toString().trim().isBlank() || nombre.trim().isBlank() || horas < 0) {
                return;
            }
            CicloFormativo ciclo = new CicloFormativo(codigo, familia, grado, nombre, horas);
            VistaGrafica.getInstancia().getControlador().insertar(ciclo);
            Dialogos.mostrarDialogoTexto("Ciclo Formativo insertado","Ciclo Formativo insertado correctamente");
        } catch (Exception e) {
            Dialogos.mostrarDialogoError("Error al insertar Ciclo Formativo",e.getMessage());
        }*/
    }

}
