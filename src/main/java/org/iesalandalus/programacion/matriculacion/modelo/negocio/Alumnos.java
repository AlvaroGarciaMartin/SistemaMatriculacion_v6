package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;

import javax.naming.OperationNotSupportedException;


public class Alumnos {

    private int capacidad;
    private int tamano=0;
    private Alumno[] coleccionAlumnos;




    public Alumnos(int capacidad) {
        if (!(capacidad > 0)) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        coleccionAlumnos = new Alumno[capacidad];
    }



    public Alumno[] get() {
        return copiaProfundaAlumnos();
    }

    private Alumno[] copiaProfundaAlumnos() {

        Alumno[] copiaAlumnos = new Alumno[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaAlumnos[i] = new Alumno(coleccionAlumnos[i]);
        }
        return copiaAlumnos;
    }

    private int buscarIndice(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: 1.No se puede buscar un alumno nulo.");
        }

        int indice = 0;
        boolean alumnoEncontrado = false;
        while (!tamanoSuperado(indice) && !alumnoEncontrado) {
            if (get()[indice].equals(alumno)) {
                alumnoEncontrado = true;
            } else {
                indice++;
            }
        }
        return indice;
    }
    //Insertar Alumno
    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
        }

        int indice = buscarIndice(alumno);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más alumnos.");
        }

        if (tamanoSuperado(indice)) {
            coleccionAlumnos[indice] = new Alumno(alumno);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
        }
    }
    private boolean tamanoSuperado(int indice) {
        return indice >= getTamano();
    }

    private boolean capacidadSuperada(int indice) {
        return indice >= getCapacidad();
    }
    //buscar alumno
    public static Alumno buscar(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: 2.No se puede buscar un alumno nulo.");
        }
        int indice = buscarIndice(alumno);
        if (tamanoSuperado(indice)) {
            return null;
        } else {
            return new Alumno(get()[indice]);
        }
    }
    //borrar alumno
    public void borrar (Alumno alumno) throws OperationNotSupportedException {
        // Objects.requireNonNull(alumno,"ERROR: No se puede borrar un alumno nulo.");
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
        }
        int indice = buscarIndice(alumno);
        if (tamanoSuperado(indice)){
            throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
        }
        else{
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }
    //desplazar posicion a la izquierda
    private void desplazarUnaPosicionHaciaIzquierda (int indice){
        coleccionAlumnos[indice]= null;
        int i;
        for (i=indice; !tamanoSuperado(i);i++){
            if (i<getCapacidad()-1) {
                coleccionAlumnos[i] = coleccionAlumnos [i+1];
            }
        }

        tamano--;
    }


    public int getCapacidad() {
        return capacidad;
    }



    public int getTamano() {

        return tamano;
    }



}
