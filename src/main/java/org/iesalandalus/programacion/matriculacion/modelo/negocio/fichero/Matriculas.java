package org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IMatriculas;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Objects;

public class Matriculas implements IMatriculas {
private ArrayList<Matricula> coleccionMatriculas;

    public Matriculas() {

        coleccionMatriculas = new ArrayList<>();
    }

    @Override
    public void comenzar() {

    }

    @Override
    public void terminar() {

    }

    //copia profunda
    public ArrayList<Matricula> get() throws OperationNotSupportedException {
        return copiaProfundaMatriculas();
    }

    private ArrayList<Matricula> copiaProfundaMatriculas() throws OperationNotSupportedException {
        ArrayList<Matricula> copiaMatriculas = new ArrayList<>();
        for (Matricula matricula : coleccionMatriculas) {
            copiaMatriculas.add(new Matricula(matricula));
        }
        return copiaMatriculas;
    }
    //Insertar Matricula
    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        Objects.requireNonNull(matricula, "ERROR: No se puede insertar una matrícula nula.");

        int indice = this.coleccionMatriculas.indexOf(matricula);

        if (indice == -1) {
            this.coleccionMatriculas.add(new Matricula(matricula));

        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una matrícula con ese identificador.");
        }
    }

    //buscar Matricula
    public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede buscar una Matricula nula.");
        }

        int indice = this.coleccionMatriculas.indexOf(matricula);
        if (indice == -1) {
            return null;
        } else {
            return new Matricula(this.coleccionMatriculas.get(indice));
        }
    }
    //borrar Matricula
    public void borrar (Matricula matricula) throws OperationNotSupportedException {
        Objects.requireNonNull(matricula,"ERROR: No se puede borrar una matrícula nula.");

        int indice = this.coleccionMatriculas.indexOf(matricula);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula como la indicada.");
        }
        this.coleccionMatriculas.remove(indice);
    }





    public int getTamano() {
        return this.coleccionMatriculas.size();
    }

    public ArrayList<Matricula> get(Alumno alumno) throws OperationNotSupportedException {
        ArrayList<Matricula> auxiliar = new ArrayList<>();
        for (Matricula matricula:coleccionMatriculas) {
            if (matricula != null && matricula.getAlumno().equals(alumno)) {
                auxiliar.add(new Matricula(matricula));

            }
        }

        return auxiliar;
        //int contador = 0;

        //Contar cuántas coincidencias del alumno hay en matrículas:
        //Recorre las matrículas
       /* for (Matricula matricula : coleccionMatriculas) {
            //Si en la matricula actual, su alumno = al alumno pasado...
            if (matricula.getAlumno().equals(alumno)) {
                contador++;
            }
        }

        //Crear array con el número de coincidencias del alumno en las matrículas:
        Matricula[] coleccionMatriculasAlumn = new Matricula[contador];

        int i = 0;
        //Para asignar las matrículas con coincidencias al nuevo array:
        for (Matricula matriculaAlumno : coleccionMatriculas){
            if (matriculaAlumno.getAlumno().equals(alumno)){
                coleccionMatriculasAlumn[i] = matriculaAlumno;
                i++;
            }
        }
        return coleccionMatriculasAlumn;*/
        //return auxiliar.toArray(new Matricula[0]);
    }


    public ArrayList<Matricula> get(String cursoAcademico) throws OperationNotSupportedException {
        ArrayList<Matricula> auxiliar = new ArrayList<>();
        for (Matricula matricula:coleccionMatriculas) {
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


}
