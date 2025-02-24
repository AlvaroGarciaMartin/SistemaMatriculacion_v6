package org.iesalandalus.programacion.matriculacion;
import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.vista.*;


public class MainApp {
    public static void main(String[] args) {
        //crear modelo
        Modelo modelo = new Modelo();
        //crear vista
        Vista vista = new Vista();
        //crear controlador
        Controlador controlador = new Controlador(modelo, vista);
        //comienza el programa
        controlador.comenzar();
        //termina el programa
        controlador.terminar();
    }
}



