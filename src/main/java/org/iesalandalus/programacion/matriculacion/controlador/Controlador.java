package org.iesalandalus.programacion.matriculacion.controlador;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.vista.*;

import javax.naming.OperationNotSupportedException;

public class Controlador {
    private static Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        if (modelo == null) {
            throw new NullPointerException("ERROR: El modelo no puede ser nulo");
        }
        if (vista == null) {
            throw new NullPointerException("ERROR: La vista no puede ser nula");
        }
        this.modelo = modelo;
        this.vista = vista;
        //Instanciar vista, llamar metodo setControlador
        this.vista.setControlador(this);
    }
    public void comenzar() {
        modelo.comenzar();
        vista.comenzar();
    }
    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }
    //ALUMNO
    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        modelo.insertar(alumno);
    }
    public Alumno buscar(Alumno alumno){
        return modelo.buscar(alumno);
        //modelo.buscar(alumno);
        //return alumno;
    }
    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        modelo.borrar(alumno);
    }
    public Alumno [] getAlumnos(){
        return modelo.getAlumnos();
    }
    //ASIGNATURA
    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        modelo.insertar(asignatura);
    }
    public Asignatura buscar(Asignatura asignatura){
        return modelo.buscar(asignatura);
        //modelo.buscar(asignatura);
        //return asignatura;
    }
    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        modelo.borrar(asignatura);
    }
    public static Asignatura[] getAsignaturas(){
        return modelo.getAsignaturas();
    }
    //CICLO FORMATIVO
    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        modelo.insertar(cicloFormativo);
    }
    public CicloFormativo buscar(CicloFormativo cicloFormativo){
        return modelo.buscar(cicloFormativo);
        //modelo.buscar(cicloFormativo);
        //return cicloFormativo;
    }
    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        modelo.borrar(cicloFormativo);
    }
    public CicloFormativo[] getCicloFormativos(){
        return modelo.getCiclosFormativos();
    }
    //MATRICULA
    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        modelo.insertar(matricula);
    }
    public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
        return modelo.buscar(matricula);
        //modelo.buscar(matricula);
        //return matricula; 
    }
    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        modelo.borrar(matricula);
    }
    public Matricula[] getMatriculas() throws OperationNotSupportedException {
        return modelo.getMatriculas();
    }

    public Matricula[] getMatriculas(Alumno alumno) throws OperationNotSupportedException {
      return modelo.getMatriculas(alumno);
    }

    public Matricula[] getMatriculas(CicloFormativo cicloFormativo){
       return modelo.getMatriculas(cicloFormativo);
    }
    public Matricula[] getMatriculas(String cursoAcademico) throws OperationNotSupportedException {
        return modelo.getMatriculas(cursoAcademico);
    }

}
