package org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.ICiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Grado;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.GradoD;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.GradoE;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Modalidad;


import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;



public class CiclosFormativos implements ICiclosFormativos {
    private Connection conexion;

    private static CiclosFormativos instancia = null;





    public CiclosFormativos() {
        comenzar();
    }

    public static CiclosFormativos getInstancia(){
        if (instancia == null) {
            instancia = new CiclosFormativos();
        }
        return instancia;
    }
    public Grado getGrado(String tipoGrado, String nombreGrado, int numAniosGrado, String modalidad, int numEdiciones) {
        Grado grado = null;

        if (tipoGrado.equals("gradod")) {
            Modalidad modalidadGrado = Modalidad.valueOf(modalidad.toUpperCase());
            grado = new GradoD(nombreGrado, numAniosGrado, modalidadGrado);
        }else {
            grado = new GradoE(nombreGrado, numAniosGrado, numEdiciones);
        }
        return grado;
    }


    //copia profunda
    public ArrayList<CicloFormativo> get() throws SQLException {
        //return copiaProfundaCiclosFormativos();
        ArrayList<CicloFormativo> copiaCiclos = new ArrayList<>();
        String consulta = """
                SELECT c.codigo
                , c.familiaProfesional
                , c.grado
                , c.nombre
                , c.horas
                , c.nombreGrado
                , c.numAniosGrado
                , c.modalidad
                , c.numEdiciones
                FROM cicloFormativo c
                ORDER BY c.nombre
                """;

        Statement sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(consulta);
        while(resultado.next()) {
            Grado grado = getGrado(resultado.getString(3), resultado.getString(6), resultado.getInt(7), resultado.getString(8), resultado.getInt(9));
            CicloFormativo cicloFormativo = new CicloFormativo(resultado.getInt(1), resultado.getString(2), grado, resultado.getString(4), resultado.getInt(5));
            copiaCiclos.add(cicloFormativo);
        }
        return copiaCiclos;
    }

    

    /*private ArrayList<CicloFormativo> copiaProfundaCiclosFormativos() {
        ArrayList<CicloFormativo> copiaCiclosFormativos = new ArrayList<>();
        for (CicloFormativo cicloFormativo : coleccionCiclosFormativos) {
            copiaCiclosFormativos.add(new CicloFormativo(cicloFormativo));
        }
        return copiaCiclosFormativos;
    }*/

    //Insertar Ciclo
    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException, SQLException {
        Objects.requireNonNull(cicloFormativo, "ERROR: No se puede insertar un ciclo formativo nulo.");

        if (buscar(cicloFormativo)!=null){
            throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese código.");
        }

        String consulta = """
                INSERT INTO cicloFormativo
                (codigo,
                familiaProfesional,
                grado,
                nombre,
                horas,
                nombreGrado,
                numAniosGrado,
                modalidad,
                numEdiciones)
                VALUES
                 (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        PreparedStatement pstmt = conexion.prepareStatement(consulta);
        pstmt.setInt(1, cicloFormativo.getCodigo());
        pstmt.setString(2, cicloFormativo.getFamiliaProfesional());
        pstmt.setString(3, cicloFormativo.getGrado().getClass().getSimpleName().toLowerCase());
        pstmt.setString(4, cicloFormativo.getNombre());
        pstmt.setInt(5, cicloFormativo.getHoras());
        pstmt.setString(6, cicloFormativo.getGrado().getNombre().toLowerCase());
        pstmt.setInt(7, cicloFormativo.getGrado().getNumAnios());
        if(cicloFormativo.getGrado() instanceof GradoD) {
            pstmt.setString(8, ((GradoD)cicloFormativo.getGrado()).getModalidad().toString());
            pstmt.setNull(9, java.sql.Types.INTEGER);
        }else {
            pstmt.setNull(8, java.sql.Types.VARCHAR);
            pstmt.setInt(9, ((GradoE) cicloFormativo.getGrado()).getNumEdiciones());
        }
        pstmt.executeUpdate();

        /*int indice =this.coleccionCiclosFormativos.indexOf(cicloFormativo);
        if (indice==-1) {
            coleccionCiclosFormativos.add(new CicloFormativo(cicloFormativo));
            return ;
        }
            throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese código.");*/

    }


    //buscar ciclo
    public CicloFormativo buscar(CicloFormativo cicloFormativo) throws SQLException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede buscar un Ciclo Formativo nulo.");
        }
        String consulta = """
                SELECT codigo
                , familiaProfesional
                , grado
                , nombre
                , horas
                , nombreGrado
                , numAniosGrado
                , modalidad
                , numEdiciones
                FROM cicloFormativo
                WHERE codigo = ?
                """;

        PreparedStatement pstmt = conexion.prepareStatement(consulta);
        pstmt.setInt(1, cicloFormativo.getCodigo());
        ResultSet resultado = pstmt.executeQuery();
        if (resultado.next()) {
            Grado grado = null;
            if (resultado.getString(3).equals("gradod")) {
                grado = new GradoD(resultado.getString(6), resultado.getInt(7), Modalidad.valueOf(resultado.getString(8).toUpperCase()));
            } else {
                grado = new GradoE(resultado.getString(6), resultado.getInt(7), resultado.getInt(9));
            }
            return new CicloFormativo(resultado.getInt(1), resultado.getString(2), grado, resultado.getString(4), resultado.getInt(5));
        }
        return null;

        /*int indice = this.coleccionCiclosFormativos.indexOf(cicloFormativo);
        if (indice==-1) {
            return null;
        } else {
            return new CicloFormativo(this.coleccionCiclosFormativos.get(indice));
        }*/
    }

    //borrar ciclo
    public void borrar (CicloFormativo cicloFormativo) throws OperationNotSupportedException, SQLException {
        //Objects.requireNonNull(cicloFormativo,"ERROR: No se puede borrar un ciclo formativo nulo.");
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede borrar un ciclo formativo nulo.");
        }
        if (buscar(cicloFormativo)==null) {
            throw new OperationNotSupportedException("ERROR: No existe ningun ciclo formativo como el indicado.");
        }

        String consulta = """
                DELETE FROM cicloFormativo
                WHERE codigo = ?
                """;
        PreparedStatement pstmt = conexion.prepareStatement(consulta);
        pstmt.setInt(1, cicloFormativo.getCodigo());
        pstmt.executeUpdate();

        /*int indice = this.coleccionCiclosFormativos.indexOf(cicloFormativo);
        if (indice==-1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún ciclo formativo como el indicado.");
        }
        this.coleccionCiclosFormativos.remove(indice);*/
    }





    public int getTamano() throws SQLException {
        //return this.coleccionCiclosFormativos.size();
        String consulta = """
                SELECT COUNT(1) as cont
                FROM cicloformativo
                """;
        Statement sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(consulta);
        return resultado.getInt(1);
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
