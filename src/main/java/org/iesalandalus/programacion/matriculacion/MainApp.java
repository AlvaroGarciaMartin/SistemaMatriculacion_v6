package org.iesalandalus.programacion.matriculacion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.vista.FactoriaVista;
import org.iesalandalus.programacion.matriculacion.vista.Vista;
import org.iesalandalus.programacion.matriculacion.vista.grafica.LanzadoraVentanaPrincipal;
import org.iesalandalus.programacion.matriculacion.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.matriculacion.vista.texto.VistaTexto;
import org.iesalandalus.programacion.matriculacion.vista.grafica.recursos.LocalizadorRecursos;


public class MainApp {
    public static void main(String[] args) {
        //LanzadoraVentanaPrincipal.comenzar();


        //crear modelo
        Modelo modelo = procesarArgumentosFuenteDatos(args);
        //crear vista
        Vista vista = procesarArgumentosVista(args);
        //crear controlador
        Controlador controlador = new Controlador(modelo, vista);
        //comienza el programa
        controlador.comenzar();
        //termina el programa
        controlador.terminar();
    }
    private static Modelo procesarArgumentosFuenteDatos(String[] args){


        for (String arg : args) {
            if (arg.equalsIgnoreCase("-fdmysql")) {
                return new Modelo(FactoriaFuenteDatos.MYSQL);
            } else if (arg.equalsIgnoreCase("-fdmemoria")) {
                return new Modelo(FactoriaFuenteDatos.MEMORIA);
            }else if(arg.equalsIgnoreCase("-fdmfichero")) {
                return new Modelo(FactoriaFuenteDatos.FICHEROS);
            }
        }
        System.out.println("Fuente de datos no reconocida, Usando memoria por defecto.");
        return new Modelo(FactoriaFuenteDatos.MEMORIA);

        /*if (args.length == 0) {
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

        }*/

    }
    private static Vista procesarArgumentosVista(String[] args) {
        for (String arg : args) {
            if (arg.equalsIgnoreCase("-vgrafica")) {
                System.out.println("Vista: GRAFICA");
                return FactoriaVista.GRAFICA.crear();
            } else if (arg.equalsIgnoreCase("-vtexto")) {
                System.out.println("Vista: TEXTO");
                return FactoriaVista.TEXTO.crear();
            }
        }
        System.out.println("Vista no reconocida, Usando Texto por defecto.");
        return FactoriaVista.TEXTO.crear();

        /*if (args.length <= 1) {
            System.out.println("Vista por defecto: Texto");
            return new VistaTexto();
        } else {
            if (args[1].equalsIgnoreCase("-vtexto")) {
                System.out.println("Vista: TEXTO");
                return new VistaTexto();

            } else if (args[1].equalsIgnoreCase("-vgrafica")) {
                System.out.println("Vista: GRAFICA");
                return VistaGrafica.getInstancia();
            } else {
                System.out.println("Vista no reconocida, Usando Texto por defecto.");
                return new VistaTexto();
            }
        }*/
    }
}




