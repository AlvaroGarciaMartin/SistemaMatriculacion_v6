package org.iesalandalus.programacion.matriculacion.modelo;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Matriculas;

import javax.naming.OperationNotSupportedException;

public class Modelo {
   public final static int CAPACIDAD=10;
   private Alumnos alumnos;
   private Matriculas matriculas;
   private Asignaturas asignaturas;
   private CiclosFormativos ciclosFormativos;


   public void comenzar() {
      this.alumnos = new Alumnos(CAPACIDAD);
      this.asignaturas = new Asignaturas(CAPACIDAD);
      this.ciclosFormativos = new CiclosFormativos(CAPACIDAD);
      this.matriculas = new Matriculas(CAPACIDAD);
   }
   public void terminar() {
      System.out.println("Aplicacion terminada.");
   }
   //ALUMNOS
   public void insertar(Alumno alumno) throws OperationNotSupportedException {
      this.alumnos.insertar(alumno);
   };
   public Alumno buscar(Alumno alumno){
      return this.alumnos.buscar(alumno);
       //this.alumnos.buscar(alumno);
      //alumnos.buscar(alumno);
       //return alumno;
   };
   public void borrar(Alumno alumno) throws OperationNotSupportedException {
      this.alumnos.borrar(alumno);
   };

   public Alumno[] getAlumnos() {
      return alumnos.get();
      //return new Alumno[0];
   }
   //ASIGNATURAS
   public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
      this.asignaturas.insertar(asignatura);
   };
   public Asignatura buscar(Asignatura asignatura){
      this.asignaturas.buscar(asignatura);
      return asignatura;
   };
   public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
      this.asignaturas.borrar(asignatura);
   };

   public Asignatura[] getAsignaturas() {
      return asignaturas.get();
      //return new Asignatura[0];
   }
   //CICLOS FORMATIVOS
   public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
      this.ciclosFormativos.insertar(cicloFormativo);
   };
   public CicloFormativo buscar(CicloFormativo cicloFormativo){
      this.ciclosFormativos.buscar(cicloFormativo);
      return cicloFormativo;
   };
   public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
      this.ciclosFormativos.borrar(cicloFormativo);
   };

   public CicloFormativo[] getCiclosFormativos() {
      return ciclosFormativos.get();
      //return new CicloFormativo[0];
   }
   //MATRICULAS
   public void insertar(Matricula matricula) throws OperationNotSupportedException {
      this.matriculas.insertar(matricula);
   };
   public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
      this.matriculas.buscar(matricula);
      return matricula;
   };
   public void borrar(Matricula matricula) throws OperationNotSupportedException {
      this.matriculas.borrar(matricula);
   };

   public Matricula[] getMatriculas() throws OperationNotSupportedException {
      return matriculas.get();
      //return new Matricula[0];
   }
   public Matricula[] getMatriculas(Alumno alumno) throws OperationNotSupportedException {
      //this.matriculas.get(alumno);
      return matriculas.get(alumno);
   }
   public Matricula[] getMatriculas(CicloFormativo cicloFormativo) {
      //this.matriculas.get(cicloFormativo);
      return matriculas.get(cicloFormativo);
   }
   public Matricula[] getMatriculas(String cursoAcademico) {
      //this.matriculas.get(cursoAcademico);
      return matriculas.get(cursoAcademico);
   }
}
