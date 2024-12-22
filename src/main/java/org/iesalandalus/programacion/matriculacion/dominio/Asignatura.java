package org.iesalandalus.programacion.matriculacion.dominio;

import java.util.Arrays;
import java.util.Objects;

public class Asignatura {
  public static final int MAX_NUM_HORAS_ANUALES=20;
  public static final int MAX_NUM_HORAS_DESDOBLES=10;
  private static final String ER_CODIGO="^\\d{4}$";
  private String codigo;
  private String nombre;
  private int horasAnuales;
  private Curso curso;
  private int horasDesdoble;
  private EspecialidadProfesorado especialidadProfesorado;
  private CicloFormativo cicloFormativo;



  public Asignatura(String codigo, String nombre, int horasAnuales, Curso curso, int horasDesdoble, EspecialidadProfesorado especialidadProfesorado, CicloFormativo cicloFormativo) {
    setCodigo(codigo);
    setNombre(nombre);
    setHorasAnuales(horasAnuales);
    setCurso(curso);
    setHorasDesdoble(horasDesdoble);
    setEspecialidadProfesorado(especialidadProfesorado);
    setCicloFormativo(cicloFormativo);
  }
  public Asignatura(Asignatura asignatura) {
      setCodigo(codigo);
      setNombre(nombre);
      setHorasAnuales(horasAnuales);
      setCurso(curso);
      setHorasDesdoble(horasDesdoble);
      setEspecialidadProfesorado(especialidadProfesorado);
      setCicloFormativo(cicloFormativo);
  }


    public String getCodigo() {
        return codigo;
    }

    private void setCodigo(String codigo) {
        if (codigo == null) {
            throw new NullPointerException("ERROR: El código de una asignatura no puede ser nulo.");
        }else if (codigo.isBlank()) {
            throw new IllegalArgumentException("ERROR: El código de una asignatura no puede estar vacío.");
        }else if (codigo.length() != 4) {
            throw new IllegalArgumentException("ERROR: El código de la asignatura debe ser de 4 caracteres.");
        }
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHorasAnuales() {
        return horasAnuales;
    }

    public void setHorasAnuales(int horasAnuales) {
        this.horasAnuales = horasAnuales;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getHorasDesdoble() {
        return horasDesdoble;
    }

    public void setHorasDesdoble(int horasDesdoble) {
        this.horasDesdoble = horasDesdoble;
    }

    public EspecialidadProfesorado getEspecialidadProfesorado() {
        return especialidadProfesorado;
    }

    public void setEspecialidadProfesorado(EspecialidadProfesorado especialidadProfesorado) {
        this.especialidadProfesorado = especialidadProfesorado;
    }

    public CicloFormativo getCicloFormativo() {
        return cicloFormativo;
    }

    public void setCicloFormativo(CicloFormativo cicloFormativo) {
        this.cicloFormativo = cicloFormativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asignatura that = (Asignatura) o;
        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    @Override
    public String toString() {
        return "Asignatura{" +
                "MAX_NUM_HORAS_ANUALES=" + MAX_NUM_HORAS_ANUALES +
                ", MAX_NUM_HORAS_DESDOBLES=" + MAX_NUM_HORAS_DESDOBLES +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", horasAnuales=" + horasAnuales +
                ", curso=" + curso +
                ", horasDesdoble=" + horasDesdoble +
                ", especialidadProfesorado=" + especialidadProfesorado +
                ", cicloFormativo=" + cicloFormativo +
                '}';
    }
    public String imprimir() {
        return codigo + " " + nombre + " " + horasAnuales + " " + curso + " " + horasDesdoble + " " + especialidadProfesorado + " " + cicloFormativo;
    }
}
