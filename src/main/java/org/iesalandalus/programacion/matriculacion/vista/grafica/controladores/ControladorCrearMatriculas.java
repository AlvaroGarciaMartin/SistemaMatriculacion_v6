package org.iesalandalus.programacion.matriculacion.vista.grafica.controladores;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.matriculacion.vista.grafica.utilidades.Dialogos;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ControladorCrearMatriculas {

    @FXML private Button btnCrearMatricula;
    @FXML private ComboBox<Asignatura> cbAsignaturasDisponibles;
    @FXML private VBox crearMatriculas;
    @FXML private DatePicker dpFechaMatricula;
    @FXML private TextField tfCursoAcademico;
    @FXML private TextField tfDniAlumno;
    @FXML private TextField tfIdMatricula;
    @FXML private TextField tfCodigoCiclo;

    ArrayList<Asignatura> asignaturasfiltradas = new ArrayList<>();



    @FXML
    public void initialize()
    {
        dpFechaMatricula.setValue(LocalDate.now());
    }
    @FXML
    void insertarMatriculas(ActionEvent event) {


        Stage escenarioMatriculas = (Stage) btnCrearMatricula.getScene().getWindow();

        try{

            int idMatricula = Integer.parseInt(tfIdMatricula.getText());
            String cursoAcademico = tfCursoAcademico.getText();
            LocalDate fechaMatricula = dpFechaMatricula.getValue();
            String dniAlumno = tfDniAlumno.getText();
            ArrayList<Asignatura> asignaturas = new ArrayList<>();
            Alumno alumno = VistaGrafica.getInstancia().getControlador().buscar(new Alumno("ficticio", "666666666", "ficticio@inventado.es",  dniAlumno, LocalDate.of(1991, 12, 12)));
            if (alumno == null) {
                Dialogos.mostrarDialogoInformacion("Alumno no encontrado", "El alumno con DNI " + dniAlumno + " no existe");
            }else {
                Asignatura asignatura = cbAsignaturasDisponibles.getValue();
                asignaturas.add(asignatura);
                Matricula matricula = new Matricula(idMatricula, cursoAcademico, fechaMatricula, alumno, asignaturas);
                
                VistaGrafica.getInstancia().getControlador().insertar(matricula);
                Dialogos.mostrarDialogoInformacion("Matricula insertada", "Matricula insertada correctamente");
                escenarioMatriculas.close();
            }
        }catch (Exception e){
            Dialogos.mostrarDialogoError("Error al insertar la matricula",e.getMessage());

        }

    }
    @FXML
    void listaAsignaturasDisponibles(ActionEvent event){

        cbAsignaturasDisponibles.getItems().setAll(asignaturasfiltradas);
    }

    @FXML
    void filtrarCicloFormativo(InputMethodEvent event) {
        String codigoCiclo = tfCodigoCiclo.getText();
        if(codigoCiclo.length() < 4) {
            return;
        }
       try {


           ArrayList<Asignatura> asignaturas = VistaGrafica.getInstancia().getControlador().getAsignaturas();
           for (Asignatura asignatura : asignaturas) {
               if (asignatura.getCicloFormativo().equals(codigoCiclo)) {
                   asignaturasfiltradas.add(asignatura);
               }
           }

       }catch (Exception e){
           Dialogos.mostrarDialogoError("Error al filtrar las asignaturas",e.getMessage());
    }

    }


}
