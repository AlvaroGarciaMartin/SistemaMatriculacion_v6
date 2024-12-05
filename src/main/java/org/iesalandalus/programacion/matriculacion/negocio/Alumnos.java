package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;

import java.util.*;


public class Alumnos {

   private int capacidad;
   private int tamano;
   //private Alumno[] coleccionAlumnos;
    private String [] Alumno;
    private String[] coleccionAlumnos;
   coleccionAlumnos = new String[capacidad];
   Alumno = new String[capacidad];




   Alumnos(int capacidad) {
       setCapacidad(capacidad);
       setTamano(tamano);
       //this.tamano = coleccionAlumnos.length;
   }

    public get(){

        //se necesita hacer un for que recorra coleccionAlumnos que se llame copiaProfundaAlumno
        String copiaProfundaAlumnos[] = new String[capacidad];
        for (int i = 0; i < capacidad; i++) {
            Alumno[i]= new alumnos(coleccionAlumnos[i]);// esto es una copia profunda
        }
       return copiaProfundaAlumnos[coleccionAlumnos];
    }




    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {

       this.capacidad = capacidad;
    }

    public int getTamano() {

       return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }


}
