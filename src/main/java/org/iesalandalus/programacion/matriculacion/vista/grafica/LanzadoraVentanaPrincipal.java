package org.iesalandalus.programacion.matriculacion.vista.grafica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.iesalandalus.programacion.matriculacion.vista.grafica.recursos.LocalizadorRecursos;

public class LanzadoraVentanaPrincipal extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/VentanaPrincipal.fxml"));
            Parent raiz = fxmlLoader.load();

            Scene escena = new Scene(raiz, 600, 600);
            stage.setTitle("Sistema de Matriculacion 24-25");
            stage.setScene(escena);
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void confirmarSalida(WindowEvent e, Stage escenarioPrincipal) {

    }
}
