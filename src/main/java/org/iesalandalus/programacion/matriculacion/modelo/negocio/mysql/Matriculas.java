package org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Curso;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.EspecialidadProfesorado;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.GradoE;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IMatriculas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Objects;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Matriculas implements IMatriculas {


    private Connection conexion;

    private static Matriculas instancia = null;

    private ArrayList<Matricula> coleccionMatriculas;

    public Matriculas() {

        coleccionMatriculas = new ArrayList<>();
        comenzar();
    }

    public static Matriculas getInstancia() {
        if (instancia == null) {
            instancia = new Matriculas();
        }
        return instancia;
    }

    //copia profunda
    public ArrayList<Matricula> get() throws OperationNotSupportedException, SQLException {

        ArrayList<Matricula> copiaMatriculas = new ArrayList<>();
        String consulta = """
                SELECT m.idMatricula,
                m.cursoAcademico,
                m.fechaMatriculacion,
                m.fechaAnulacion,
                m.dni
                FROM matricula m
                LEFT JOIN alumnoa a ON m.dni = a.dni
                ORDER BY m.fechaMatriculacion DESC, a.nombre
                """;

        Statement sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(consulta);

        while (resultado.next()) {
            Alumno alumno = Alumnos.getInstancia().buscar(new Alumno("ficticio", resultado.getString("dni"), "ficticio@ficticio.es", "666555444", LocalDate.of(1999, 1, 2)));
            Matricula matricula = new Matricula(resultado.getInt("idMatricula"), resultado.getString("cursoAcademico"), resultado.getDate("fechaMatriculacion").toLocalDate(), alumno, getAsignaturasMatricula(resultado.getInt("idMatricula")));

            copiaMatriculas.add(matricula);
        }
        return copiaMatriculas;
        // return copiaProfundaMatriculas();
    }

    /*private ArrayList<Matricula> copiaProfundaMatriculas() throws OperationNotSupportedException, SQLException {

        for (Matricula matricula : coleccionMatriculas) {
            copiaMatriculas.add(new Matricula(matricula));
        }

        return copiaMatriculas;
    }*/

    private ArrayList<Asignatura> getAsignaturasMatricula(int idMatricula) throws SQLException {
        String consulta = """
                			SELECT a.codigo
                	, a.nombre
                	, a.horasAnuales
                	, a.curso
                	, a.horasDesdoble
                	, a.especialidadProfesorado
                	, a.codigoCiclo
                FROM asignaturasmatricula am
                LEFT JOIN asignatura a ON am.codigo = a.codigo
                WHERE am.idMatricula = ?
                """;
        PreparedStatement pstmt = conexion.prepareStatement(consulta);
        pstmt.setInt(1, idMatricula);
        ResultSet resultado = pstmt.executeQuery();
        ArrayList<Asignatura> asignaturas = new ArrayList<>();
        while (resultado.next()) {
            CicloFormativo cicloFormativo = CiclosFormativos.getInstancia().buscar(new CicloFormativo(
                    resultado.getInt("codigoCiclo"), "ficticio", new GradoE("gradoe", 1, 1), "ficticio", 1));
            Asignatura asignatura = new Asignatura(
                    resultado.getString("codigo"),
                    resultado.getString("nombre"),
                    resultado.getInt("horasAnuales"),
                    Curso.valueOf(resultado.getString("curso").toUpperCase()),
                    resultado.getInt("horasDesdoble"),
                    EspecialidadProfesorado.valueOf(resultado.getString("especialidadProfesorado").toUpperCase()),
                    cicloFormativo);
            asignaturas.add(asignatura);
        }
        return asignaturas;
    }

    //Insertar Matricula
    public void insertar(Matricula matricula) throws OperationNotSupportedException, SQLException {
        Objects.requireNonNull(matricula, "ERROR: No se puede insertar una matrícula nula.");

        String consulta = """
                INSERT INTO matricula
                (idMatricula
                , cursoAcademicoç
                , fechaMatriculacion
                , fechaAnulacion
                , dni)
                VALUES (?, ?, ?, ?, ?)
                """;
        PreparedStatement pstmt = conexion.prepareStatement(consulta);
        pstmt.setInt(1, matricula.getIdMatricula());
        pstmt.setString(2, matricula.getCursoAcademico());
        pstmt.setDate(3, java.sql.Date.valueOf(matricula.getFechaMatriculacion()));
        if (matricula.getFechaAnulacion() == null) {
            pstmt.setNull(4, java.sql.Types.DATE);
        } else {
            pstmt.setDate(4, java.sql.Date.valueOf(matricula.getFechaAnulacion()));
        }

        pstmt.setString(5, matricula.getAlumno().getDni());
        pstmt.executeUpdate();

       /* int indice = this.coleccionMatriculas.indexOf(matricula);

        if (indice == -1) {
            this.coleccionMatriculas.add(new Matricula(matricula));

        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una matrícula con ese identificador.");
        }*/
    }

    //insertarAsignaturasMatricula
    private void insertarAsignaturasMatricula(int idMatricula, ArrayList<Asignatura> coleccionAsigntauras) throws SQLException {
        String query = """
                			INSERT INTO asignaturasmatricula
                	(idMatricula
                    ,codigo)
                VALUES 
                	(?, ?)
                """;
        PreparedStatement pstmt = conexion.prepareStatement(query);
        for (Asignatura asignatura : coleccionAsigntauras) {
            pstmt.setInt(1, idMatricula);
            pstmt.setString(2, asignatura.getCodigo());
            pstmt.executeUpdate();
        }

    }

    //buscar Matricula
    public Matricula buscar(Matricula matricula) throws OperationNotSupportedException, SQLException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede buscar una Matricula nula.");
        }
        String consulta = """ 
                SELECT m.idMatricula
                , m.cursoAcademico
                , m.fechaMatriculacion
                , m.fechaAnulacion
                , m.dni
                FROM matricula m
                WHERE m.idMatricula = ?
                """;
        PreparedStatement pstmt = conexion.prepareStatement(consulta);
        pstmt.setInt(1, matricula.getIdMatricula());
        ResultSet resultado = pstmt.executeQuery();

        if (resultado.next()) {
            Alumno alumno = Alumnos.getInstancia().buscar(new Alumno("ficticio", resultado.getString("dni"), "ficticio@fake.com", "666554433", LocalDate.of(2000, 1, 1)));
            Matricula matriculaEncontrada = new Matricula(resultado.getInt("idMatricula"),
                    resultado.getString("cursoAcademico"),
                    resultado.getDate("fechaMatriculacion").toLocalDate(),
                    alumno,
                    getAsignaturasMatricula(resultado.getInt("idMatricula")));
            return matriculaEncontrada;
        }

        return null;



        /*int indice = this.coleccionMatriculas.indexOf(matricula);
        if (indice == -1) {
            return null;
        } else {
            return new Matricula(this.coleccionMatriculas.get(indice));
        }*/
    }

    //borrar Matricula
    public void borrar(Matricula matricula) throws OperationNotSupportedException, SQLException {
        Objects.requireNonNull(matricula, "ERROR: No se puede borrar una matrícula nula.");

        if (buscar(matricula) == null) {
            throw new OperationNotSupportedException("ERROR: no existe ninguna matricula como la indicada.");
        }
        String consulta = """
                DELETE FROM matricula
                WHERE idMatricula = ?
                """;
        PreparedStatement pstmt = conexion.prepareStatement(consulta);
        pstmt.setInt(1, matricula.getIdMatricula());
        pstmt.executeUpdate();

       /* int indice = this.coleccionMatriculas.indexOf(matricula);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula como la indicada.");
        }
        this.coleccionMatriculas.remove(indice);*/
    }


    public ArrayList<Matricula> get(Alumno alumno) throws OperationNotSupportedException, SQLException {
        ArrayList<Matricula> auxiliar = new ArrayList<>();
        String consulta = """
                SELECT m.idMatricula
                , m.cursoAcademico
                , m.fechaMatriculacion
                , m.fechaAnulacion
                , m.dni
                FROM matricula m
                LEFT JOIN alumno a ON m.dni = a.dni
                WHERE a.dni = ?
                ORDER BY m.fechaMatriculacion DESC, a.nombre
                """;

        PreparedStatement pstmt = conexion.prepareStatement(consulta);
        pstmt.setString(1, alumno.getDni());
        ResultSet resultado = pstmt.executeQuery();

        while (resultado.next()) {
            Matricula matricula = new Matricula(resultado.getInt("idMatricula"),
                    resultado.getString("cursoAcademico"),
                    resultado.getDate("fechaMatriculacion").toLocalDate(),
                    alumno,
                    getAsignaturasMatricula(resultado.getInt("idMatricula")));
            auxiliar.add(matricula);
        }
        return auxiliar;
    }


    public ArrayList<Matricula> get(String cursoAcademico) throws OperationNotSupportedException, SQLException {
        ArrayList<Matricula> copiaMatriculas = new ArrayList<>();

        String consulta = """
                SELECT m.idMatricula
                , m.cursoAcademico
                , m.fechaMatriculacion
                , m.fechaAnulacion
                , m.dni
                FROM matricula m
                LEFT JOIN alumno a ON m.dni = a.dni
                WHERE m.cursoAcademico = ?
                ORDER BY m.fechaMatriculacion DESC, a.nombre
                """;
        PreparedStatement pstmt = conexion.prepareStatement(consulta);
        pstmt.setString(1, cursoAcademico);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Alumno alumno = Alumnos.getInstancia().buscar(new Alumno("ficticio", rs.getString("dni"), "ficticio@fake.com", "666554433", LocalDate.of(2000, 1, 1)));
            Matricula matricula = new Matricula(rs.getInt("idMatricula"),
                    rs.getString("cursoAcademico"),
                    rs.getDate("fechaMatriculacion").toLocalDate(),
                    alumno,
                    getAsignaturasMatricula(rs.getInt("idMatricula")));
            copiaMatriculas.add(matricula);
        }
        return copiaMatriculas;
        /*for (Matricula matricula:coleccionMatriculas) {
            if (matricula != null && matricula.getCursoAcademico().equals(cursoAcademico)) {
                auxiliar.add(new Matricula(matricula));

            }
        }
        return auxiliar;
        /*int contador = 0;

        for (Matricula matricula : coleccionMatriculas) {
            if (matricula.getCursoAcademico().equals(cursoAcademico)) {
                contador++;
            }
        }

        Matricula[] coleccionMatriculasCurso = new Matricula[contador];

        int i = 0;

        for (Matricula matriculaCurso : coleccionMatriculas) {

            if (matriculaCurso.getCursoAcademico().equals(cursoAcademico)){
                coleccionMatriculasCurso[i] = matriculaCurso;
                i++;
            }
        }
        return coleccionMatriculasCurso;*/
    }


    public ArrayList<Matricula> get(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        ArrayList<Matricula> auxiliar = new ArrayList<>();
        for (Matricula matricula:coleccionMatriculas) {
            if (matricula != null ) {
                for (Asignatura asignatura : matricula.getColeccionAsignaturas()) {

                    if (asignatura != null && asignatura.getCicloFormativo().equals(cicloFormativo)) {
                        auxiliar.add(new Matricula(matricula));
                        break;
                    }

                }
            }
        }

        return auxiliar;

    }
    public int getTamano() throws SQLException {
        //return this.coleccionMatriculas.size();
        String consulta = """
                        SELECT COUNT(codigo)
                        FROM matricula
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
