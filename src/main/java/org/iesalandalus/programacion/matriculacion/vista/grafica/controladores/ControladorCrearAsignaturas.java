package org.iesalandalus.programacion.matriculacion.vista.grafica.controladores;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ControladorCrearAsignaturas {

    @FXML
    private Button btnCrearAsignatura;

    @FXML
    private ComboBox<?> cbEspecialidadProfesor;

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
    void crearAsignaturas(ActionEvent event) {

    }

}