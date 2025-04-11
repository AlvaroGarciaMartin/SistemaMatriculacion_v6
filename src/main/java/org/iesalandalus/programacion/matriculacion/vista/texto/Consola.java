package org.iesalandalus.programacion.matriculacion.vista.texto;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


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
        String nombre="ficticio";
        String telefono="111111111";
        String correo="ficticio@inventado.es";
        String dni="00000000T";
        LocalDate fechaNacimiento= LocalDate.of(1991, 12, 12);

        do {
            System.out.println("\nIntroduce el DNI del alumno: ");
            dni = Entrada.cadena();
        }while (dni.trim().isBlank()||dni.trim().isEmpty());

        return new Alumno(nombre, telefono, correo, dni, fechaNacimiento);

    }

    /*public static LocalDate leerFecha(String mensaje) {
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

    }*/
    public static LocalDate leerFecha(String mensaje) {
        do {
            try {
                System.out.printf(mensaje, Alumno.FORMATO_FECHA);
                LocalDate fecha = LocalDate.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA));
                return fecha;
            } catch (DateTimeParseException e) {
                System.out.println("ERROR: El formato de la fecha no es correcto.");
            }
        } while (true);
    }


    public static TiposGrado leerTiposGrado() {
        int seleccion;
        do{
            System.out.println("Introduzca el codigo del tipo de Grado");
            for (TiposGrado tiposGrado: TiposGrado.values()){
                System.out.println(tiposGrado.imprimir());
                //tiposGrado.imprimir();
            }
            seleccion=Entrada.entero();
            if (seleccion == 1) {
                seleccion = 0;
            }else if (seleccion == 2) {
               seleccion = 1;
            }
        }
        while (seleccion < 1 || seleccion >= TiposGrado.values().length);
        return TiposGrado.values()[seleccion];
    }
    public static Modalidad leerModalidad() {
        int seleccion;
        do{
            System.out.println("Introduzca el codigo del tipo de Grado");
            //System.out.println("1.GDCFGB\n2.GDCFGM\n3.GDCFGS");
            for (Modalidad modalidad: Modalidad.values()){
                System.out.println(modalidad.imprimir());
                //modalidad.imprimir();
            }
            seleccion=Entrada.entero();
            if (seleccion == 1) {
                seleccion = 0;
            }else if (seleccion == 2) {
                seleccion = 1;
            }
        }
        while (seleccion < 1 || seleccion >= Modalidad.values().length);
        return Modalidad.values()[seleccion];
    }
    public static CicloFormativo leerCicloFormativo() {
        int codigo;
        String familiaProfesional;
        Grado grado;
        String nombre;
        int horas;
        do {
            System.out.println("\nIntroduce el codigo del ciclo formativo: ");
            System.out.println("\nEl codigo debe ser un numero de 4 digitos.");
            codigo = Entrada.entero();
        } while (codigo < 0);
        do {
            System.out.println("\nIntroduce la familia profesional del ciclo formativo: ");
            familiaProfesional = Entrada.cadena();
        } while (familiaProfesional.isBlank());

            System.out.println("\nIntroduce el grado del ciclo formativo: ");
            grado = leerGrado();

        do {
            System.out.println("\nIntroduce el nombre del ciclo formativo: ");
            nombre = Entrada.cadena();
        } while (nombre.trim().isBlank() || nombre.trim().isEmpty());
        do {
            System.out.println("\nIntroduce las horas del ciclo formativo: ");
            System.out.println("El maximo numero de horas es " + CicloFormativo.MAXIMO_NUMERO_HORAS);
            horas = Entrada.entero();
        } while (horas < 0);
        if (horas > CicloFormativo.MAXIMO_NUMERO_HORAS) {
            throw new IllegalArgumentException("ERROR: El numero de horas no puede ser mayor que " + CicloFormativo.MAXIMO_NUMERO_HORAS);
        }

        return new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);
    }

    public static Grado leerGrado() {
        TiposGrado tipoGrado = leerTiposGrado();

        //Pedimos al usuario el nombre del grado
        String nombre = "";
        do {
            System.out.println("Introduce el nombre del grado:");
            nombre = Entrada.cadena();
        } while (nombre.isBlank());

        //pedimos al usuario los años del grado
        int numAnios = -1;
        do {
            System.out.println("\nIntroduce el numero de años del grado: ");
            System.out.println("\nSi el grado seleccionado es gradoD solo puede ser 2 o 3 y si es gradoE solo puede ser 1");
            numAnios = Entrada.entero();
        } while (tipoGrado == TiposGrado.GRADOD && (numAnios < 2 || numAnios > 3) || tipoGrado == TiposGrado.GRADOE && numAnios != 1);


        if (tipoGrado == TiposGrado.GRADOD) {
            Modalidad modalidad = leerModalidad();
            return new GradoD(nombre, numAnios, modalidad);
        } else {
            int numEdiciones = -1;
            do {
                System.out.println("\nIntroduce el numero de ediciones del grado: ");
                System.out.println("\nel valor de numero de ediciones solo puede ser 1 ");
                numEdiciones = Entrada.entero();
            } while (numEdiciones < 1);
            return new GradoE(nombre, numAnios, numEdiciones);
        }

    }

    public static void mostrarCiclosFormativos (ArrayList<CicloFormativo> ciclosFormativos){
        System.out.println("Lista de ciclos formativos disponibles:");
        if (ciclosFormativos.size() == 0) {
            System.out.println("No hay ciclos formativos disponibles.");
        }else{
            for (CicloFormativo cicloFormativo : ciclosFormativos) {
                if (cicloFormativo != null) {
                 System.out.println(cicloFormativo);
                }
            }
        }
    }

    public static CicloFormativo getCicloFormativoPorCodigo() {
        int codigo;
        String familiaProfesional="ficticia";
        Grado grado=new GradoD("Grado Ficitico", 2, Modalidad.PRESENCIAL);
        String nombre="ficticio";
        int horas=1;
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
                System.out.println(curso.imprimir());
                //curso.imprimir();
            }
            seleccion=Entrada.entero();
            /*if (seleccion == 1) {
                seleccion = 0;
            }else if (seleccion == 2) {
                seleccion = 1;
            }*/
        }
        while (seleccion < 0 || seleccion >= Curso.values().length);
        return Curso.values()[seleccion];
    }

    public static EspecialidadProfesorado leerEspecialidadProfesorado() {
        int seleccion;
        do{
            System.out.println("Introduzca una Especialidad de Profesorado");
            for (EspecialidadProfesorado especialidadProfesorado: EspecialidadProfesorado.values()){
                //especialidadProfesorado.imprimir();
                System.out.println(especialidadProfesorado.imprimir());
            }
            seleccion=Entrada.entero();
            /*if (seleccion == 1) {
                seleccion = 0;
            }else if (seleccion == 2) {
                seleccion = 1;
            }else if (seleccion == 3) {
                seleccion = 2;
            }*/
        }
        while (seleccion < 0 || seleccion >= EspecialidadProfesorado.values().length);
        return EspecialidadProfesorado.values()[seleccion];
    }

    public static Asignatura leerAsignatura(CicloFormativo ciclosFormativos) {
        String codigo;
        String nombre;
        int horasAnuales;
        Curso curso;
        int horasDesdoble;
        EspecialidadProfesorado especialidadProfesorado;
        do {
            System.out.println("\nIntroduce el codigo de la asignatura: ");
            System.out.println("El codigo debe tener un formato de 4 digitos.");
            codigo = Entrada.cadena();
        } while (codigo.trim().isBlank());
        do {
            System.out.println("\nIntroduce el nombre de la asignatura: ");
            nombre = Entrada.cadena();
        } while (nombre.trim().isBlank());
        do {
            System.out.println("\nIntroduce las horas anuales de la asignatura: ");
            System.out.println("El numero de horas tiene que ser entre 0 y 300.");
            horasAnuales = Entrada.entero();
        } while (horasAnuales < 0 || horasAnuales > 300);

            System.out.println("\nIntroduce el curso de la asignatura: ");
            curso = leerCurso();

        do {
            System.out.println("\nIntroduce las horas desdoble de la asignatura: ");
            System.out.println("El numero de horas de desdoble tiene que ser entre 0 y 6");
            horasDesdoble = Entrada.entero();
        } while (horasDesdoble < 0 || horasDesdoble > 6);


            System.out.println("\nIntroduce la especialidad profesorada de la asignatura: ");
            especialidadProfesorado = leerEspecialidadProfesorado();


         return new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidadProfesorado, ciclosFormativos);

    }
    public static Asignatura getAsignaturaPorCodigo(){
        String codigo;
        String nombre="Asignaturaficticia";
        int horasAnuales=13;
        Curso curso=Curso.PRIMERO;
        int horasDesdoble=5;
        Grado grado=new GradoD("Grado Ficitico", 2, Modalidad.PRESENCIAL);
        EspecialidadProfesorado especialidadProfesorado= EspecialidadProfesorado.SISTEMAS;
        CicloFormativo cicloFormativo= new CicloFormativo(9999, "ficticio", grado, "ficticio", 200);
        Asignatura asignatura;
        do {
            System.out.println("\nIntroduce el codigo de la asignatura: ");
            codigo = Entrada.cadena();
        } while (codigo == null || codigo.trim().isBlank() || codigo.trim().isEmpty());

        asignatura=new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidadProfesorado, cicloFormativo);

        return new Asignatura(asignatura);
    }

   /* private static void mostrarAsignaturas(Asignaturas asignaturas){
       Asignatura [] listaAsignaturas = asignaturas.get();

       for (Asignatura asignatura :  listaAsignaturas) {
                System.out.println(asignatura.imprimir());

       }

    }*/

    private static void mostrarAsignaturas(ArrayList<Asignatura> asignaturas){
        System.out.println("Listado de Asignaturas disponibles:");
        for (Asignatura asignatura : asignaturas) {
            if (asignatura != null) {
                System.out.println(asignatura);
            }

        }
    }



    /*public static ArrayList<Asignatura> elegirAsignaturasMatricula(ArrayList<Asignatura> asignaturas) throws OperationNotSupportedException {

        if (asignaturas == null || asignaturas.size() == 0) {
            throw new OperationNotSupportedException("No hay asignaturas disponibles.");

        }
        ArrayList<Asignatura> asignaturasMatricula = new ArrayList<>();
        int opcion;
        //Comprobar si la asignatura ya existe
        for (int i = 0; i < asignaturasMatricula.size(); i++) {
            System.out.println("Introduzca el codigo de la asignatura que desea matricular.");
            asignaturasMatricula.get(i) = leerAsignatura(null);
           /* while (asignaturaYaMatriculada(asignaturasMatricula, asignaturasMatricula[i])) {
                System.out.println("La asignatura ya esta matriculada");
                asignaturasMatricula[i] = leerAsignatura(null);
            }
        }

        do {
            mostrarAsignaturas(asignaturas);
            Asignatura asignatura = getAsignaturaPorCodigo();
            //asignatura= Asignaturas.buscar(asignatura);

            int indice = -1;
            boolean encontrado = false;
            for (int i = 0; i < asignaturasMatricula.size() && !encontrado; i++) {
                if (asignaturasMatricula.get(i) == null||asignaturasMatricula.get(i).equals(asignatura)) {
                    indice = i;
                    encontrado = true;
                }
            }
            if (indice != -1) {
                asignaturasMatricula[indice] = new Asignatura(asignatura);
                if (asignaturaYaMatriculada(asignaturasMatricula, asignatura)) {
                    System.out.println("La asignatura ya esta añadida");
                    System.out.print("¿quieres añadir otra asignatura? (n/s): ");
                    System.out.println("0.- No \n1.- Si");
                    opcion = Entrada.entero();
                }else {
                    throw new OperationNotSupportedException("ERROR: La asignatura ya esta matriculada.");
                }
            }else {
                throw new OperationNotSupportedException("ERROR: No se aceptan mas asignaturas.");
            }
        }while (opcion == 1);


        return asignaturasMatricula;
    }*/
    public static ArrayList<Asignatura> elegirAsignaturasMatricula(ArrayList<Asignatura> asignaturas)
            throws OperationNotSupportedException {
        if (asignaturas == null || asignaturas.size() == 0) {
            throw new IllegalArgumentException("ERROR: No hay asignaturas disponibles para seleccionar.");
        }

        ArrayList<Asignatura> asignaturasMatricula = new ArrayList<>();
        int opcion = 0;

        do {
            mostrarAsignaturas(asignaturas); // Muestra las asignaturas disponibles

            // Obtener la asignatura por código
            System.out.print("Introduzca el código de la asignatura: ");
            System.out.print("debe ser un codigo de 4 digitos: ");
            String codigo = Entrada.cadena();
            Asignatura asignatura = null;

            // Validar si el código corresponde a una asignatura válida
            for (Asignatura a : asignaturas) {
                if (a != null && a.getCodigo().equals(codigo)) {
                    asignatura = a;
                    break;
                }
            }

            if (asignatura == null) {
                System.out.println("ERROR: La asignatura seleccionada no es válida.");
                continue; // Volver a solicitar la asignatura
            }

            // Verificar si ya está matriculada
            if (asignaturaYaMatriculada(asignaturasMatricula, asignatura)) {
                System.out.println("La asignatura ya está seleccionada.");
            } else {
                // Añadir asignatura al array
                asignaturasMatricula.add(asignatura);
                System.out.println("Asignatura añadida correctamente.");
            }

            // Preguntar si desea añadir otra asignatura
            System.out.print("¿Desea añadir otra asignatura? (0 = No, 1 = Sí): ");
            opcion = Entrada.entero();
        } while (opcion == 1 && asignaturasMatricula.size() < asignaturas.size());

        // Crear un nuevo array con las asignaturas seleccionadas
        return asignaturasMatricula;
    }


    //comparar si la asignatura ya esta matriculada
    private static boolean asignaturaYaMatriculada(ArrayList<Asignatura> asignaturasMatricula, Asignatura asignatura){

        if (asignaturasMatricula != null) {
            for (int i = 0; i < asignaturasMatricula.size(); i++) {
                if (asignaturasMatricula.get(i)!= null && asignaturasMatricula.get(i).equals(asignatura)) {
                    return true;
                }
            }
        }
       return false;
    }
  //leer matricula
    public static Matricula leerMatricula (Alumno alumno, ArrayList<Asignatura> asignaturas) throws OperationNotSupportedException {

        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno de una matrícula no puede ser nulo.");
        }
        if (asignaturas == null || asignaturas.size() == 0) {
            throw new NullPointerException("ERROR: Las asignaturas de una matrícula no pueden ser nulas.");
        }

        int idMatricula;
        String cursoAcademico;
        LocalDate fechaMatriculacion;
        Matricula matricula;

        System.out.println("Introduzca el Id de la Matrícula.");
        idMatricula = Entrada.entero();

        System.out.println("Introduzca el Curso Académico.");
        System.out.println("El curso academico tiene que tener el formato 24-25");
        cursoAcademico = Entrada.cadena();

        String mensaje= "Introduzca la Fecha de matriculación.";

        System.out.println("La fecha de matriculación como maximo puede ser de 15 días anterior al día actual");
        fechaMatriculacion = leerFecha(mensaje);

        matricula = new Matricula (idMatricula, cursoAcademico, fechaMatriculacion, alumno, asignaturas);

        //return new Matricula (matricula);
        return matricula;
    }

    public static Matricula getMatriculaPorIdentificador() throws OperationNotSupportedException {
        int idMatricula;
        String cursoAcademico = "24-25";
        LocalDate fechaMatriculacion = LocalDate.now();
        Alumno alumno = new Alumno("ficticio apellidoficticio","444444444", "ficticio@ficticio.es", "00000000T",
                LocalDate.of(2003, 8, 21));

        System.out.println("Introduzca el id de la Matrícula.");
        idMatricula = Entrada.entero();

        Matricula matricula = new Matricula(idMatricula, cursoAcademico, fechaMatriculacion, alumno, new ArrayList<>());

        return matricula;
    }

}
