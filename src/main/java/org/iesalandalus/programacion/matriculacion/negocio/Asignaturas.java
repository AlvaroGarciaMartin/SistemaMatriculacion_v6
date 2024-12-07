package org.iesalandalus.programacion.matriculacion.negocio;


import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;
import java.util.*;

public class Asignaturas {
    private int capacidad;
    private int tamano;
    private Asignatura[] coleccionAsignaturas;

    public Asignaturas(int capacidad){
        if (!(capacidad > 0)) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        setCapacidad(capacidad);
        setTamano(tamano);
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
            throw new OperationNotSupportedException("ERROR: Ya existe una Asignatura con ese codigo.");
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
    public Asignatura buscar(Asignatura asignatura) {

        int indice = buscarIndice(asignatura);
        if (tamanoSuperado(indice)) {
            return null;
        } else {
            return new Asignatura(get()[indice]);
        }
    }
    //borrar Asignatura
    public void borrar (Asignatura asignatura) {
        Objects.requireNonNull(asignatura,"ERROR: No se puede borrar una Asignatura nulo.");

        int indice = buscarIndice(asignatura);
        if (tamanoSuperado(indice)){
            throw new IllegalArgumentException("ERROR: No existe ningúna Asignatura como la indicada.");
        }
        else{
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }
    //desplazar posicion a la izquierda
    private void desplazarUnaPosicionHaciaIzquierda (int indice){
        int i;
        for (i=indice; !tamanoSuperado(i);i++){
            coleccionAsignaturas[i] = coleccionAsignaturas [i+1];
        }
        coleccionAsignaturas[i]= null;
        tamano--;
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

    public Asignatura[] getColeccionAsignaturas() {
        return coleccionAsignaturas;
    }

    public void setColeccionAsignaturas(Asignatura[] coleccionAsignaturas) {
        this.coleccionAsignaturas = coleccionAsignaturas;
    }
}
