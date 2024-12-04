package org.iesalandalus.programacion.matriculacion.dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CicloFormativo {
    private final int MAXIMO_NUMERO_HORAS=2000;
    private int codigo;
    private String familiaProfesional;
    private Grado grado;
    private String nombre;
    private int horas;
    public CicloFormativo(int codigo, String familiaProfesional, Grado grado, String nombre, int horas) {
        setCodigo(codigo);
        setFamiliaProfesional(familiaProfesional);
        setGrado(grado);
        setNombre(nombre);
        setHoras(horas);
    }
    public CicloFormativo(CicloFormativo cicloFormativo) {
        setCodigo(codigo);
        setFamiliaProfesional(familiaProfesional);
        setGrado(grado);
        setNombre(nombre);
        setHoras(horas);
    }



    public int getCodigo() {
        return codigo;
    }

    private void setCodigo(int codigo) {
        if (codigo < 0) {
            throw new IllegalArgumentException("ERROR: El código de un ciclo formativo debe ser mayor que cero.");
        }else if (codigo > 0 && codigo < 1000) {
            throw new IllegalArgumentException("ERROR: El código de un ciclo formativo debe ser un numero de 4 digitos.");
        }
        this.codigo = codigo;
    }

    public String getFamiliaProfesional() {
        return familiaProfesional;
    }

    public void setFamiliaProfesional(String familiaProfesional) {
        this.familiaProfesional = familiaProfesional;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        if (horas > MAXIMO_NUMERO_HORAS) {
            throw new IllegalArgumentException("ERROR: El numero de horas no puede ser mayor que " + MAXIMO_NUMERO_HORAS);
        }else if (horas < 0) {
            throw new IllegalArgumentException("ERROR: El numero de horas no puede ser menor que 0");
        }
        this.horas = horas;
    }
    public String imprimir() {
        return codigo + " " + familiaProfesional + " " + grado + " " + nombre + " " + horas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CicloFormativo that = (CicloFormativo) o;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    @Override
    public String toString() {
        return "CicloFormativo{" +
                "MAXIMO_NUMERO_HORAS=" + MAXIMO_NUMERO_HORAS +
                ", codigo=" + codigo +
                ", familiaProfesional='" + familiaProfesional + '\'' +
                ", grado=" + grado +
                ", nombre='" + nombre + '\'' +
                ", horas=" + horas +
                '}';
    }
}
