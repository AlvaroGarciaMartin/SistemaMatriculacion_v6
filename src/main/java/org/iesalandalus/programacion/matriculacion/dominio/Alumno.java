package org.iesalandalus.programacion.matriculacion.dominio;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.regex.*;

public class Alumno {

  private String ER_TELEFONO;
  private  String ER_CORREO;
  private String ER_DNI;
  public String FORMATO_FECHA;
  private String ER_NIA;
  private int MIN_EDAD_ALUMNADO = 16;
  private String nombre;
  private String telefono;
  private String correo;
  private String dni;
  private LocalDate fechaNacimiento;
  private String nia;

//constructor Alumno CON PARAMETROS
  public Alumno(String nombre,String dni,String correo,String telefono,LocalDate fechaNacimiento){
    setNombre(nombre);
    setDni(dni);
    setCorreo(correo);
    setTelefono(telefono);
    setFechaNacimiento(fechaNacimiento);
  }
  //constructor copia de Alumno
  public Alumno(Alumno alumno){
    setNombre(nombre);
    setDni(dni);
    setCorreo(correo);
    setTelefono(telefono);
    setFechaNacimiento(fechaNacimiento);
  }


  //metodo para eliminar los espacios en blanco
  private void formateaNombre(){
    String [] nombreNormalizado=nombre.split(" ");

    // StringBuilder para construir el nombre formateado.
    StringBuilder nombreFormateado = new StringBuilder();

    // Recorrer cada palabra.
    for (String palabra : nombreNormalizado) {
      // Convertir la primera letra a mayúsculas y el resto a minúsculas.
      if (palabra.length() > 0) {
        String palabraFormateada = palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase();
        nombreFormateado.append(palabraFormateada).append(" ");
      }
    }

    // Eliminar el último espacio adicional.
    setNombre(nombreFormateado.toString().trim());


  }
  private boolean comprobarLetraDni() {
    String udni=getDni();
    boolean resultado = false;
    int numero;
    char letra;
    Pattern patron=Pattern.compile("^(\\d{8})([A-Za-z]{1})$");
    Matcher comparador=patron.matcher(udni);
    char[] LETRAS_DNI = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
    if (comparador.matches()) {
      numero = Integer.parseInt(comparador.group(1));
      letra = comparador.group(2).charAt(0);

    } else {

      return false;

    }

      int numeroDivido=numero/23;
      int numeroMultiplicado=numeroDivido*23;
      int posicionLetra=numero-numeroMultiplicado;

      if (letra==LETRAS_DNI[posicionLetra]) {
        resultado=true;

      }


    return resultado;
  }
  private String[] getIniciales() {
      String [] iniciales = nombre.split(" ");


    // StringBuilder para construir el nombre formateado.
    StringBuilder inicialesFormateado = new StringBuilder();

    // Recorrer cada palabra.
    for (String inicial : iniciales) {
      // Convertir la primera letra a mayúsculas y el resto a minúsculas.
      if (inicial.length() > 0) {
        String palabraFormateada = inicial.substring(0, 1).toUpperCase();
        inicialesFormateado.append(palabraFormateada).append("");

      }
    }

      return inicialesFormateado.toString().split("");
  }

//geters y seters
  public String getNia() {
    String nNombre;
    String nDni;
    nNombre=nombre.substring(0,4);
    nDni=dni.substring(5,7);
    nia=nNombre+nDni;
    return nia;
  }

  public void setNia(String nia) {

    if (nia == null) {
      throw new NullPointerException("El nia no puede ser nulo");
    }  else if (!nia.substring(0,4).equals(nombre.substring(0,4).toLowerCase()) && !nia.substring(5,7).equals(dni.substring(5,7)) ) {
      throw new IllegalArgumentException("El nia no es correcto");
    }

    this.nia = nia;
  }

  public String getNombre() {

    return nombre;
  }

  public void setNombre(String nombre) {
    if (nombre == null) {
      throw new NullPointerException("El nombre no puede ser nulo");
    }
    this.nombre = nombre;
  }

  public String getTelefono() {

    return telefono;
  }

  public void setTelefono(String telefono) {
    if (telefono == null) {
      throw new NullPointerException("El telefono no puede ser nulo");
    } else if (telefono.length()!=9) {
      throw new IllegalArgumentException("El telefono no es correcto");

    }
    this.telefono = telefono;
  }

  public String getCorreo() {

    return correo;
  }

  public void setCorreo(String correo) {
    if (correo == null) {
      throw new NullPointerException("El correo no puede ser nulo");
    } else if (!correo.contains("@")) {
      throw new IllegalArgumentException("El correo no contiene '@': " + correo);
    } else if (!correo.contains(".")) {
      throw new IllegalArgumentException("El correo no contiene dominio adecuado ");
    } else if (!(correo.endsWith(".com") || correo.endsWith(".es"))) {
      throw new IllegalArgumentException("El correo no tiene dominio adecuado");
    }
    this.correo = correo;
  }

  public String getDni() {

    return dni;
  }

  public void setDni(String dni) {
    if (dni == null) {
      throw new NullPointerException("El dni no puede ser nulo");
    } else if (comprobarLetraDni()==false) {
      throw new IllegalArgumentException("El dni no es correcto");
    }
    this.dni = dni;
  }

  public LocalDate getFechaNacimiento() {

    return fechaNacimiento;
  }

  public void setFechaNacimiento(LocalDate fechaNacimiento) {
    if (fechaNacimiento.getYear() - LocalDate.now().getYear() < MIN_EDAD_ALUMNADO) {
      throw new IllegalArgumentException("El Alumno no puede tener menos de 16 años");
    }
    this.fechaNacimiento = fechaNacimiento;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Alumno alumno = (Alumno) o;
    return Objects.equals(ER_DNI, alumno.ER_DNI) && Objects.equals(dni, alumno.dni);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ER_DNI, dni);
  }
  @Override
  public String toString() {
    return "Alumno{" +
            "nombre='" + nombre + '\'' +
            ", telefono='" + telefono + '\'' +
            ", correo='" + correo + '\'' +
            ", dni='" + dni + '\'' +
            ", fechaNacimiento=" + fechaNacimiento +
            ", nia='" + nia + '\'' +
            '}';
  }

}
