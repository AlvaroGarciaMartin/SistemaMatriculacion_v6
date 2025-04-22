package org.iesalandalus.programacion.matriculacion.vista.grafica.controladores;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.matriculacion.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.matriculacion.vista.grafica.utilidades.Dialogos;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ControladorVentanaPrincipal {

    @FXML private BorderPane bdVentanaPrincipal;
    @FXML private ImageView btnBusqueda;
    @FXML private Button btnCrearAlumnos;
    @FXML private Button btnCrearAsignaturas;
    @FXML private Button btnCrearCiclos;
    @FXML private Button btnCrearMatriculas;
    @FXML private ComboBox<String> cbSelectorBusqueda;
    @FXML private HBox hbMenuBusqueda;
    @FXML private TableView<Alumno> tablCentralBusquedasAlumno;
    @FXML private TableColumn<Alumno, String> tablColum1;
    @FXML private TableColumn<Alumno, Integer> tablColum2;
    @FXML private TableColumn<Alumno, String> tablColum3;
    @FXML private TableColumn<Alumno, String> tablColum4;
    @FXML private TableColumn<Alumno, String> tablColum5;
    //@FXML private TableColumn<Alumno, LocalDate> tablColum5;
    @FXML private TableView<?> tablFiltro;
    @FXML private TableColumn<?, ?> tablFiltroColum1;
    @FXML private TableColumn<?, ?> tablFiltroColum2;
    @FXML private TableColumn<?, ?> tablFiltroColum3;
    @FXML private TableColumn<?, ?> tablFiltroColum4;
//    @FXML private ToolBar tbBotonesPrincipales;
//    @FXML private TextField tfBusqueda;
//    @FXML private VBox vbBotonesPrincipales;
//    @FXML private VBox vbDesplegables;
    // variables tabla ciclo formativo
    @FXML private TableColumn<?, ?> columCicloAnios;
    @FXML private TableColumn<?, ?> columCicloCodigo;
    @FXML private TableColumn<?, ?> columCicloFamPro;
    @FXML private TableColumn<?, ?> columCicloHoras;
    @FXML private TableColumn<?, ?> columCicloNombre;
    @FXML private TableColumn<?, ?> columCicloNombreGrado;
    @FXML private TableColumn<?, ?> columCicloGrado;
    @FXML private TableColumn<?, ?> columCicloModalidad;
    @FXML private TableColumn<?, ?> columCicloNumEdiciones;
    // fin variables ciclo formativo
    @FXML private TableColumn<?, ?> ColumNombreAsignatura;

    @FXML private TableColumn<?, ?> cokumCursoAcademico;

    @FXML private TableColumn<?, ?> columCodigoAsignatura;

    @FXML private TableColumn<?, ?> columCodigoCiclo;

    @FXML private TableColumn<?, ?> columCurso;

    @FXML private TableColumn<?, ?> columDniMatricula;

    @FXML private TableColumn<?, ?> columEspecialidad;

    @FXML private TableColumn<?, ?> columFechaMatriculacion;

    @FXML private TableColumn<?, ?> columHorasAnuales;

    @FXML private TableColumn<?, ?> columHorasDesdoble;

    @FXML private TableColumn<?, ?> columIdMatricula;

    @FXML private ToolBar tbBotonesPrincipales;

    @FXML private TextField tfBusqueda;

    @FXML private VBox vbBotonesPrincipales;

    @FXML private VBox vbDesplegables;

    private List<Alumno> coleccionAlumnos = new ArrayList<>();
    private ObservableList<Alumno> alumnosObservable = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        try {
            mostrarTablaAlumno();
            mostrarTablaCiclosFormativos();
//            mostrarTablaAsignaturas();
//            mostrarTablaMatriculas();

            tablCentralBusquedasAlumno.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    mostrarFiltrado();
                } else {
                    tablFiltro.getItems().clear();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @FXML
    public void crearAlumnos(ActionEvent event){
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/CrearAlumnos.fxml"));
        Parent raiz = null;

            raiz = fxmlLoader.load();

        Stage escenarioAlumnos = new Stage();
        Scene escena = new Scene(raiz/*, 600, 600*/);
        escenarioAlumnos.setTitle("Crear Alumnos");
        escenarioAlumnos.setScene(escena);
        escenarioAlumnos.initModality(Modality.APPLICATION_MODAL);
        escenarioAlumnos.setOnCloseRequest(e->confirmaCierreAlumnos(escenarioAlumnos,e));
        escenarioAlumnos.setResizable(false);
        escenarioAlumnos.showAndWait();
        } catch (IOException e) {
            Dialogos.mostrarDialogoError("Creador de Alumnos", "Error al crear el Alumno");
        }
    }
    private void confirmaCierreAlumnos(Stage escenarioAlumnos, WindowEvent e)
    {
        if (Dialogos.mostrarDialogoConfirmacion("Creador de Alumnos", "¿Realmente quieres salir sin guardar el Alumno?"))
        {
            escenarioAlumnos.close();
        }
        else
            e.consume();
    }

    @FXML
    void crearCiclos(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/CrearCiclos.fxml"));
            Parent raiz = null;

            raiz = fxmlLoader.load();

            Stage escenarioCiclosFormativos = new Stage();
            Scene escena = new Scene(raiz/*, 600, 600*/);
            escenarioCiclosFormativos.setTitle("Crear Ciclos Formativos");
            escenarioCiclosFormativos.setScene(escena);
            escenarioCiclosFormativos.initModality(Modality.APPLICATION_MODAL);
            escenarioCiclosFormativos.setOnCloseRequest(e->confirmaCierreCiclos(escenarioCiclosFormativos,e));
            escenarioCiclosFormativos.setResizable(false);
            escenarioCiclosFormativos.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void confirmaCierreCiclos(Stage escenarioCiclosFormativos, WindowEvent e)
    {
        if (Dialogos.mostrarDialogoConfirmacion("Creador de Ciclos Formativos", "¿Realmente quieres salir sin guardar el Ciclo Formativo?"))
        {
            escenarioCiclosFormativos.close();
        }
        else
            e.consume();
    }

    @FXML
    void crearAsignaturas(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/CrearAsignaturas.fxml"));
            Parent raiz = null;

            raiz = fxmlLoader.load();

            Stage escenarioAsignaturas = new Stage();
            Scene escena = new Scene(raiz/*, 600, 600*/);
            escenarioAsignaturas.setTitle("Crear Asignaturas");
            escenarioAsignaturas.setScene(escena);
            escenarioAsignaturas.initModality(Modality.APPLICATION_MODAL);
            escenarioAsignaturas.setOnCloseRequest(e->confirmaCierreAsignaturas(escenarioAsignaturas,e));
            escenarioAsignaturas.setResizable(false);
            escenarioAsignaturas.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void confirmaCierreAsignaturas(Stage escenarioAsignaturas, WindowEvent e)
    {
        if (Dialogos.mostrarDialogoConfirmacion("Creador de Asignaturas", "¿Realmente quieres salir sin guardar la Asignatura?"))
        {
            escenarioAsignaturas.close();
        }
        else
            e.consume();
    }
    @FXML
    void crearMatriculas(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/CrearMatriculas.fxml"));
            Parent raiz = null;

            raiz = fxmlLoader.load();

            Stage escenarioMatriculas = new Stage();
            Scene escena = new Scene(raiz/*, 600, 600*/);
            escenarioMatriculas.setTitle("Crear Matriculas");
            escenarioMatriculas.setScene(escena);
            escenarioMatriculas.initModality(Modality.APPLICATION_MODAL);
            escenarioMatriculas.setOnCloseRequest(e->confirmaCierreMatriculas(escenarioMatriculas,e));
            escenarioMatriculas.setResizable(false);
            escenarioMatriculas.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void confirmaCierreMatriculas(Stage escenarioMatriculas, WindowEvent e)
    {
        if (Dialogos.mostrarDialogoConfirmacion("Creador de Matriculas", "¿Realmente quieres salir sin guardar la Matricula?"))
        {
            escenarioMatriculas.close();
        }
        else
            e.consume();
    }

    private void mostrarTablaAlumno() {
        try {
           //alumnosObservable.setAll(VistaGrafica.getInstancia().getControlador().getAlumnos());
            //tablCentralBusquedasAlumno.setItems(alumnosObservable);


            tablColum1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            tablColum2.setCellValueFactory(new PropertyValueFactory<>("telefono"));
            tablColum3.setCellValueFactory(new PropertyValueFactory<>("correo"));
            tablColum4.setCellValueFactory(new PropertyValueFactory<>("dni"));
            //tablColum5.setCellValueFactory(new PropertyValueFactory<Alumno, LocalDate>("fechaNacimiento"));
            //tablColum5.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));

            tablColum5.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getFechaNacimiento().format(DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA))));

            tablCentralBusquedasAlumno.setItems(alumnosObservable);
            coleccionAlumnos = new ArrayList<Alumno>(VistaGrafica.getInstancia().getControlador().getAlumnos());
            alumnosObservable.setAll(coleccionAlumnos);

           tablCentralBusquedasAlumno.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> muestraPersonaSeleccionada(newValue));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarTablaCiclosFormativos() {
        try {

            columCicloCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            columCicloFamPro.setCellValueFactory(new PropertyValueFactory<>("familiaProfesional"));
            columCicloGrado.setCellValueFactory(new PropertyValueFactory<>("correo"));
            columCicloNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            columCicloHoras.setCellValueFactory(new PropertyValueFactory<>("horas"));
            columCicloNombreGrado.setCellValueFactory(new PropertyValueFactory<>("nombreGrado"));
            columCicloAnios.setCellValueFactory(new PropertyValueFactory<>("numAniosGrado"));
            columCicloModalidad.setCellValueFactory(new PropertyValueFactory<>("modalidad"));
            columCicloNumEdiciones.setCellValueFactory(new PropertyValueFactory<>("numEdiciones"));


//            tablCentralBusquedasAlumno.setItems(alumnosObservable);
//            coleccionAlumnos = new ArrayList<Alumno>(VistaGrafica.getInstancia().getControlador().getAlumnos());
//            alumnosObservable.setAll(coleccionAlumnos);
//
//            tablCentralBusquedasAlumno.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> muestraPersonaSeleccionada(newValue));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void mostrarTablaAsignaturas() {
//        try {
//            tablColum1.setCellValueFactory(new PropertyValueFactory<Alumno, String>("nombre"));
//            tablColum2.setCellValueFactory(new PropertyValueFactory<Alumno, Integer>("telefono"));
//            tablColum3.setCellValueFactory(new PropertyValueFactory<Alumno, String>("correo"));
//            tablColum4.setCellValueFactory(new PropertyValueFactory<Alumno, String>("dni"));
//            tablColum4.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getFechaNacimiento().format(DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA))));
//
//            tablCentralBusquedasAlumno.setItems(alumnosObservable);
//            coleccionAlumnos = new ArrayList<Alumno>(VistaGrafica.getInstancia().getControlador().getAlumnos());
//            alumnosObservable.setAll(coleccionAlumnos);
//
//            tablCentralBusquedasAlumno.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> muestraPersonaSeleccionada(newValue));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    private void mostrarTablaMatriculas() {
//        try {
//            tablColum1.setCellValueFactory(new PropertyValueFactory<Alumno, String>("nombre"));
//            tablColum2.setCellValueFactory(new PropertyValueFactory<Alumno, Integer>("telefono"));
//            tablColum3.setCellValueFactory(new PropertyValueFactory<Alumno, String>("correo"));
//            tablColum4.setCellValueFactory(new PropertyValueFactory<Alumno, String>("dni"));
//            tablColum4.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getFechaNacimiento().format(DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA))));
//
//            tablCentralBusquedasAlumno.setItems(alumnosObservable);
//            coleccionAlumnos = new ArrayList<Alumno>(VistaGrafica.getInstancia().getControlador().getAlumnos());
//            alumnosObservable.setAll(coleccionAlumnos);
//
//            tablCentralBusquedasAlumno.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> muestraPersonaSeleccionada(newValue));
//        } catch (Exception e) {
//            e.printStackTrace();
        }


  private void muestraPersonaSeleccionada(Object newValue) {

  }

  private void mostrarFiltrado(){
        Alumno alumnoSeleccion = tablCentralBusquedasAlumno.getSelectionModel().getSelectedItem();


  }

}





