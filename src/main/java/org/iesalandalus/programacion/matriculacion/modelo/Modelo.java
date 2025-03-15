package org.iesalandalus.programacion.matriculacion.modelo;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.*;

import javax.naming.OperationNotSupportedException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Modelo {

   private IAlumnos alumnos;
   private IMatriculas matriculas;
   private IAsignaturas asignaturas;
   private ICiclosFormativos ciclosFormativos;


   public Modelo(FactoriaFuenteDatos factoriaFuenteDatos) {
       setFuenteDatos(factoriaFuenteDatos.crear());
   }
   private void setFuenteDatos(IFuenteDatos fuenteDatos) {
      this.fuenteDatos = fuenteDatos;
   }

   private IFuenteDatos fuenteDatos;


   public void comenzar() {
      this.alumnos = fuenteDatos.crearAlumnos();
      this.asignaturas = fuenteDatos.crearAsignaturas();
      this.ciclosFormativos = fuenteDatos.crearCiclosFormativos();
      this.matriculas = fuenteDatos.crearMatriculas();

   }
   public void terminar() {
      this.alumnos.terminar();
      this.asignaturas.terminar();
      this.ciclosFormativos.terminar();
      this.matriculas.terminar();
      System.out.println("Aplicacion terminada.");
   }
   //ALUMNOS
   public void insertar(Alumno alumno) throws OperationNotSupportedException, SQLException {
      this.alumnos.insertar(alumno);
   }
   public Alumno buscar(Alumno alumno) throws SQLException {
      return this.alumnos.buscar(alumno);

   }
   public void borrar(Alumno alumno) throws OperationNotSupportedException, SQLException {
      this.alumnos.borrar(alumno);
   }

   public ArrayList<Alumno> getAlumnos() throws SQLException {
      return alumnos.get();

   }
   //ASIGNATURAS
   public void insertar(Asignatura asignatura) throws OperationNotSupportedException, SQLException {
      this.asignaturas.insertar(asignatura);
   }
   public Asignatura buscar(Asignatura asignatura) throws SQLException {
      return this.asignaturas.buscar(asignatura);
      //return asignatura;
   }
   public void borrar(Asignatura asignatura) throws OperationNotSupportedException, SQLException {
      this.asignaturas.borrar(asignatura);
   }

   public ArrayList<Asignatura> getAsignaturas() throws SQLException {
      return asignaturas.get();
   }
   //CICLOS FORMATIVOS
   public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException, SQLException {
      this.ciclosFormativos.insertar(cicloFormativo);
   }
   public CicloFormativo buscar(CicloFormativo cicloFormativo) throws SQLException {
     return this.ciclosFormativos.buscar(cicloFormativo);

   }
   public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException, SQLException {
      this.ciclosFormativos.borrar(cicloFormativo);
   }

   public ArrayList<CicloFormativo> getCiclosFormativos() throws SQLException {
      return ciclosFormativos.get();

   }
   //MATRICULAS
   public void insertar(Matricula matricula) throws OperationNotSupportedException, SQLException {
      this.matriculas.insertar(matricula);
   }
   public Matricula buscar(Matricula matricula) throws OperationNotSupportedException, SQLException {
      return this.matriculas.buscar(matricula);

   }
   public void borrar(Matricula matricula) throws OperationNotSupportedException, SQLException {
      this.matriculas.borrar(matricula);
   }

   public ArrayList<Matricula> getMatriculas() throws OperationNotSupportedException, SQLException {
      return matriculas.get();
   }
   public ArrayList<Matricula> getMatriculas(Alumno alumno) throws OperationNotSupportedException, SQLException {
      return matriculas.get(alumno);
   }
   public ArrayList<Matricula> getMatriculas(CicloFormativo cicloFormativo) throws OperationNotSupportedException, SQLException {
      return matriculas.get(cicloFormativo);
   }
   public ArrayList<Matricula> getMatriculas(String cursoAcademico) throws OperationNotSupportedException, SQLException {
      return matriculas.get(cursoAcademico);
   }
}
