package org.iesalandalus.programacion.matriculacion.negocio;

public class Alumnos {

   private int capacidad;
   private int tamano=0;

   public Alumnos (int capacidad) {
       setCapacidad(capacidad);
       setTamaño(tamano);
   }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {

       this.capacidad = capacidad;
    }

    public int getTamaño() {

       return tamano;
    }

    public void setTamaño(int tamano) {
        this.tamano = tamano;
    }

}
