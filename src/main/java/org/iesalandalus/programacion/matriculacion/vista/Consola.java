package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.utilidades.Entrada;
import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.*;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {
    private Consola() {

    }
    public static void mostrarMenu() {
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion.toString());
        }
    }

    public static Opcion elegirOpcion() {
        int opcion;
       do{
           System.out.print("Elige una opcion del menu: ");
           opcion = Entrada.entero();
       } while (!(opcion >= 0 && opcion <= Opcion.values().length-1));
       return Opcion.values()[opcion];
    }

    public static Alumno leerAlumno() {
        String nombre;
        String telefono;
        String correo;
        String dni;
        LocalDate fechaNacimiento;

        System.out.println("Introduce los datos del alumno: ");
        do {
            System.out.println("\nIntroduce el nombre del alumno: ");
            nombre = Entrada.cadena();
        }while (nombre.isBlank()||nombre.isEmpty());
        do {
            System.out.println("\nIntroduce el telefono del alumno: ");
            telefono = Entrada.cadena();
        }while (telefono.isBlank()||telefono.isEmpty());
        do {
            System.out.println("\nIntroduce el correo del alumno: ");
            correo = Entrada.cadena();
        }while (correo.isBlank()||correo.isEmpty());
        do {
            System.out.println("\nIntroduce el DNI del alumno: ");
            dni = Entrada.cadena();
        }while (dni.isBlank()||dni.isEmpty());
       do {
            String mensaje = "Introduce la fecha de nacimiento del alumno:";
           fechaNacimiento = leerFecha(mensaje);
       }while (fechaNacimiento == null);


        return new Alumno(nombre, telefono, correo, dni, fechaNacimiento);

    }


    public static Alumno getAlumnoPorDni() {
        Alumno alumno= null;
        String nombre="ficticio";
        String telefono="111111111";
        String correo="ficticio@inventado.es";
        String dni="00000000A";
        LocalDate fechaNacimiento= LocalDate.now();

        do {
            System.out.println("\nIntroduce el DNI del alumno: ");
            dni = Entrada.cadena();
        }while (dni.trim().isBlank()||dni.trim().isEmpty());
        alumno= new Alumno(nombre, telefono, correo, dni, fechaNacimiento);
        return new Alumno(alumno);
    }

    public static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = Alumno.getFechaNacimiento();
        boolean fechaCorrecta = false;
        do{
            try {
                System.out.printf(mensaje, Alumno.FORMATO_FECHA);
                fecha = LocalDate.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA));
                fechaCorrecta = true;
            }
            catch (DateTimeParseException e){
                fechaCorrecta = false;
            }
        }while (!fechaCorrecta);
        return fecha;

    }
    public static Grado leerGrado() {
        int seleccion;
        do{
            System.out.println("Introduzca un Grado");

            for (Grado grado: Grado.values()){
                grado.imprimir();
            }
            seleccion=Entrada.entero();
        }
        while (seleccion<0 || seleccion>3 && seleccion > Grado.values().length);
        return Grado.values()[seleccion];
    }
    public static CicloFormativo leerCicloFormativo(CiclosFormativos ciclosFormativos) {
        int codigo;
        String familiaProfesional;
        Grado grado;
        String nombre;
        int horas;
        do {
            System.out.println("\nIntroduce el codigo del ciclo formativo: ");
            codigo = Entrada.entero();
        } while (codigo < 0);
        do {
            System.out.println("\nIntroduce la familia profesional del ciclo formativo: ");
            familiaProfesional = Entrada.cadena();
        } while (familiaProfesional.isBlank() || familiaProfesional.isEmpty());
        do {
            System.out.println("\nIntroduce el grado del ciclo formativo: ");
            grado = leerGrado();
        } while (grado == null || grado != Grado.values()[0] && grado != Grado.values()[1] && grado != Grado.values()[2]);
        do {
            System.out.println("\nIntroduce el nombre del ciclo formativo: ");
            nombre = Entrada.cadena();
        } while (nombre.trim().isBlank() || nombre.trim().isEmpty());
        do {
            System.out.println("\nIntroduce las horas del ciclo formativo: ");
            horas = Entrada.entero();
        } while (horas < 0);
        if (horas > CicloFormativo.MAXIMO_NUMERO_HORAS) {
            throw new IllegalArgumentException("ERROR: El numero de horas no puede ser mayor que " + CicloFormativo.MAXIMO_NUMERO_HORAS);
        }

        return new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);
    }
    public static void mostrarCiclosFormativos (CiclosFormativos ciclosFormativos){
        CicloFormativo [] listaCiclosFormativos = ciclosFormativos.get();
        for (CicloFormativo cicloFormativo : listaCiclosFormativos) {
            System.out.println(cicloFormativo);
        }
    }
    public static CicloFormativo getCicloFormativoPorCodigo() {
        int codigo;
        String familiaProfesional="ficticia";
        Grado grado=Grado.GDCFGB;
        String nombre="ficticio";
        int horas=0;
        do {
            System.out.println("\nIntroduce el codigo del ciclo formativo: ");
            codigo = Entrada.entero();

        } while (codigo < 0);
        return new CicloFormativo(codigo,familiaProfesional,grado,nombre,horas);
    }
    public static Curso leerCurso(){
        int seleccion;
        do{
            System.out.println("Introduzca un Curso");
            for (Curso curso: Curso.values()){
                curso.imprimir();
            }
            seleccion=Entrada.entero();
        }
        while (seleccion<0 || seleccion>1 && seleccion > Curso.values().length);
        return Curso.values()[seleccion];
    }

    public static EspecialidadProfesorado leerEspecialidadProfesorado() {
        int seleccion;
        do{
            System.out.println("Introduzca una EspecialidadProfesorado");
            for (EspecialidadProfesorado especialidadProfesorado: EspecialidadProfesorado.values()){
                especialidadProfesorado.imprimir();
            }
            seleccion=Entrada.entero();
        }
        while (seleccion<0 || seleccion>2 && seleccion > EspecialidadProfesorado.values().length);
        return EspecialidadProfesorado.values()[seleccion];
    }

    public static Asignatura leerAsignatura(CiclosFormativos ciclosFormativos) {
        String codigo;
        String nombre;
        int horasAnuales;
        Curso curso;
        int horasDesdoble;
        EspecialidadProfesorado especialidadProfesorado;
        CicloFormativo cicloFormativo;
        Asignatura asignatura;
        do {
            System.out.println("\nIntroduce el codigo de la asignatura: ");
            codigo = Entrada.cadena();
        } while (codigo.trim().isBlank() || codigo.trim().isEmpty());
        do {
            System.out.println("\nIntroduce el nombre de la asignatura: ");
            nombre = Entrada.cadena();
        } while (nombre.trim().isBlank() || nombre.trim().isEmpty());
        do {
            System.out.println("\nIntroduce las horas anuales de la asignatura: ");
            horasAnuales = Entrada.entero();
        } while (horasAnuales < 0);
        do {
            System.out.println("\nIntroduce el curso de la asignatura: ");
            curso = leerCurso();
        } while (curso == null);
        do {
            System.out.println("\nIntroduce las horas desdoble de la asignatura: ");
            horasDesdoble = Entrada.entero();
        } while (horasDesdoble < 0);
        do {
            System.out.println("\nIntroduce la especialidad profesorada de la asignatura: ");
            especialidadProfesorado = leerEspecialidadProfesorado();
        } while (especialidadProfesorado == null);
        do {
            System.out.println("\nIntroduce el ciclo formativo de la asignatura: ");
            cicloFormativo = leerCicloFormativo(ciclosFormativos);
        } while (cicloFormativo == null);

       asignatura=new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidadProfesorado, cicloFormativo);
        return new Asignatura(asignatura);
    }
    public static Asignatura getAsignaturaPorCodigo(){
        String codigo;
        String nombre="Asignaturaficticia";
        int horasAnuales=13;
        Curso curso=Curso.PRIMERO;
        int horasDesdoble=10;
        EspecialidadProfesorado especialidadProfesorado= EspecialidadProfesorado.SISTEMAS;
        CicloFormativo cicloFormativo= new CicloFormativo(1, "ficticio", Grado.GDCFGB, "ficticio", 0);
        Asignatura asignatura;
        do {
            System.out.println("\nIntroduce el codigo de la asignatura: ");
            codigo = Entrada.cadena();
        } while (codigo == null || codigo.trim().isBlank() || codigo.trim().isEmpty());
        asignatura=new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidadProfesorado, cicloFormativo);

        return new Asignatura(asignatura);
    }

    private static void mostrarAsignaturas(Asignaturas asignaturas){
       Asignatura [] listaAsignaturas = asignaturas.get();

       for (Asignatura asignatura :  listaAsignaturas) {
                System.out.println(asignatura.imprimir());

       }

    }
    //comparar si la asignatura ya esta matriculada
    private static boolean asignaturaYaMatriculada(Asignatura [] asignaturasMatricula, Asignatura asignatura){

        if (asignaturasMatricula != null) {
            for (int i = 0; i < asignaturasMatricula.length; i++) {
                if (asignaturasMatricula[i].equals(asignatura)) {
                    return true;

                }
            }
        }
       return false;
    }
    public static Matricula leerMatricula (Alumnos alumnos,Asignaturas asignaturas) throws OperationNotSupportedException {

        int idMatricula;
        String cursoAcademico;
        LocalDate fechaMatriculacion;
        Alumno alumno;
        Matricula matricula;

        System.out.println("Introduzca el Id de la Matrícula.");
        idMatricula = Entrada.entero();

        System.out.println("Introduzca el Curso Académico.");
        cursoAcademico = Entrada.cadena();

        String mensaje= "Introduzca la Fecha de matriculación.";
        fechaMatriculacion = leerFecha(mensaje);

        alumno= leerAlumno();
        Alumno alumnoBuscado=alumnos.buscar(alumno);

        matricula = new Matricula (idMatricula, cursoAcademico, fechaMatriculacion, alumno, asignaturas.get());

        return new Matricula (matricula);
    }

    public static Matricula getMatriculaPorIdentificador() throws OperationNotSupportedException {
        int idMatricula;
        String cursoAcademico = "24-25";
        LocalDate fechaMatriculacion = LocalDate.now();
        Alumno alumno = new Alumno("ficticio apellidoficticio", "00000000B", "ficticio@ficticio.es", "444444444", LocalDate.of(2013, 8, 21));

        CicloFormativo cicloFormativo = new CicloFormativo(4444, "familiaficticia", Grado.GDCFGS, "nombreFiciticio", 1500);

        Asignatura asignatura = new Asignatura("1234", "asignatura1", 180, Curso.PRIMERO, 5, EspecialidadProfesorado.SISTEMAS, cicloFormativo);
        Asignatura asignatura2 = new Asignatura("4321", "asignatura2", 260, Curso.PRIMERO, 2, EspecialidadProfesorado.INFORMATICA, cicloFormativo);
        Asignatura asignatura3 = new Asignatura("5555", "asignatura3", 100, Curso.PRIMERO, 3, EspecialidadProfesorado.FOL, cicloFormativo);

        Asignaturas coleccionAsignaturas = new Asignaturas(2);
        coleccionAsignaturas.insertar(asignatura);
        coleccionAsignaturas.insertar(asignatura2);

        Matricula matricula;

        System.out.println("Introduzca el id de la Matrícula.");
        idMatricula = Entrada.entero();

        matricula = new Matricula(idMatricula, cursoAcademico, fechaMatriculacion, alumno, coleccionAsignaturas.get());

        return new Matricula(matricula);
    }

}
