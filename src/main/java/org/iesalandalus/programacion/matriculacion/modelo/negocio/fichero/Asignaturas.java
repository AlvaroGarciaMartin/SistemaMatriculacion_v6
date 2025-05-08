package org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero;


import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAsignaturas;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Objects;

public class Asignaturas implements IAsignaturas {
    private ArrayList<Asignatura> coleccionAsignaturas;

    public Asignaturas(){

        coleccionAsignaturas = new ArrayList<>();
    }

    @Override
    public void comenzar() {

    }

    @Override
    public void terminar() {

    }

    //copia profunda
    public ArrayList<Asignatura> get() {
        return copiaProfundaAsignaturas();
    }

    private ArrayList<Asignatura> copiaProfundaAsignaturas() {
        ArrayList<Asignatura> copiaAsignaturas = new ArrayList<>();
        for (Asignatura asignatura : coleccionAsignaturas) {
            copiaAsignaturas.add(new Asignatura(asignatura));
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
        this.coleccionAsignaturas.remove(indice);
    }

    public int getTamano() {
        return this.coleccionAsignaturas.size();
    }

}
