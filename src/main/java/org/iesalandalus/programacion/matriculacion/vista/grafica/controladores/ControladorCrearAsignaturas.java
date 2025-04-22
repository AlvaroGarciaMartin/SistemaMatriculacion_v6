package org.iesalandalus.programacion.matriculacion.vista.grafica.controladores;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.EspecialidadProfesorado;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.TiposGrado;

public class ControladorCrearAsignaturas {

    @FXML
    private Button btnCrearAsignatura;

    @FXML
    private ComboBox<EspecialidadProfesorado> cbEspecialidadProfesor;

    @FXML
    private HBox crearAsignaturas;

    @FXML
    private TextField tfCodigoAsignatura;

    @FXML
    private Label tfCodigoCicloAsignatura;

    @FXML
    private TextField tfCurso;

    @FXML
    private TextField tfHorasAnuales;

    @FXML
    private TextField tfHorasDesdoble;

    @FXML
    private TextField tfNombreAsignatura;

    @FXML
    void seleccionarEspecialidad(ActionEvent event) {
        System.out.println(cbEspecialidadProfesor.getValue());
    }

//    @FXML
//    void crearAsignaturas(ActionEvent event) {
//
//    }

    public void initialize() {

        cargarEspecialidadProfesor();

    }
    private ObservableList<EspecialidadProfesorado> obsListadoOpcionesChoice=
            FXCollections.observableArrayList(EspecialidadProfesorado.values());

    private void cargarEspecialidadProfesor() {
        cbEspecialidadProfesor.setItems(obsListadoOpcionesChoice);
        cbEspecialidadProfesor.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->modificadoComboBoxListadoOpciones(oldValue,newValue));
    }
    private void modificadoComboBoxListadoOpciones(EspecialidadProfesorado oldValue, EspecialidadProfesorado newValue) {
        System.out.println(oldValue+" -> "+newValue);
    }

}