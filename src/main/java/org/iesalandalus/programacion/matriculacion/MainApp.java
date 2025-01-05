package org.iesalandalus.programacion.matriculacion;
import org.iesalandalus.programacion.matriculacion.negocio.*;
import org.iesalandalus.programacion.matriculacion.vista.*;
import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;
import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;



public class MainApp {
    public static final int CAPACIDAD=3;
    private static CiclosFormativos ciclosFormativos = new CiclosFormativos(CAPACIDAD);
    private static Alumnos alumnos = new Alumnos(CAPACIDAD);
    private static Asignaturas asignaturas= new Asignaturas(CAPACIDAD);
    private static Matriculas matriculas= new Matriculas(CAPACIDAD);


    public static void main(String[] args) {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion= Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        }while(opcion!=Opcion.SALIR);

        System.out.println("Hasta luego!!!!");
    }

    private static void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case SALIR:
                System.out.println("Hasta luego!!");
                System.exit(0);
            case INSERTAR_ALUMNO:
                insertarAlumno();
                System.out.println("Insertar alumno");
                break;
            case BUSCAR_ALUMNO:
                buscarAlumno();
                System.out.println("Buscar alumno");
                break;
            case BORRAR_ALUMNO:
               borrarAlumno();
                System.out.println("Borrar alumno");
                break;
            case MOSTRAR_ALUMNOS:
                mostrarAlumnos();
                System.out.println("Mostrar alumnos");
                break;
            case INSERTAR_CICLO_FORMATIVO:
                insertarCicloFormativo();
                System.out.println("Insertar ciclo formativo");
                break;
            case BUSCAR_CICLO_FORMATIVO:
                buscarCicloFormativo();
                System.out.println("Buscar ciclo formativo");
                break;
            case BORRAR_CICLO_FORMATIVO:
                borrarCicloFormativo();
                System.out.println("Borrar ciclo formativo");
                break;
            case MOSTRAR_CICLOS_FORMATIVOS:
                mostrarCicloFormativos();
                System.out.println("Mostrar ciclos formativos");
                break;
            case INSERTAR_ASIGNATURA:
                insertarAsignatura();
                System.out.println("Insertar asignatura");
                break;
            case BUSCAR_ASIGNATURA:
                buscarAsignatura();
                System.out.println("Buscar asignatura");
                break;
            case BORRAR_ASIGNATURA:
                borrarAsignatura();
                System.out.println("Borrar asignatura");
                break;
            case MOSTRAR_ASIGNATURAS:
                mostrarAasignaturas();
                System.out.println("Mostrar asignaturas");
                break;
            case INSERTAR_MATRICULA:
                insertarMatricula();
                System.out.println("Insertar matricula");
                break;
            case BUSCAR_MATRICULA:
                buscarAsignatura();
                System.out.println("Buscar matricula");
                break;
            case ANULAR_MATRICULA:
                anularMatricula();
                System.out.println("Borrar matricula");
                break;
            case MOSTRAR_MATRICULAS:
                mostrarMatriculas();
                System.out.println("Mostrar matriculas");
                break;
            case MOSTRAR_MATRICULAS_ALUMNO:
                mostrarMatriculasPorAlumno();
                System.out.println("Mostrar matriculas alumno");
                break;
            case MOSTRAR_MATRICULAS_CICLO_FORMATIVO:
                mostrarMatriculasPorCicloFormativo();
                System.out.println("Mostrar matriculas ciclo formativo");
                break;
            case MOSTRAR_MATRICULAS_CURSO_ACADEMICO:
                mostrarMatriculasPorCursoAcademico();
                System.out.println("Mostrar matriculas curso academico");
                break;
                
        }
    }
    //insertar alumno
    private static void insertarAlumno() {
        try {
            Alumno alumno = Consola.leerAlumno();
            alumnos.insertar(alumno);
            System.out.println("Alumno insertado correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar un Alumno nulo.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
    //buscar Alumno
    private static void buscarAlumno() {
        try {
            Alumno alumnoBuscado = alumnos.buscar(Consola.getAlumnoPorDni());
            Alumno encontrado = alumnos.buscar(alumnoBuscado);
            if (encontrado != null) {
                System.out.printf("Los datos del alumno solicitado son: %s", alumnoBuscado);
            } else {
                System.out.println("No existe ningun alumno con tales datos.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: 3.No se puede buscar un Alumno nulo.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    //borrar Alumno
    private static void borrarAlumno() {
        try {
            Alumno alumnoBorrar = Consola.getAlumnoPorDni();
            alumnos.borrar(alumnoBorrar);
            System.out.println("Alumno borrado correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede borrar un Alumno nulo.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (OperationNotSupportedException e) {
            throw new RuntimeException(e);
        }

    }
    //mostrar Alumnos
    private static void mostrarAlumnos() {
        Alumno [] arrayAlumnos = alumnos.get();
        if (arrayAlumnos.length >0) {
            for (Alumno alumno : arrayAlumnos) {
                System.out.println(alumno);
            }
        } else if (alumnos.getTamano() == 0) {
            System.out.println("No existen alumnos.");

        }else {
            System.out.println("No hay alumnos.");
        }
    }
    //insertar Asignatura
    private static void insertarAsignatura() {
        try {
            Asignatura asignatura = Consola.leerAsignatura(ciclosFormativos);
            asignaturas.insertar(asignatura);
            System.out.println("Asignatura insertada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar una Asignatura nula.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
    //buscar Asignatura
    private static void buscarAsignatura() {
        try {
            Asignatura asignaturaBuscar = asignaturas.buscar(Consola.getAsignaturaPorCodigo());
            Asignatura encontrada = asignaturas.buscar(asignaturaBuscar);
            if (encontrada != null) {
                System.out.printf("Los datos de la asignatura solicitada son: %s", asignaturaBuscar);
            } else {
                System.out.println("No existe ninguna asignatura con tales datos.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede buscar una asignatura nula.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    //borrar Asignatura
    private static void borrarAsignatura() {
        try {
            Asignatura asignaturaBorrar = Consola.getAsignaturaPorCodigo();
            asignaturas.borrar(asignaturaBorrar);
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
    private static void mostrarAasignaturas() {
        Asignatura [] arrayAsignatura = asignaturas.get();
        if (arrayAsignatura.length >0) {
            for (Asignatura asignatura : arrayAsignatura) {
                System.out.println(asignatura);
            }
        } else if (alumnos.getTamano() == 0) {
            System.out.println("No existen asignaturas.");

        }else {
            System.out.println("No hay asignaturas.");
        }
    }
    //insertar CicloFormativo
    private static void insertarCicloFormativo() {
        try {
            CicloFormativo ciclosFormativo = Consola.leerCicloFormativo(ciclosFormativos);
            ciclosFormativos.insertar(ciclosFormativo);
            System.out.println("Ciclo formativo insertada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar Ciclo Formativo nulo.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
    //buscar CicloFormativo
    private static void buscarCicloFormativo() {
        try {
            CicloFormativo cicloFormativoBuscar = ciclosFormativos.buscar(Consola.getCicloFormativoPorCodigo());
            CicloFormativo encontrada = ciclosFormativos.buscar(cicloFormativoBuscar);
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
    private static void borrarCicloFormativo() {
        try {
            CicloFormativo cicloFormativoBorrar = Consola.getCicloFormativoPorCodigo();
            ciclosFormativos.borrar(cicloFormativoBorrar);
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
    private static void mostrarCicloFormativos() {
        CicloFormativo[] arrayCicloFormativo = ciclosFormativos.get();
        if (arrayCicloFormativo.length > 0) {
            for (CicloFormativo cicloFormativo : arrayCicloFormativo) {
                System.out.println(cicloFormativo);
            }
        } else if (alumnos.getTamano() == 0) {
            System.out.println("No existen ciclos formativos.");

        }
    }
    //insertar Matricula
    private static void insertarMatricula() {
        try {
            Matricula matricula = Consola.leerMatricula(alumnos, asignaturas);
            matriculas.insertar(matricula);
            System.out.println("Matricula insertada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar una Matricula nula.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
    //buscar Matricula
    private static void buscarMatricula() {
        try {
            Matricula matriculaBuscar = matriculas.buscar(Consola.getMatriculaPorIdentificador());
            Matricula encontrada =matriculas.buscar(matriculaBuscar);
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
    private static void anularMatricula() {
        try{
            Alumno alumno = Consola.getAlumnoPorDni();
            Matricula matrioculaAnular = matriculas.buscar(Consola.getMatriculaPorIdentificador());
            if (matrioculaAnular != null && matrioculaAnular.getAlumno().equals(alumno)) {
                matriculas.borrar(matrioculaAnular);
                System.out.println("indique la fecha de anulación:");
                String fechaAnulacion = (Entrada.cadena());
                LocalDate fechaAnular;
                fechaAnular = LocalDate.parse(fechaAnulacion);
                matrioculaAnular.setFechaAnulacion(fechaAnular);
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
    private static void mostrarMatriculas() {
        Matricula[] arrayMatriculas = matriculas.get();
        if (arrayMatriculas.length > 0) {
            for (Matricula matricula: arrayMatriculas) {
                System.out.println(matricula);
            }
        } else if (alumnos.getTamano() == 0) {
            System.out.println("No existen Matriculas.");

        }
    }
    //mostrar Matricula por Alumno
    private static void mostrarMatriculasPorAlumno() {
        Alumno alumno = Consola.getAlumnoPorDni();
        Matricula[] arrayMatricula = matriculas.get();
        for (Matricula matricula : arrayMatricula) {
            if (matricula.getAlumno().equals(alumno)) {
                System.out.println(matricula);
            }else {
                System.out.println("No existen matriculas para el alumno indicado.");
            }
        }
    }
    //mostrar Matricula por CicloFormativo
    private static void mostrarMatriculasPorCicloFormativo() {
     Consola.mostrarCiclosFormativos(ciclosFormativos);
     int codigo=Entrada.entero();
     CicloFormativo[] CicloFormativo=ciclosFormativos.get();
        if (codigo == CicloFormativo[0].getCodigo()) {
            System.out.println(matriculas.get().equals(CicloFormativo[0]));
        }
        if (ciclosFormativos == null) {
            System.out.println("No existen matriculas para el ciclo formativo indicado.");
        }

    }
    //mostrar Matricula por curso academico
    private static void mostrarMatriculasPorCursoAcademico() {
        Matricula[] arrayMatriculas = matriculas.get();

        for (Matricula matricula : arrayMatriculas) {
            if (matricula == null) {
                System.out.println("No existen matriculas para el curso academico indicado.");

            }else if(matricula.getCursoAcademico().equals(LocalDate.now().getYear())) {
                System.out.println(matricula);
            }else {
                System.out.println("No existen matriculas para el curso academico indicado.");
            }

        }

    }



}
