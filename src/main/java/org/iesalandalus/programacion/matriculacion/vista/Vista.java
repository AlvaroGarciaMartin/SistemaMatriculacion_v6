package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;


public class Vista {
    private Controlador controlador;

public Vista(){
    Opcion.setVista(this);
}

    public void comenzar() {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion= Consola.elegirOpcion();
            opcion.ejecutar();
        }while(opcion!=Opcion.SALIR);
    }
    public void terminar() {
        controlador.terminar();
    }

    public void setControlador(Controlador controlador) {
        if (controlador == null) {
            throw new NullPointerException("ERROR: El controlador no puede ser nulo");
        }
        this.controlador = controlador;
    }


    //insertar alumno
    public void insertarAlumno() {
        try {
            Alumno alumno = Consola.leerAlumno();
            controlador.insertar(alumno);
            System.out.println("Alumno insertado correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar un Alumno nulo.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
    //buscar Alumno
    public void buscarAlumno() {
        try {
           //Alumno alumnoBuscado = alumnos.buscar(Consola.getAlumnoPorDni());
            //Alumno encontrado = alumnos.buscar(alumnoBuscado);
            Alumno alumnoBuscado = controlador.buscar(Consola.getAlumnoPorDni());
            if (alumnoBuscado != null) {
                System.out.printf("Los datos del alumno solicitado son: %s", alumnoBuscado);
            }
             else {
                System.out.println("No existe ningun alumno con tales datos.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: 3.No se puede buscar un Alumno nulo.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    //borrar Alumno
    public void borrarAlumno() {
        try {
            //Alumno alumnoBorrar = Consola.getAlumnoPorDni();
            //alumnos.borrar(alumnoBorrar);
            controlador.borrar(Consola.getAlumnoPorDni());
            System.out.println("Alumno borrado correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede borrar un Alumno nulo.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }

    }
    //mostrar Alumnos
    public void mostrarAlumnos() {
        ArrayList<Alumno> arrayAlumnos = controlador.getAlumnos();
        if (arrayAlumnos.size() == 0) {
            System.out.println("No existen alumnos.");
        }
        else {
            arrayAlumnos.sort(Comparator.comparing(Alumno::getNombre));

            for (Alumno a : arrayAlumnos) {
                System.out.println(a);
            }
        }
    }
    //insertar Asignatura
    public void insertarAsignatura() {

        try {
            CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            CicloFormativo ciclo = controlador.buscar(cicloFormativo);
            if (ciclo == null) {
                System.out.println("No existe el ciclo formativo indicado.");
                return;
            }
            Asignatura asignatura = Consola.leerAsignatura(ciclo);
            //asignaturas.insertar(asignatura);
            controlador.insertar(asignatura);
            System.out.println("Asignatura insertada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar una Asignatura nula.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
    //buscar Asignatura
    public void buscarAsignatura() {
        try {
            //Asignatura asignaturaBuscar = asignaturas.buscar(Consola.getAsignaturaPorCodigo());
            //Asignatura encontrada = asignaturas.buscar(asignaturaBuscar);
            Asignatura asignaturaBuscar = controlador.buscar(Consola.getAsignaturaPorCodigo());
            Asignatura encontrada = controlador.buscar(asignaturaBuscar);
            if (encontrada != null) {
                System.out.printf("Los datos de la asignatura solicitada son: %s", asignaturaBuscar);
            } else {
                System.out.println("No existe ninguna asignatura con tales datos.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede buscar una asignatura nula.");
        }
    }
    //borrar Asignatura
    public  void borrarAsignatura() {
        try {
            Asignatura asignaturaBorrar = Consola.getAsignaturaPorCodigo();
            //asignaturas.borrar(asignaturaBorrar);
            controlador.borrar(asignaturaBorrar);
            System.out.println("Asignatura borrada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede borrar una asignatura nula.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (OperationNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    //mostrar Asignatura
    public void mostrarAsignaturas() {
        //Asignatura [] arrayAsignatura = asignaturas.get();
        ArrayList<Asignatura> arrayAsignatura = controlador.getAsignaturas();
        if (arrayAsignatura.size()==0) {
            System.out.println("No existen asignaturas.");
        }/* else{
            arrayAsignatura.sort(new Comparator<Asignatura>(){
                @Override
                public int compare(Asignatura o1, Asignatura o2) {
                    return o1.getNombre().compareTo(o2.getNombre());
                }
            });
        }
        for(Asignatura asig : arrayAsignatura){
            System.out.println(asig);
        }*/
        else {
            arrayAsignatura.sort(Comparator.comparing(Asignatura::getNombre));
            for (Asignatura asignatura : arrayAsignatura) {
                System.out.println(asignatura);
            }
        }


    }
    //insertar CicloFormativo
    public void insertarCicloFormativo() {
        try {
            CicloFormativo ciclosFormativo = Consola.leerCicloFormativo();
            controlador.insertar(ciclosFormativo);
            //ciclosFormativos.insertar(ciclosFormativo);
            System.out.println("Ciclo formativo insertada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar Ciclo Formativo nulo.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
    //buscar CicloFormativo
    public void buscarCicloFormativo() {
        try {
            //CicloFormativo cicloFormativoBuscar = ciclosFormativos.buscar(Consola.getCicloFormativoPorCodigo());
            //CicloFormativo encontrada = ciclosFormativos.buscar(cicloFormativoBuscar);
            CicloFormativo cicloFormativoBuscar = controlador.buscar(Consola.getCicloFormativoPorCodigo());
            CicloFormativo encontrada = controlador.buscar(cicloFormativoBuscar);
            if (encontrada != null) {
                System.out.printf("Los datos del ciclo formativo solicitado son: %s", cicloFormativoBuscar);
            } else {
                System.out.println("No existe ningun ciclo formativo con tales datos.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede buscar un ciclo formativo nulo.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    //borrar CicloFormativo
    public void borrarCicloFormativo() {
        try {
            CicloFormativo cicloFormativoBorrar = Consola.getCicloFormativoPorCodigo();
            //ciclosFormativos.borrar(cicloFormativoBorrar);
            controlador.borrar(cicloFormativoBorrar);
            System.out.println("Ciclo formativo borrada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede borrar un ciclo formativo nulo.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (OperationNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    //mostrar CicloFormativo
    public void mostrarCicloFormativos() {
        //CicloFormativo[] arrayCicloFormativo = ciclosFormativos.get();
        ArrayList<CicloFormativo> arrayCicloFormativo = controlador.getCicloFormativos();
        if (arrayCicloFormativo.size() == 0) {
            System.out.println("No existen ciclos formativos.");
        }/*
        else{
            arrayCicloFormativo.sort(new Comparator<CicloFormativo>(){
                @Override
                public int compare(CicloFormativo o1, CicloFormativo o2) {
                    return o1.getNombre().compareTo(o2.getNombre());
                }
            });
        }
        for(CicloFormativo c : arrayCicloFormativo){
            System.out.println(c);
        }*/
        else {
            arrayCicloFormativo.sort(Comparator.comparing(CicloFormativo::getNombre));
            for (CicloFormativo cicloFormativo : arrayCicloFormativo) {
                System.out.println(cicloFormativo);
            }
        }
    }
    //insertar Matricula
    public void insertarMatricula() {
        try {
            System.out.println("Datos del alumno:");
            Alumno alumno = Consola.getAlumnoPorDni();
            Alumno a = controlador.buscar(alumno);
            if (a == null) {
                System.out.println("No existe el alumno indicado.");
                return;
            }
            System.out.println("Asignaturas de la matricula:");
            ArrayList<Asignatura> matriculaAsignaturas = Consola.elegirAsignaturasMatricula(controlador.getAsignaturas());
            System.out.println("Datos de la matricula:");
            Matricula matricula = Consola.leerMatricula(a, matriculaAsignaturas);
            controlador.insertar(matricula);
            System.out.println("Matricula insertada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar una Matricula nula.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
    //buscar Matricula
    public void buscarMatricula() {
        try {
            //Matricula matriculaBuscar = matriculas.buscar(Consola.getMatriculaPorIdentificador());
            //Matricula encontrada =matriculas.buscar(matriculaBuscar);
            Matricula matriculaBuscar = controlador.buscar(Consola.getMatriculaPorIdentificador());
            Matricula encontrada =controlador.buscar(matriculaBuscar);
            if (encontrada != null) {
                System.out.printf("Los datos del ciclo formativo solicitado son: %s", matriculaBuscar);
            } else {
                System.out.println("No existe ningun ciclo formativo con tales datos.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede buscar un ciclo formativo nulo.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (OperationNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    //Anular Matricula
    public void anularMatricula() {
        try{
            Alumno alumno = Consola.getAlumnoPorDni();
            //Matricula matriculaAnular = matriculas.buscar(Consola.getMatriculaPorIdentificador());
            Matricula matriculaAnular = controlador.buscar(Consola.getMatriculaPorIdentificador());
            if (matriculaAnular != null && matriculaAnular.getAlumno().equals(alumno)) {
                //controlador.borrar(matriculaAnular);
                System.out.println("indique la fecha de anulación:");
                String fechaAnulacion = (Entrada.cadena());
                LocalDate fechaAnular;
                fechaAnular = LocalDate.parse(fechaAnulacion);
                matriculaAnular.setFechaAnulacion(fechaAnular);
                System.out.println("Matricula anulada correctamente.");
            } else {
                System.out.println("No se ha encontrado la matricula o no corresponde al alumno indicado.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede anular una matricula nula.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
    //mostrar Matriculas
    public void mostrarMatriculas() {
        try {
            //Matricula[] arrayMatriculas = matriculas.get();
            ArrayList<Matricula> arrayMatriculas = controlador.getMatriculas();
            if (arrayMatriculas.size()== 0) {
                System.out.println("No existen Matriculas.");
            } /*else{
                arrayMatriculas.sort( new Comparator<Matricula>(){
                    @Override
                    public int compare(Matricula m1, Matricula m2) {
                        int comp = - m1.getFechaMatriculacion().compareTo(m2.getFechaMatriculacion());
                        if (comp==0) {
                            comp = m1.getAlumno().getNombre().compareTo(m2.getAlumno().getNombre());
                        }
                        return comp;
                    }

                });
            }*/
            else{
                arrayMatriculas.sort(Comparator.comparing(Matricula::getFechaMatriculacion).reversed().thenComparing(matricula -> matricula.getAlumno().getNombre()));
            }
            for(Matricula m : arrayMatriculas){
                System.out.println(m);
            }

        }catch (OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo mostrar matriculas.");
        }
    }
    //mostrar Matricula por Alumno
    public void mostrarMatriculasPorAlumno()  {
        try {

            Alumno alumno = Consola.getAlumnoPorDni();
            //Matricula[] arrayMatricula = matriculas.get();
            ArrayList<Matricula> arrayMatricula = controlador.getMatriculas(alumno);
            if (arrayMatricula.size() == 0) {
                System.out.println("No existen matriculas para el alumno indicado.");
            } else {
                arrayMatricula.sort(
                        Comparator.comparing(Matricula::getFechaMatriculacion).reversed()
                                .thenComparing(m -> m.getAlumno().getNombre())
                );
                for (Matricula matricula : arrayMatricula) {
                    System.out.println(matricula);
                }
            }
           /* arrayMatricula.sort(new Comparator<Matricula>(){
                @Override
                public int compare(Matricula m1, Matricula m2) {
                    int comp = - m1.getFechaMatriculacion().compareTo(m2.getFechaMatriculacion());
                    if (comp==0) {
                        comp = m1.getAlumno().getNombre().compareTo(m2.getAlumno().getNombre());
                    }
                    return comp;
                }

            });
            for (Matricula matricula : arrayMatricula) {
                    System.out.println(matricula);

            }*/
        }catch (OperationNotSupportedException e){
            System.out.println("ERROR: No se pueden mostrar matriculas por alumno");
        }
    }
    //mostrar Matricula por CicloFormativo
    public void mostrarMatriculasPorCicloFormativo(){
        CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
        cicloFormativo = controlador.buscar(cicloFormativo);
        if (cicloFormativo == null) {
            System.out.println("No existe ningun ciclo formativo con tales datos.");
        }
        ArrayList<Matricula> matriculaCiclo;
        try {
            matriculaCiclo = controlador.getMatriculas(cicloFormativo);
            if (matriculaCiclo.size() == 0) {
                System.out.println("No existen matriculas para el ciclo formativo indicado.");
            }

            System.out.println("Matrículas del ciclo formativo " + cicloFormativo.getCodigo() + ":");
            matriculaCiclo.sort(
                    Comparator.comparing(Matricula::getFechaMatriculacion).reversed()
                            .thenComparing(m -> m.getAlumno().getNombre())
            );
            for (Matricula matricula : matriculaCiclo) {
                System.out.println(matricula);
            }
        }catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }


    }
    //mostrar Matricula por curso academico
    public void mostrarMatriculasPorCursoAcademico() {
        try {
            System.out.println("indique el curso academico:");
            System.out.println("El formato del curso es YY-YY");
            String cursoAcademico = Entrada.cadena();
            ArrayList<Matricula> arrayMatriculas = controlador.getMatriculas(cursoAcademico);
             /* arrayMatriculas.sort(new Comparator<Matricula>(){
              @Override
                public int compare(Matricula m1, Matricula m2) {
                    int comp = - m1.getFechaMatriculacion().compareTo(m2.getFechaMatriculacion());
                    if (comp==0) {
                        comp = m1.getAlumno().getNombre().compareTo(m2.getAlumno().getNombre());
                    }
                    return comp;
                }
            });*/
             if (arrayMatriculas.size() == 0) {
                    System.out.println("No existen matrículas para el curso académico indicado.");
                    return;
                }
            System.out.println("Matrículas del curso académico " + cursoAcademico + ":");
			arrayMatriculas.sort(
                    Comparator.comparing(Matricula::getFechaMatriculacion).reversed()
	                      .thenComparing(m -> m.getAlumno().getNombre())
                        );
			for (Matricula matricula : arrayMatriculas) {
                    System.out.println(matricula);
                }
            /*for (Matricula matricula : arrayMatriculas) {
                if (matricula == null) {
                    System.out.println("No existen matriculas para el curso academico indicado.");

                } else if (matricula.getCursoAcademico().equals(LocalDate.now().getYear())) {
                    System.out.println(matricula);
                } else if (matricula.getCursoAcademico().equals(cursoAcademico)) {
                    System.out.println(matricula);
                } else {
                    System.out.println("No existen matriculas para el curso academico indicado.");
                }

            }*/
        } catch (OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo mostrar matriculas por curso academico.");
        }

    }
}
