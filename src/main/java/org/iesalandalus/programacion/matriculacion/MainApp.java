package org.iesalandalus.programacion.matriculacion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.vista.texto.VistaTexto;
import org.iesalandalus.programacion.matriculacion.vista.grafica.recursos.LocalizadorRecursos;


public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);

        //crear modelo
        Modelo modelo = procesarArgumentosFuenteDatos(args);
        //crear vista
        VistaTexto vistaTexto = new VistaTexto();
        //crear controlador
        Controlador controlador = new Controlador(modelo, vistaTexto);
        //comienza el programa
        controlador.comenzar();
        //termina el programa
        controlador.terminar();
    }
    private static Modelo procesarArgumentosFuenteDatos(String[] args){
        if (args.length == 0) {
            return new Modelo(FactoriaFuenteDatos.MEMORIA);
        }else{
            if (args[0].equalsIgnoreCase("-fdmysql")) {
                System.out.println("Fuente de datos: MySQL");
                return new Modelo(FactoriaFuenteDatos.MYSQL);

            }else if (args[0].equalsIgnoreCase("-fdmemoria")) {
                System.out.println("Fuente de datos: MEMORIA");
                return new Modelo(FactoriaFuenteDatos.MEMORIA);
            } else{
                System.out.println("Fuente de datos no reconocida, Usando memoria por defecto.");
                return new Modelo(FactoriaFuenteDatos.MEMORIA);
            }

        }

    }

    @Override
    public void start(Stage escenarioPrincipal ) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/VentanaPrincipal.fxml"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}




