package org.iesalandalus.programacion.matriculacion.vista.grafica.controladores;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.matriculacion.vista.grafica.utilidades.Dialogos;

import java.time.LocalDate;

public class ControladorCrearMatriculas {

    @FXML private Button btnCrearMatricula;
    @FXML private ComboBox<Asignatura> cbAsignaturasDisponibles;
    @FXML private VBox crearMatriculas;
    @FXML private DatePicker dpFechaMatricula;
    @FXML private TextField tfCursoAcademico;
    @FXML private TextField tfDniAlumno;
    @FXML private TextField tfIdMatricula;


    @FXML
    public void initialize()
    {
        dpFechaMatricula.setValue(LocalDate.now());
    }
    @FXML
    void insertarMatriculas(ActionEvent event) {

//        Stage escenarioMatriculas = (Stage) btnCrearMatricula.getScene().getWindow();
//
//        try{
//
//            int idMatricula = Integer.parseInt(tfIdMatricula.getText());
//            String cursoAcademico = tfCursoAcademico.getText();
//            LocalDate fechaMatricula = dpFechaMatricula.getValue();
//            String dniAlumno = tfDniAlumno.getText();
//            Alumno alumno = VistaGrafica.getInstancia().getControlador().buscar(parseString(dniAlumno);
//            Asignatura asignatura = cbAsignaturasDisponibles.getValue();
//            Matricula matricula = new Matricula(idMatricula, cursoAcademico, fechaMatricula, alumno, new Asignatura[]{asignatura});
//
//
//            VistaGrafica.getInstancia().getControlador().insertar(matricula);
//            Dialogos.mostrarDialogoInformacion("Matricula insertada","Matricula insertada correctamente");
//            escenarioMatriculas.close();
//        }catch (Exception e){
//            Dialogos.mostrarDialogoError("Error al insertar la matricula",e.getMessage());
//
//        }

    }

}
