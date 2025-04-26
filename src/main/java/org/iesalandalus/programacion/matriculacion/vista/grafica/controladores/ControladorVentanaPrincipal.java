package org.iesalandalus.programacion.matriculacion.vista.grafica.controladores;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.Alumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.Asignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.Matriculas;
import org.iesalandalus.programacion.matriculacion.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.matriculacion.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.matriculacion.vista.grafica.utilidades.Dialogos;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ControladorVentanaPrincipal {
    @FXML private Button btnBorrarAlumno;
    @FXML private Button btnBorrarAsignatura;
    @FXML private Button btnBorrarCiclo;
    @FXML private Button btnBorrarMatricula;
    @FXML private Tab tabAlumnos;
    @FXML private Tab tabCiclos;
    @FXML private Tab tabMatriculas;
    @FXML private Tab tabAsignaturas;
    @FXML private BorderPane bdVentanaPrincipal;
    @FXML private ImageView btnBusqueda;
    @FXML private Button btnCrearAlumnos;
    @FXML private Button btnCrearAsignaturas;
    @FXML private Button btnCrearCiclos;
    @FXML private Button btnCrearMatriculas;
    @FXML private ComboBox<String> cbSelectorBusqueda;
    @FXML private HBox hbMenuBusqueda;
    @FXML private TableView<Alumno> tablCentralBusquedasAlumno;
    @FXML private TableView<CicloFormativo> tablCiclosFormativos;
    @FXML private TableView<Asignatura> tablAsignaturasMostrar;
    @FXML private TableView<Matricula> tablMatriculasMostrar;
    // variables tabla alumno
    @FXML private TableColumn<Alumno, String> tablColum1;
    @FXML private TableColumn<Alumno, Integer> tablColum2;
    @FXML private TableColumn<Alumno, String> tablColum3;
    @FXML private TableColumn<Alumno, String> tablColum4;
    @FXML private TableColumn<Alumno, LocalDate> tablColum5;
    // fin variables tabla alumno

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
//    @FXML private TableColumn<CicloFormativo, Integer> columCicloAnios;
    @FXML private TableColumn<CicloFormativo, Integer> columCicloCodigo;
    @FXML private TableColumn<CicloFormativo, String> columCicloFamPro;
    @FXML private TableColumn<CicloFormativo, Integer> columCicloHoras;
    @FXML private TableColumn<CicloFormativo, String> columCicloNombre;
//    @FXML private TableColumn<CicloFormativo, String> columCicloNombreGrado;
    @FXML private TableColumn<CicloFormativo, String> columCicloGrado;
//    @FXML private TableColumn<CicloFormativo, String> columCicloModalidad;
//    @FXML private TableColumn<CicloFormativo, Integer> columCicloNumEdiciones;
// fin variables ciclo formativo
// variables tabla asignatura
    @FXML private TableColumn<Asignatura, String> columCodigoAsignatura;
    @FXML private TableColumn<Asignatura, String> ColumNombreAsignatura;
    @FXML private TableColumn<Asignatura, Integer> columHorasAnuales;
    @FXML private TableColumn<Asignatura, Curso> columCurso;
    @FXML private TableColumn<Asignatura, Integer> columHorasDesdoble;
    @FXML private TableColumn<Asignatura, EspecialidadProfesorado> columEspecialidad;
    @FXML private TableColumn<Asignatura, String> columCodigoCiclo;
 // fin variables tabla asignatura
// variables tabla matricula
    @FXML private TableColumn<Matricula, Integer> columIdMatricula;
    @FXML private TableColumn<Matricula, String> columCursoAcademico;
    @FXML private TableColumn<Matricula, LocalDate> columFechaMatriculacion;
    @FXML private TableColumn<Matricula, LocalDate> columFechaAnulacion;
    @FXML private TableColumn<Matricula, Alumno> columDniMatricula;
// fin variables tabla matricula

    @FXML private ToolBar tbBotonesPrincipales;
    @FXML private TextField tfBusqueda;
    @FXML private VBox vbBotonesPrincipales;
    @FXML private VBox vbDesplegables;

//observable alumnos
    private List<Alumno> coleccionAlumnos = new ArrayList<>();
    private ObservableList<Alumno> alumnosObservable = FXCollections.observableArrayList();
//observable ciclo formativo
    private List<CicloFormativo> coleccionCicloFormativo = new ArrayList<>();
    private ObservableList<CicloFormativo> cicloFormativoObservable = FXCollections.observableArrayList();
//observable asignatura
private final ObservableList<Asignatura> asignaturasObservable = FXCollections.observableArrayList();
private List<Asignatura> coleccionAsignaturas = new ArrayList<>();
//observable matricula
    private List<Matricula> coleccionMatriculas = new ArrayList<>();
    private ObservableList<Matricula> matriculasObservable = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        try {
            mostrarTablaAlumno();
            mostrarTablaCiclosFormativos();
            mostrarTablaAsignaturas();
            mostrarTablaMatriculas();

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

            mostrarTablaAlumno();
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

            mostrarTablaCiclosFormativos();
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

            mostrarTablaAsignaturas();
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

            mostrarTablaMatriculas();
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
            tablColum5.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));

            // Configurar la fecha en formato español
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            tablColum5.setCellFactory(column -> new TableCell<Alumno, LocalDate>() {
                @Override
                protected void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    setText(empty || date == null ? "" : date.format(formatter));
                }
            });

            //tablColum5.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getFechaNacimiento().format(DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA))));
            // Cargar los datos en la tabla Alumnos
            tablCentralBusquedasAlumno.setItems(FXCollections.observableArrayList(obtenerListaAlumnos()));
            tablCentralBusquedasAlumno.setItems(alumnosObservable);
            coleccionAlumnos = new ArrayList<>(VistaGrafica.getInstancia().getControlador().getAlumnos());
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
            columCicloGrado.setCellValueFactory(new PropertyValueFactory<>("grado"));
            columCicloNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            columCicloHoras.setCellValueFactory(new PropertyValueFactory<>("horas"));
//            columCicloNombreGrado.setCellValueFactory(new PropertyValueFactory<>("nombreGrado"));
//            columCicloAnios.setCellValueFactory(new PropertyValueFactory<>("numAniosGrado"));
//            columCicloModalidad.setCellValueFactory(new PropertyValueFactory<>("modalidad"));
//            columCicloNumEdiciones.setCellValueFactory(new PropertyValueFactory<>("numEdiciones"));


            tablCiclosFormativos.setItems(cicloFormativoObservable);
            coleccionCicloFormativo = new ArrayList<>(VistaGrafica.getInstancia().getControlador().getCicloFormativos());
            cicloFormativoObservable.setAll(coleccionCicloFormativo);
//
//            tablCiclosFormativos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> muestraPersonaSeleccionada(newValue));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void mostrarTablaAsignaturas() {
        try {
            columCodigoAsignatura.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            ColumNombreAsignatura.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            columHorasAnuales.setCellValueFactory(new PropertyValueFactory<>("horasAnuales"));
            columCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
            columHorasDesdoble.setCellValueFactory(new PropertyValueFactory<>("horasDesdoble"));
            columEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidadProfesorado"));
            columCodigoCiclo.setCellValueFactory(new PropertyValueFactory<>("cicloFormativo"));

            tablAsignaturasMostrar.setItems(asignaturasObservable);
            coleccionAsignaturas = new ArrayList<>(VistaGrafica.getInstancia().getControlador().getAsignaturas());
            asignaturasObservable.setAll(coleccionAsignaturas);

           // tablCentralBusquedasAlumno.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> muestraPersonaSeleccionada(newValue));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void mostrarTablaMatriculas() {
        try {
            columIdMatricula.setCellValueFactory(new PropertyValueFactory<>("idMatricula"));
            columCursoAcademico.setCellValueFactory(new PropertyValueFactory<>("cursoAcademico"));
            columFechaMatriculacion.setCellValueFactory(new PropertyValueFactory<>("fechaMatriculacion"));
            //columFechaAnulacion.setCellValueFactory(matricula -> new SimpleStringProperty(matricula.getValue().getFechaMatriculacion().format(DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA))));
            columFechaAnulacion.setCellValueFactory(new PropertyValueFactory<>("fechaAnulacion"));
            columDniMatricula.setCellValueFactory(new PropertyValueFactory<>("alumno"));


            tablMatriculasMostrar.setItems(matriculasObservable);
            coleccionMatriculas = new ArrayList<>(VistaGrafica.getInstancia().getControlador().getMatriculas());
            matriculasObservable.setAll(coleccionMatriculas);

//           tablCentralBusquedasAlumno.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> muestraPersonaSeleccionada(newValue));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  private void muestraPersonaSeleccionada(Object newValue) {

  }

  private void mostrarFiltrado(){
        Alumno alumnoSeleccion = tablCentralBusquedasAlumno.getSelectionModel().getSelectedItem();


  }
    // Métodos para obtener listas de objetos desde la base de datos
    List<Alumno> obtenerListaAlumnos() {
        Alumnos alumnosBD = Alumnos.getInstancia();
        try {
            return alumnosBD.get(); // Obtiene la lista de alumnos desde la base de datos
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of(); // Devuelve una lista vacía en caso de error
        }
    }

    // METODOS BORRAR Y ANULAR
    public void borrarAlumno() throws OperationNotSupportedException, SQLException {
        Alumno alumnoSeleccion = tablCentralBusquedasAlumno.getSelectionModel().getSelectedItem();
        if (alumnoSeleccion != null) {
            if (Dialogos.mostrarDialogoConfirmacion("Borrar Alumno", "¿Realmente quieres borrar el Alumno?")) {
                Alumnos.getInstancia().borrar(alumnoSeleccion);
                mostrarTablaAlumno();
            }
        }
    }

    public void borrarCiclo() throws OperationNotSupportedException, SQLException {
        CicloFormativo cicloFormativoSeleccion = tablCiclosFormativos.getSelectionModel().getSelectedItem();
        if (cicloFormativoSeleccion != null) {
            if (Dialogos.mostrarDialogoConfirmacion("Borrar Ciclo", "¿Realmente quieres borrar el Ciclo?")) {
                CiclosFormativos.getInstancia().borrar(cicloFormativoSeleccion);
                mostrarTablaCiclosFormativos();
            }
        }
    }
    public void borrarAsignatura() throws OperationNotSupportedException, SQLException {
        Asignatura asignaturaSeleccion = tablAsignaturasMostrar.getSelectionModel().getSelectedItem();
        if (asignaturaSeleccion != null) {
            if (Dialogos.mostrarDialogoConfirmacion("Borrar Asignatura", "¿Realmente quieres borrar la Asignatura?")) {
                Asignaturas.getInstancia().borrar(asignaturaSeleccion);
                mostrarTablaAsignaturas();
            }
        }
    }
    public void anularMatricula() throws OperationNotSupportedException, SQLException {
        Matricula matriculaSeleccion = tablMatriculasMostrar.getSelectionModel().getSelectedItem();
        if (matriculaSeleccion != null) {
           if (Dialogos.mostrarDialogoConfirmacion("Anular Matricula", "¿Realmente quieres anular la matricula?")) {
               matriculaSeleccion.setFechaAnulacion(LocalDate.now());
               Matriculas.getInstancia().borrar(matriculaSeleccion);
               mostrarTablaMatriculas();
           }
        }
    }
}





