package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Matriculas {
private int capacidad;
private int tamano;
private Matricula[] coleccionMatriculas;

    public Matriculas(int capacidad) {
        if (!(capacidad > 0)) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        coleccionMatriculas = new Matricula[capacidad];
    }
    //copia profunda
    public Matricula[] get() {
        return copiaProfundaMatriculas();
    }

    private Matricula[] copiaProfundaMatriculas() {
        Matricula[] copiaMatriculas = new Matricula[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaMatriculas[i] = new Matricula(coleccionMatriculas[i]);
        }
        return copiaMatriculas;
    }
    //Buscar indice
    private int buscarIndice(Matricula matricula) {

        int indice = 0;
        boolean matriculaEncontrado = false;
        while (!tamanoSuperado(indice) && !matriculaEncontrado) {
            if (get()[indice].equals(matricula)) {
                matriculaEncontrado = true;
            } else {
                indice++;
            }
        }
        return indice;
    }
    //Insertar Matricula
    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        Objects.requireNonNull(matricula, "ERROR: No se puede insertar una Matricula nula.");

        int indice = buscarIndice(matricula);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más Matriculas.");
        }

        if (tamanoSuperado(indice)) {
            coleccionMatriculas[indice] = new Matricula(matricula);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una Matricula con ese codigo.");
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
    //buscar Matricula
    public Matricula buscar(Matricula matricula) {

        int indice = buscarIndice(matricula);
        if (tamanoSuperado(indice)) {
            return null;
        } else {
            return new Matricula(get()[indice]);
        }
    }
    //borrar Matricula
    public void borrar (Matricula matricula) {
        Objects.requireNonNull(matricula,"ERROR: No se puede borrar una Matricula nulo.");

        int indice = buscarIndice(matricula);
        if (tamanoSuperado(indice)){
            throw new IllegalArgumentException("ERROR: No existe ningúna Matricula como la indicada.");
        }
        else{
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }
    //desplazar posicion a la izquierda
    private void desplazarUnaPosicionHaciaIzquierda (int indice){
        int i;
        for (i=indice; !tamanoSuperado(i);i++){
            coleccionMatriculas[i] = coleccionMatriculas [i+1];
        }
        coleccionMatriculas[i]= null;
        tamano--;
    }

    public int getCapacidad() {
        return capacidad;
    }


    public int getTamano() {
        return tamano;
    }

}
