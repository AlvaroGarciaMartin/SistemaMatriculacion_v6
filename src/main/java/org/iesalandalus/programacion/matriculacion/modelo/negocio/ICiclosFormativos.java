package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ICiclosFormativos {
    public void comenzar();
    public void terminar();
    public ArrayList<CicloFormativo> get() throws SQLException;
    public int getTamano() throws SQLException;
    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException, SQLException;
    public CicloFormativo buscar(CicloFormativo cicloFormativo) throws SQLException;
    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException, SQLException;
}
