package org.iesalandalus.programacion.matriculacion.modelo;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Matriculas;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Modelo {

   private Alumnos alumnos;
   private Matriculas matriculas;
   private Asignaturas asignaturas;
   private CiclosFormativos ciclosFormativos;


   public void comenzar() {
      this.alumnos = new Alumnos();
      this.asignaturas = new Asignaturas();
      this.ciclosFormativos = new CiclosFormativos();
      this.matriculas = new Matriculas();
   }
   public void terminar() {
      System.out.println("Aplicacion terminada.");
   }
   //ALUMNOS
   public void insertar(Alumno alumno) throws OperationNotSupportedException {
      this.alumnos.insertar(alumno);
   }
   public Alumno buscar(Alumno alumno){
      return this.alumnos.buscar(alumno);

   }
   public void borrar(Alumno alumno) throws OperationNotSupportedException {
      this.alumnos.borrar(alumno);
   }

   public ArrayList<Alumno> getAlumnos() {
      return alumnos.get();
      //return new Alumno[0];
   }
   //ASIGNATURAS
   public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
      this.asignaturas.insertar(asignatura);
   }
   public Asignatura buscar(Asignatura asignatura){
      this.asignaturas.buscar(asignatura);
      return asignatura;
   }
   public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
      this.asignaturas.borrar(asignatura);
   }

   public ArrayList<Asignatura> getAsignaturas() {
      return asignaturas.get();
      //return new Asignatura[0];
   }
   //CICLOS FORMATIVOS
   public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
      this.ciclosFormativos.insertar(cicloFormativo);
   }
   public CicloFormativo buscar(CicloFormativo cicloFormativo){
     return this.ciclosFormativos.buscar(cicloFormativo);

   }
   public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
      this.ciclosFormativos.borrar(cicloFormativo);
   }

   public ArrayList<CicloFormativo> getCiclosFormativos() {
      return ciclosFormativos.get();
      //return new CicloFormativo[0];
   }
   //MATRICULAS
   public void insertar(Matricula matricula) throws OperationNotSupportedException {
      this.matriculas.insertar(matricula);
   }
   public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
      return this.matriculas.buscar(matricula);

   }
   public void borrar(Matricula matricula) throws OperationNotSupportedException {
      this.matriculas.borrar(matricula);
   }

   public ArrayList<Matricula> getMatriculas() throws OperationNotSupportedException {
      return matriculas.get();
      //return new Matricula[0];
   }
   public ArrayList<Matricula> getMatriculas(Alumno alumno) throws OperationNotSupportedException {
      //this.matriculas.get(alumno);
      return matriculas.get(alumno);
   }
   public ArrayList<Matricula> getMatriculas(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
      //this.matriculas.get(cicloFormativo);
      return matriculas.get(cicloFormativo);
   }
   public ArrayList<Matricula> getMatriculas(String cursoAcademico) throws OperationNotSupportedException {
      //this.matriculas.get(cursoAcademico);
      return matriculas.get(cursoAcademico);
   }
}
