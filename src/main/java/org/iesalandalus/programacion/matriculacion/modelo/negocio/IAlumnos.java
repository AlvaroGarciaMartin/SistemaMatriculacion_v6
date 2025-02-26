package org.iesalandalus.programacion.matriculacion.modelo.negocio;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;

import javax.naming.OperationNotSupportedException;
import java.sql.SQLException;
import java.util.ArrayList;
public interface IAlumnos {
    public void comenzar();
    public void terminar();
    public ArrayList<Alumno> get() throws SQLException;
    public int getTamano() throws SQLException;
    public void insertar(Alumno alumno) throws OperationNotSupportedException, SQLException;
    public Alumno buscar(Alumno alumno) throws SQLException;
    public void borrar(Alumno alumno) throws OperationNotSupportedException, SQLException;
}
