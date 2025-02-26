package org.iesalandalus.programacion.matriculacion.modelo;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.Alumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.Asignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.Matriculas;

import javax.naming.OperationNotSupportedException;
import java.sql.SQLException;
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

   }
   //ASIGNATURAS
   public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
      this.asignaturas.insertar(asignatura);
   }
   public Asignatura buscar(Asignatura asignatura){
      return this.asignaturas.buscar(asignatura);
      //return asignatura;
   }
   public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
      this.asignaturas.borrar(asignatura);
   }

   public ArrayList<Asignatura> getAsignaturas() {
      return asignaturas.get();
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
   }
   public ArrayList<Matricula> getMatriculas(Alumno alumno) throws OperationNotSupportedException {
      return matriculas.get(alumno);
   }
   public ArrayList<Matricula> getMatriculas(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
      return matriculas.get(cicloFormativo);
   }
   public ArrayList<Matricula> getMatriculas(String cursoAcademico) throws OperationNotSupportedException {
      return matriculas.get(cursoAcademico);
   }
}
