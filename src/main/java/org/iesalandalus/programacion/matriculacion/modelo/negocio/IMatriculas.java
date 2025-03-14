package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;

import javax.naming.OperationNotSupportedException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IMatriculas {
    public void comenzar();
    public void terminar();
    public ArrayList<Matricula> get() throws OperationNotSupportedException, SQLException;
    public int getTamano() throws SQLException;
    public void insertar(Matricula matricula) throws OperationNotSupportedException, SQLException;
    public Matricula buscar(Matricula matricula) throws OperationNotSupportedException, SQLException;
    public void borrar(Matricula matricula) throws OperationNotSupportedException, SQLException;
    public ArrayList<Matricula> get(Alumno alumno) throws OperationNotSupportedException, SQLException;
    public ArrayList<Matricula> get(String cursoAcademico) throws OperationNotSupportedException, SQLException;
    public ArrayList<Matricula> get(CicloFormativo cicloFormativo) throws OperationNotSupportedException, SQLException;
}
