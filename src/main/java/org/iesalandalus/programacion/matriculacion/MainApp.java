package org.iesalandalus.programacion.matriculacion;
import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.vista.*;


public class MainApp {
    public static void main(String[] args) {


        //crear modelo
        Modelo modelo = procesarArgumentosFuenteDatos(args);
        //crear vista
        Vista vista = new Vista();
        //crear controlador
        Controlador controlador = new Controlador(modelo, vista);
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

}




