package org.iesalandalus.programacion.matriculacion.vista;

public enum Opcion {
    SALIR("Salir"),
    INSERTAR_ALUMNO("Insertar Alumno"),
    BUSCAR_ALUMNO("Buscar Alumno"),
    BORRAR_ALUMNO("Borrar Alumno"),
    MOSTRAR_ALUMNOS("Mostrar Alumnos"),
    INSERTAR_CICLO_FORMATIVO("Insertar ciclo formativo"),
    BUSCAR_CICLO_FORMATIVO("Buscar Ciclo Formativo"),
    BORRAR_CICLO_FORMATIVO("Borrar Ciclo Formativo"),
    MOSTRAR_CICLOS_FORMATIVOS("Mostrar Ciclos Formativos"),
    INSERTAR_ASIGNATURA("Insertar Asignatura"),
    BUSCAR_ASIGNATURA("Buscar Asignatura"),
    BORRAR_ASIGNATURA("Borrar Asignatura"),
    MOSTRAR_ASIGNATURAS("Mostrar Asignaturas"),
    INSERTAR_MATRICULA("Insertar Matricula"),
    BUSCAR_MATRICULA("Buscar Matricula"),
    ANULAR_MATRICULA("Anular Matricula"),
    MOSTRAR_MATRICULAS("Mostrar Matriculas"),
    MOSTRAR_MATRICULAS_ALUMNO("Mostrar Matriculas Alumno"),
    MOSTRAR_MATRICULAS_CICLO_FORMATIVO("Mostrar Matriculas Ciclo Formativo"),
    MOSTRAR_MATRICULAS_CURSO_ACADEMICO("Mostrar Matriculas Curso Academico");


    private String cadenaAMostrar;

    private Opcion(String cadenaAMostrar)
    {
        this.cadenaAMostrar=cadenaAMostrar;
    }

    @Override
    public String toString() {
        return ordinal()+".-"+cadenaAMostrar;
    }
}
