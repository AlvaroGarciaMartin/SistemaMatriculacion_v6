package org.iesalandalus.programacion.matriculacion.modelo.negocio;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;

import javax.naming.OperationNotSupportedException;
import java.sql.SQLException;
import java.util.ArrayList;
public interface IAlumnos {
    public void comenzar();
    public void terminar();
    public ArrayList<Alumno> get() throws SQLException;
    public int getTamano();
    public void insertar(Alumno alumno) throws OperationNotSupportedException;
    public Alumno buscar(Alumno alumno);
    public void borrar(Alumno alumno) throws OperationNotSupportedException;
}
