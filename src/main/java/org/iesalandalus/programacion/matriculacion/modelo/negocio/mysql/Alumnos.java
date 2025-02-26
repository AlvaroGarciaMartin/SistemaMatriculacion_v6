package org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAlumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;

import javax.naming.OperationNotSupportedException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;


public class Alumnos implements IAlumnos {


    private Connection conexion;
    private static Alumnos instancia= null;





    public Alumnos() {
        comenzar();
    }
    public static Alumnos getInstancia() {
        if (instancia ==null) {
            instancia = new Alumnos();
        }
        return instancia;
    }

    @Override
    public void comenzar() {
        conexion= MySQL.establecerConexion();
    }

    @Override
    public void terminar() {
        MySQL.cerrarConexion();
    }

    public ArrayList<Alumno> get() throws SQLException {
        ArrayList<Alumno> auxiliar = new ArrayList<>();
        String consulta = """
                SELECT nombre
                 , telefono
                 , correo
                 , dni
                 , fechaNacimiento 
                 FROM alumnos""";

        Statement sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(consulta);

        while (resultado.next()) {
            Alumno a = new Alumno(
                    resultado.getString("nombre"),
                    resultado.getString("telefono"),
                    resultado.getString("correo"),
                    resultado.getString("dni"),
                    resultado.getDate("fechaNacimiento").toLocalDate()
            );
            auxiliar.add(a);
        }
        //return copiaProfundaAlumnos();

        return auxiliar;
    }

    /*private ArrayList<Alumno> copiaProfundaAlumnos() {

        //Alumno[] copiaAlumnos = new Alumno[capacidad];
        ArrayList<Alumno> copiaAlumnos = new ArrayList<>();
        for (Alumno alumno:coleccionAlumnos) {
            copiaAlumnos.add(new Alumno(alumno));
        }
        return copiaAlumnos;
    }*/


    //Insertar Alumno
    public void insertar(Alumno alumno) throws OperationNotSupportedException, SQLException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
        }
if (buscar(alumno) != null) {
    throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
} else {
    String consulta = """
            INSERT INTO alumnos (nombre
            ,telefono
            ,correo
            ,dni
            ,fechaNacimiento)
            VALUES (?,?,?,?,?)""";
    PreparedStatement sentencia = conexion.prepareStatement(consulta);
    sentencia.setString(1, alumno.getNombre());
    sentencia.setString(2, alumno.getTelefono());
    sentencia.setString(3, alumno.getCorreo());
    sentencia.setString(4, alumno.getDni());
    sentencia.setDate(5, Date.valueOf(alumno.getFechaNacimiento()));
    sentencia.executeUpdate();
}
        /*int indice = this.coleccionAlumnos.indexOf(alumno);

        if (indice==-1) {
            this.coleccionAlumnos.add(new Alumno(alumno));
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
        }*/
    }
    //Buscar Alumno
    public Alumno buscar(Alumno alumno) throws SQLException {
       Objects.requireNonNull(alumno, "ERROR: No se puede buscar un alumno nulo.");
        /*int indice = this.coleccionAlumnos.indexOf(alumno);
        if (indice==-1) {
            return null;
        }
        return new Alumno(this.coleccionAlumnos.get(indice));*/
        String consulta = """
                SELECT nombre
                 , telefono
                 , correo
                 , dni
                 , fechaNacimiento 
                 FROM alumnos
                 WHERE dni = ?
                 """;
        PreparedStatement sentencia = conexion.prepareStatement(consulta);
        sentencia.setString(1, alumno.getDni());
        ResultSet resultado = sentencia.executeQuery();

        if (!resultado.next()) return null;

        if (resultado.next()) {
            return new Alumno(
                    resultado.getString("nombre"),
                    resultado.getString("telefono"),
                    resultado.getString("correo"),
                    resultado.getString("dni"),
                    resultado.getDate("fechaNacimiento").toLocalDate()
            );

        }
        return null;
    }





    //borrar alumno
    public void borrar (Alumno alumno) throws OperationNotSupportedException, SQLException {
        // Objects.requireNonNull(alumno,"ERROR: No se puede borrar un alumno nulo.");
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
        }
        if (buscar(alumno) == null) {
            throw new OperationNotSupportedException("ERROR: No existe ningun alumno como el indicado.");
        }
        String consulta = """
                DELETE FROM alumnos
                WHERE dni = ?
                """;
        PreparedStatement sentencia = conexion.prepareStatement(consulta);
        sentencia.setString(1, alumno.getDni());
        sentencia.executeUpdate();

        /*int indice = this.coleccionAlumnos.indexOf(alumno);
        if (indice==-1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
        }

            this.coleccionAlumnos.remove(indice);*/

    }




    public int getTamano() throws SQLException {
String query = """
        SELECT count(1) AS cont
        FROM alumnos""";

Statement sentencia = conexion.createStatement();
ResultSet resultado = sentencia.executeQuery(query);
return resultado.getInt("cont");

    }



}
