package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public abstract class Grado {
    protected String nombre;
    protected String iniciales;
    protected int numAnios;
    public Grado (String nombre){
        setNombre(nombre);
    }

    public String getNombre(){
        return nombre;
    }

    protected void setNombre(String nombre){
        if (nombre == null){
            throw new NullPointerException("ERROR: El nombre de un grado no puede ser nulo.");
        }
        if (nombre.isBlank()){
            throw new IllegalArgumentException("ERROR: El nombre de un grado no puede estar vacío.");
        }
        this.nombre = nombre;
        setIniciales();
    }


    private void setIniciales(){
        String[] palabras = this.nombre.split("[ ]+");
        String iniciales = "";
        for (String palabra : palabras){
            iniciales += palabra.charAt(0);
        }
        this.iniciales = iniciales.toString().toUpperCase();
    }

    public int getNumAnios() {
        return numAnios;
    }

    public abstract void setNumAnios(int numAnios);

    public String toString(){
        return "(" + iniciales + ") - " + nombre;
    }
}
