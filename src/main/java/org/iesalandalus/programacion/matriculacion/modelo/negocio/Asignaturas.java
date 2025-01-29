package org.iesalandalus.programacion.matriculacion.modelo.negocio;


import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;

import javax.naming.OperationNotSupportedException;
import java.util.*;

public class Asignaturas {
    private ArrayList<Asignatura> coleccionAsignaturas;

    public Asignaturas(){

        coleccionAsignaturas = new ArrayList<>();
    }
    //copia profunda
    public Asignatura[] get() {
        return copiaProfundaAsignaturas();
    }

    private Asignatura[] copiaProfundaAsignaturas() {
        Asignatura[] copiaAsignaturas = new Asignatura[this.coleccionAsignaturas.size()];
        for (int i = 0; i<this.coleccionAsignaturas.size(); i++) {
            copiaAsignaturas[i] = new Asignatura(this.coleccionAsignaturas.get(i));
        }
        return copiaAsignaturas;
    }

    //Insertar Asignatura
    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        Objects.requireNonNull(asignatura, "ERROR: No se puede insertar una asignatura nula.");

        int indice = this.coleccionAsignaturas.indexOf(asignatura);
        if (indice==-1) {
            this.coleccionAsignaturas.add(new Asignatura(asignatura));
        }
        else {
            throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese código.");
        }
    }

    //buscar Asignatura
    public Asignatura buscar(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede buscar una asignatura nula.");
        }
        int indice = this.coleccionAsignaturas.indexOf(asignatura);
        if (indice==-1) {
            return null;
        } else {
            return new Asignatura(this.coleccionAsignaturas.get(indice));
        }
    }
    //borrar Asignatura
    public void borrar (Asignatura asignatura) throws OperationNotSupportedException {
        Objects.requireNonNull(asignatura,"ERROR: No se puede borrar una asignatura nula.");

        int indice = this.coleccionAsignaturas.indexOf(asignatura);
        if (indice==-1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna asignatura como la indicada.");
        }

    }

    public int getTamano() {
        return this.coleccionAsignaturas.size();
    }

}
