package org.iesalandalus.programacion.matriculacion.vista.grafica.controladores;

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.iesalandalus.programacion.matriculacion.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.matriculacion.vista.grafica.utilidades.Dialogos;

import java.io.IOException;

public class ControladorVentanaPrincipal {

    @FXML
    private BorderPane bdVentanaPrincipal;

    @FXML
    private ImageView btnBusqueda;

    @FXML
    private Button btnCrearAlumnos;

    @FXML
    private Button btnCrearAsignaturas;

    @FXML
    private Button btnCrearCiclos;

    @FXML
    private Button btnCrearMatriculas;

    @FXML
    private ComboBox<?> cbSelectorBusqueda;

    @FXML
    private HBox hbMenuBusqueda;

    @FXML
    private TableView<?> tablCentralBusquedas;

    @FXML
    private TableColumn<?, ?> tablColum1;

    @FXML
    private TableColumn<?, ?> tablColum2;

    @FXML
    private TableColumn<?, ?> tablColum3;

    @FXML
    private TableColumn<?, ?> tablColum4;

    @FXML
    private TableColumn<?, ?> tablColum5;

    @FXML
    private TableView<?> tablFiltro;

    @FXML
    private TableColumn<?, ?> tablFiltroColum1;

    @FXML
    private TableColumn<?, ?> tablFiltroColum2;

    @FXML
    private TableColumn<?, ?> tablFiltroColum3;

    @FXML
    private TableColumn<?, ?> tablFiltroColum4;

    @FXML
    private ToolBar tbBotonesPrincipales;

    @FXML
    private TextField tfBusqueda;

    @FXML
    private VBox vbBotonesPrincipales;

    @FXML
    private VBox vbDesplegables;

    @FXML
    private void initialize() {

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
            throw new RuntimeException(e);
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

            Stage escenarioCiclosFormativos = new Stage();
            Scene escena = new Scene(raiz/*, 600, 600*/);
            escenarioCiclosFormativos.setTitle("Crear Ciclos Formativos");
            escenarioCiclosFormativos.setScene(escena);
            escenarioCiclosFormativos.initModality(Modality.APPLICATION_MODAL);
            escenarioCiclosFormativos.setOnCloseRequest(e->confirmaCierreCiclos(escenarioCiclosFormativos,e));
            escenarioCiclosFormativos.setResizable(false);
            escenarioCiclosFormativos.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void confirmaCierreAsignaturas(Stage escenarioCiclosFormativos, WindowEvent e)
    {
        if (Dialogos.mostrarDialogoConfirmacion("Creador de Asignaturas", "¿Realmente quieres salir sin guardar la Asignatura?"))
        {
            escenarioCiclosFormativos.close();
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

            Stage escenarioCiclosFormativos = new Stage();
            Scene escena = new Scene(raiz/*, 600, 600*/);
            escenarioCiclosFormativos.setTitle("Crear Matriculas");
            escenarioCiclosFormativos.setScene(escena);
            escenarioCiclosFormativos.initModality(Modality.APPLICATION_MODAL);
            escenarioCiclosFormativos.setOnCloseRequest(e->confirmaCierreCiclos(escenarioCiclosFormativos,e));
            escenarioCiclosFormativos.setResizable(false);
            escenarioCiclosFormativos.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void confirmaCierreMatriculas(Stage escenarioCiclosFormativos, WindowEvent e)
    {
        if (Dialogos.mostrarDialogoConfirmacion("Creador de Matriculas", "¿Realmente quieres salir sin guardar la Matricula?"))
        {
            escenarioCiclosFormativos.close();
        }
        else
            e.consume();
    }




}


