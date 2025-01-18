package org.iesalandalus.programacion.matriculacion.modelo.negocio;


import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;

import javax.naming.OperationNotSupportedException;
import java.util.*;

public class Asignaturas {
    private int capacidad;
    private int tamano=0;
    private Asignatura[] coleccionAsignaturas;

    public Asignaturas(int capacidad){
        if (!(capacidad > 0)) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        coleccionAsignaturas = new Asignatura[capacidad];
    }
    //copia profunda
    public Asignatura[] get() {
        return copiaProfundaAsignaturas();
    }

    private Asignatura[] copiaProfundaAsignaturas() {
        Asignatura[] copiaAsignaturas = new Asignatura[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaAsignaturas[i] = new Asignatura(coleccionAsignaturas[i]);
        }
        return copiaAsignaturas;
    }
    //Buscar indice
    private int buscarIndice(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede buscar una Asignatura nula.");
        }

        int indice = 0;
        boolean asignaturaEncontrado = false;
        while (!tamanoSuperado(indice) && !asignaturaEncontrado) {
            if (get()[indice].equals(asignatura)) {
                asignaturaEncontrado = true;
            } else {
                indice++;
            }
        }
        return indice;
    }
    //Insertar Asignatura
    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        Objects.requireNonNull(asignatura, "ERROR: No se puede insertar una asignatura nula.");

        int indice = buscarIndice(asignatura);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más asignaturas.");
        }

        if (tamanoSuperado(indice)) {
            coleccionAsignaturas[indice] = new Asignatura(asignatura);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese código.");
        }
    }
    //Tamaño superado
    private boolean tamanoSuperado(int indice) {
        return indice >= getTamano();
    }
    //capacidad superada
    private boolean capacidadSuperada(int indice) {
        return indice >= getCapacidad();
    }
    //buscar Asignatura
    public static Asignatura buscar(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede buscar una asignatura nula.");
        }
        int indice = buscarIndice(asignatura);
        if (tamanoSuperado(indice)) {
            return null;
        } else {
            return new Asignatura(get()[indice]);
        }
    }
    //borrar Asignatura
    public void borrar (Asignatura asignatura) throws OperationNotSupportedException {
        Objects.requireNonNull(asignatura,"ERROR: No se puede borrar una asignatura nula.");

        int indice = buscarIndice(asignatura);
        if (tamanoSuperado(indice)){
            throw new OperationNotSupportedException("ERROR: No existe ninguna asignatura como la indicada.");
        }
        else{
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }
    //desplazar posicion a la izquierda
    private void desplazarUnaPosicionHaciaIzquierda (int indice){
        coleccionAsignaturas[indice] = null;
        int i;
        for (i=indice; !tamanoSuperado(i);i++){
            if (i<getCapacidad()-1) {
                coleccionAsignaturas[i] = coleccionAsignaturas [i+1];
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
