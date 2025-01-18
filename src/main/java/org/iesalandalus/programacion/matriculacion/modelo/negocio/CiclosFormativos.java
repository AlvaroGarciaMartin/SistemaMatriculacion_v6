package org.iesalandalus.programacion.matriculacion.modelo.negocio;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;
import java.util.*;


public class CiclosFormativos {
    private int capacidad;
    private int tamano=0;
    private CicloFormativo[] coleccionCiclosFormativos;

    public CiclosFormativos(int capacidad) {
        if (!(capacidad > 0)) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
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
        if (cicloFormativo == null) {
            throw new IllegalArgumentException("ERROR: No se puede buscar un Ciclo Formativo nulo.");
        }

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
        Objects.requireNonNull(cicloFormativo, "ERROR: No se puede insertar un ciclo formativo nulo.");

        int indice = buscarIndice(cicloFormativo);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más ciclos formativos.");
        }

        if (tamanoSuperado(indice)) {
            coleccionCiclosFormativos[indice] = new CicloFormativo(cicloFormativo);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese código.");
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
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede buscar un Ciclo Formativo nulo.");
        }

        int indice = buscarIndice(cicloFormativo);
        if (tamanoSuperado(indice)) {
            return null;
        } else {
            return new CicloFormativo(get()[indice]);
        }
    }
    //borrar ciclo
    public void borrar (CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        //Objects.requireNonNull(cicloFormativo,"ERROR: No se puede borrar un ciclo formativo nulo.");
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede borrar un ciclo formativo nulo.");
        }
        int indice = buscarIndice(cicloFormativo);
        if (tamanoSuperado(indice)){
            throw new OperationNotSupportedException("ERROR: No existe ningún ciclo formativo como el indicado.");
        }
        else{
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }
    //desplazar posicion a la izquierda
    private void desplazarUnaPosicionHaciaIzquierda (int indice){
        coleccionCiclosFormativos[indice]= null;
        int i;
        for (i=indice; !tamanoSuperado(i);i++){
            if (i<getCapacidad()-1) {
                coleccionCiclosFormativos[i] = coleccionCiclosFormativos [i+1];
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
