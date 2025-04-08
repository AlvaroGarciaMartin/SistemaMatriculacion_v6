package org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql;


import com.mysql.cj.x.protobuf.MysqlxPrepare;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAsignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Curso;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.EspecialidadProfesorado;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.GradoE;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAsignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;

import javax.naming.OperationNotSupportedException;
import java.sql.*;
import java.util.*;


public class Asignaturas implements IAsignaturas {

    private Connection conexion;
    private static Asignaturas instancia= null;

    public Asignaturas(){
        comenzar();


    }


    public static Asignaturas getInstancia() {
        if (instancia ==null) {
            instancia = new Asignaturas();
        }
        return instancia;
    }

    public Curso getCurso(String curso) {
        return Curso.valueOf(curso.toUpperCase());
    }

    public EspecialidadProfesorado getEspecialidadProfesorado(String especialidad) {
        return EspecialidadProfesorado.valueOf(especialidad.toUpperCase());
    }

    //copia profunda
    public ArrayList<Asignatura> get() throws SQLException {
        //return copiaProfundaAsignaturas();
        ArrayList<Asignatura> copiaAsignaturas = new ArrayList<>();
        String consulta = """
                SELECT a.codigo
                 , a.nombre
                 , a.horasAnuales
                 , a.curso
                 , a.horasDesdoble
                 , a.especialidadProfesorado
                 , a.codigoCicloFormativo
                 FROM asignatura a
                 ORDER BY a.nombre
                """;

        Statement sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(consulta);

        while (resultado.next()) {
            CicloFormativo cicloFormativo = CiclosFormativos.getInstancia().buscar(new CicloFormativo(resultado.getInt("codigoCicloFormativo"), "ficticio", new GradoE("gradoe", 1, 1), "ficiticio",1));
            Asignatura a = new Asignatura(
                    resultado.getString("codigo"),
                    resultado.getString("nombre"),
                    resultado.getInt("horasAnuales"),
                    getCurso(resultado.getString("curso")),
                    resultado.getInt("horasDesdoble"),
                    getEspecialidadProfesorado(resultado.getString("especialidadProfesorado")),
                    cicloFormativo);
            copiaAsignaturas.add(a);

        }

        return copiaAsignaturas;
    }

    /*private ArrayList<Asignatura> copiaProfundaAsignaturas() {
        ArrayList<Asignatura> copiaAsignaturas = new ArrayList<>();
        for (Asignatura asignatura : coleccionAsignaturas) {
            copiaAsignaturas.add(new Asignatura(asignatura));
        }
        return copiaAsignaturas;
    }*/

    //Insertar Asignatura
    public void insertar(Asignatura asignatura) throws OperationNotSupportedException, SQLException {
        Objects.requireNonNull(asignatura, "ERROR: No se puede insertar una asignatura nula.");
        if(buscar(asignatura)!= null){
            throw new OperationNotSupportedException("ERROR: ya existe una asignatura con ese codigo");
        }
        String consulta = """
                INSERT INTO asignatura
                (codigo,
                nombre,
                horasAnuales,
                curso,
                horasDesdoble,
                especialidadProfesorado,
                codigoCicloFormativo)
                Values
                (?, ?, ?, ?, ?, ?, ?)
                """;
        PreparedStatement pstmt = conexion.prepareStatement(consulta);
        pstmt.setString(1, asignatura.getCodigo());
        pstmt.setString(2, asignatura.getNombre());
        pstmt.setInt(3, asignatura.getHorasAnuales());
        pstmt.setString(4, asignatura.getCurso().name().toLowerCase());
        pstmt.setInt(5, asignatura.getHorasDesdoble());
        pstmt.setString(6, asignatura.getEspecialidadProfesorado().name().toLowerCase());
        pstmt.setInt(7, asignatura.getCicloFormativo().getCodigo());
        pstmt.executeUpdate();

        /*int indice = this.coleccionAsignaturas.indexOf(asignatura);
        if (indice==-1) {
            this.coleccionAsignaturas.add(new Asignatura(asignatura));
        }
        else {
            throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese código.");
        }*/
    }

    //buscar Asignatura
    public Asignatura buscar(Asignatura asignatura) throws SQLException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede buscar una asignatura nula.");
        }
        /*
        int indice = this.coleccionAsignaturas.indexOf(asignatura);
        if (indice==-1) {
            return null;
        } else {
            return new Asignatura(this.coleccionAsignaturas.get(indice));
        }*/
        String consulta = """
                SELECT a.codigo
                , a.nombre
                , a.horasAnuales
                , a.curso
                , a.horasDesdoble
                , a.especialidadProfesorado
                , a.codigoCicloFormativo
                FROM asignatura a
                where a.codigo = ?
                """;
        PreparedStatement pstmt = conexion.prepareStatement(consulta);
        pstmt.setString(1, asignatura.getCodigo());
        ResultSet resultado = pstmt.executeQuery();
        if (resultado.next()){
            CicloFormativo cicloFormativo = CiclosFormativos.getInstancia().buscar(new CicloFormativo(resultado.getInt("codigoCicloFormativo"), "ficticio", new GradoE("gradoe", 1, 1), "ficticio", 1));
            return new Asignatura(
                    resultado.getString("codigo"),
                    resultado.getString("nombre"),
                    resultado.getInt("horasAnuales"),
                    getCurso(resultado.getString("curso")),
                    resultado.getInt("horasDesdoble"),
                    getEspecialidadProfesorado(resultado.getString("especialidadProfesorado")),
                    cicloFormativo);
        }
        return null;
    }
    //borrar Asignatura
    public void borrar (Asignatura asignatura) throws OperationNotSupportedException, SQLException {
        //Objects.requireNonNull(asignatura,"ERROR: No se puede borrar una asignatura nula.");
        if (buscar(asignatura)==null){
            throw new OperationNotSupportedException("ERROR: No existe ninguna asignatura como la indicada");
        }
        String consulta = """
                DELETE FROM asignatura
                WHERE codigo = ?
                """;

        PreparedStatement pstmt = conexion.prepareStatement(consulta);
        pstmt.setString(1,asignatura.getCodigo());
        pstmt.executeUpdate();
        //pstmt.executeQuery();

        /*int indice = this.coleccionAsignaturas.indexOf(asignatura);
        if (indice==-1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna asignatura como la indicada.");
        }
        this.coleccionAsignaturas.remove(indice);*/

    }

    public int getTamano() throws SQLException {
        String query = """
	    		SELECT COUNT(codigo) 
	    		FROM cicloformativo
	    		""";
        Statement stmt = conexion.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs.getInt(1);
    }

    @Override
    public void comenzar() {
        conexion = MySQL.establecerConexion();
    }

    @Override
    public void terminar() {
        MySQL.cerrarConexion();
    }

}
