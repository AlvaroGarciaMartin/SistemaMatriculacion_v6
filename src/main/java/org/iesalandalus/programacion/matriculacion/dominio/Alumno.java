package org.iesalandalus.programacion.matriculacion.dominio;

import java.time.LocalDate;

public class Alumno {

  private String ER_TELEFONO;
  private  String ER_CORREO;
  private String ER_DNI;
  public String FORMATO_FECHA;
  private String ER_NIA;
  private int MIN_EDAD_ALUMNO;
  private String nombre;
  private String telefono;
  private String correo;
  private String dni;
  private LocalDate fechaNacimiento;
  private String nia;

  public Alumno(){
    setNombre(nombre);
    setTelefono(telefono);
    setCorreo(correo);
    setDni(dni);
    setFechaNacimiento(fechaNacimiento);
    setNia(nia);
  }
  //metodo para eliminar los espacios en blanco
  private void formateaNombre(){
  String nombreNormalizado=nombre.replaceAll("\\s+", " ");
  }


  public String getNia() {

    return nia;
  }

  public void setNia(String nia) {

    this.nia = nia;
  }

  public String getNombre() {

    return nombre;
  }

  public void setNombre(String nombre) {

    this.nombre = nombre;
  }

  public String getTelefono() {

    return telefono;
  }

  public void setTelefono(String telefono) {

    this.telefono = telefono;
  }

  public String getCorreo() {

    return correo;
  }

  public void setCorreo(String correo) {

    this.correo = correo;
  }

  public String getDni() {

    return dni;
  }

  public void setDni(String dni) {

    this.dni = dni;
  }

  public LocalDate getFechaNacimiento() {

    return fechaNacimiento;
  }

  public void setFechaNacimiento(LocalDate fechaNacimiento) {

    this.fechaNacimiento = fechaNacimiento;
  }


}
