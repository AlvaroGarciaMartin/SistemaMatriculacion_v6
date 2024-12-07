package org.iesalandalus.programacion.matriculacion.negocio;
import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;
import java.util.*;


public class CiclosFormativos {
    private int capacidad;
    private int tamano;
    private CicloFormativo[] coleccionCiclosFormativos;

    public CiclosFormativos(int capacidad) {
        if (!(capacidad > 0)) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        setCapacidad(capacidad);
        setTamano(tamano);
        coleccionCiclosFormativos = new CicloFormativo[capacidad];
    }

    //copia profunda
    public CicloFormativo[] get() {
        return copiaProfundaCiclosFormativos();
    }

    private CicloFormativo[] copiaProfundaCiclosFormativos() {
        CicloFormativo[] copiaCiclosFormativos = new CicloFormativo[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaCiclosFormativos[i] = new CicloFormativo(coleccionCiclosFormativos[i]);
        }
        return copiaCiclosFormativos;
    }
    //Buscar indice
    private int buscarIndice(CicloFormativo cicloFormativo) {

        int indice = 0;
        boolean cicloFormativoEncontrado = false;
        while (!tamanoSuperado(indice) && !cicloFormativoEncontrado) {
            if (get()[indice].equals(cicloFormativo)) {
                cicloFormativoEncontrado = true;
            } else {
                indice++;
            }
        }
        return indice;
    }
    //Insertar Ciclo
    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        Objects.requireNonNull(cicloFormativo, "ERROR: No se puede insertar un alumno nulo.");

        int indice = buscarIndice(cicloFormativo);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más alumnos.");
        }

        if (tamanoSuperado(indice)) {
            coleccionCiclosFormativos[indice] = new CicloFormativo(cicloFormativo);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe un Ciclo Formativo con ese codigo.");
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
    //buscar ciclo
    public CicloFormativo buscar(CicloFormativo cicloFormativo) {

        int indice = buscarIndice(cicloFormativo);
        if (tamanoSuperado(indice)) {
            return null;
        } else {
            return new CicloFormativo(get()[indice]);
        }
    }
    //borrar ciclo
    public void borrar (CicloFormativo cicloFormativo) {
        Objects.requireNonNull(cicloFormativo,"ERROR: No se puede borrar un Ciclo Formativo nulo.");

        int indice = buscarIndice(cicloFormativo);
        if (tamanoSuperado(indice)){
            throw new IllegalArgumentException("ERROR: No existe ningún Ciclo Formativo como el indicado.");
        }
        else{
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }
    //desplazar posicion a la izquierda
    private void desplazarUnaPosicionHaciaIzquierda (int indice){
        int i;
        for (i=indice; !tamanoSuperado(i);i++){
            coleccionCiclosFormativos[i] = coleccionCiclosFormativos [i+1];
        }
        coleccionCiclosFormativos[i]= null;
        tamano--;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public CicloFormativo[] getColeccionCiclosFormativos() {
        return coleccionCiclosFormativos;
    }

    public void setColeccionCiclosFormativos(CicloFormativo[] coleccionCiclosFormativos) {
        this.coleccionCiclosFormativos = coleccionCiclosFormativos;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }
}
