package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Matriculas {
private int capacidad;
private int tamano=0;
private Matricula[] coleccionMatriculas;

    public Matriculas(int capacidad) {
        if (!(capacidad > 0)) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        coleccionMatriculas = new Matricula[capacidad];
    }
    //copia profunda
    public Matricula[] get() throws OperationNotSupportedException {
        return copiaProfundaMatriculas();
    }

    private Matricula[] copiaProfundaMatriculas() throws OperationNotSupportedException {
        Matricula[] copiaMatriculas = new Matricula[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaMatriculas[i] = new Matricula(coleccionMatriculas[i]);
        }
        return copiaMatriculas;
    }
    //Buscar indice
    private int buscarIndice(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new IllegalArgumentException("ERROR: No se puede buscar una Matricula nula.");
        }

        int indice = 0;
        boolean matriculaEncontrado = false;
        while (!tamanoSuperado(indice) && !matriculaEncontrado) {
            if (get()[indice].equals(matricula)) {
                matriculaEncontrado = true;
            } else {
                indice++;
            }
        }
        return indice;
    }
    //Insertar Matricula
    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        Objects.requireNonNull(matricula, "ERROR: No se puede insertar una matrícula nula.");

        int indice = buscarIndice(matricula);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más matrículas.");
        }

        if (tamanoSuperado(indice)) {
            coleccionMatriculas[indice] = new Matricula(matricula);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una matrícula con ese identificador.");
        }
    }
    //Tamaño superado
    private boolean tamanoSuperado(int indice) {
        return indice >= getTamano();
    }
    //capacidad superada
    private boolean capacidadSuperada(int indice) {
        return indice >= getCapacidad();
    }
    //buscar Matricula
    public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede buscar una Matricula nula.");
        }

        int indice = buscarIndice(matricula);
        if (tamanoSuperado(indice)) {
            return null;
        } else {
            return new Matricula(get()[indice]);
        }
    }
    //borrar Matricula
    public void borrar (Matricula matricula) throws OperationNotSupportedException {
        Objects.requireNonNull(matricula,"ERROR: No se puede borrar una matrícula nula.");

        int indice = buscarIndice(matricula);
        if (tamanoSuperado(indice)){
            throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula como la indicada.");
        }
        else{
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }
    //desplazar posicion a la izquierda
    private void desplazarUnaPosicionHaciaIzquierda (int indice){
        coleccionMatriculas[indice]= null;
        int i;
        for (i=indice; !tamanoSuperado(i);i++){
            if (i<getCapacidad()-1) {
                coleccionMatriculas[i] = coleccionMatriculas [i+1];
            }
        }

        tamano--;
    }

    public int getCapacidad() {
        return capacidad;
    }


    public int getTamano() {
        return tamano;
    }

    public Matricula[] get(Alumno alumno){

        int contador = 0;

        //Contar cuántas coincidencias del alumno hay en matrículas:
        //Recorre las matrículas
        for (Matricula matricula : coleccionMatriculas) {
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
        return coleccionMatriculasAlumn;
    }


    public Matricula[] get(String cursoAcademico){

        int contador = 0;

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
        return coleccionMatriculasCurso;
    }


    public Matricula[] get(CicloFormativo cicloFormativo) {
        int contador = 0;

        for (Matricula matricula : coleccionMatriculas) {
            for (Asignatura asignatura : matricula.getColeccionAsignaturas()) {

                if (asignatura.getCicloFormativo().equals(cicloFormativo)) {
                    contador++;
                    break;
                }
            }
        }

        Matricula[] coleccionMatriculasCiclo = new Matricula[contador];

        int i = 0;

        // Recorre las matrículas
        for (Matricula matricula : coleccionMatriculas) {
            //Dentro de una matrícula, recorre sus asignaturas
            for (Asignatura asignatura : matricula.getColeccionAsignaturas()) {

                // Si una asignatura específica de las recorridas tiene ciclo = al ciclo pasado...
                if (asignatura.getCicloFormativo().equals(cicloFormativo)) {
                    coleccionMatriculasCiclo[i] = matricula;
                    i++;

                    // break para evitar procesar más asignaturas una vez que se encuentra una
                    // coincidencia dentro de la matrícula
                    break;
                }
            }
        }
        return coleccionMatriculasCiclo;
    }


}
