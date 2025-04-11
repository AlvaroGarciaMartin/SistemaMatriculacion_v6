package org.iesalandalus.programacion.matriculacion.vista.grafica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.iesalandalus.programacion.matriculacion.vista.Vista;
import org.iesalandalus.programacion.matriculacion.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.matriculacion.vista.grafica.utilidades.Dialogos;

public class LanzadoraVentanaPrincipal extends Application {
    
    public static void comenzar() {
        launch();
    }

    @Override
    public void start(Stage escenarioPrincipal) throws Exception {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/VentanaPrincipal.fxml"));
            Parent raiz = fxmlLoader.load();

            Scene escena = new Scene(raiz/*, 600, 600*/);
            escenarioPrincipal.setTitle("Sistema de Matriculacion 24-25");
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.setOnCloseRequest(e->confirmarSalida(escenarioPrincipal,e));
            escenarioPrincipal.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void confirmarSalida( Stage escenarioPrincipal,WindowEvent e) {
        if (Dialogos.mostrarDialogoConfirmacion("Ventana Principal", "¿Realmente quieres salir de la aplicación?"))
        {
            VistaGrafica.getInstancia().terminar();
            escenarioPrincipal.close();
        }
        else
            e.consume();
    }

}
