package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public class GradoE extends Grado{

    private int numEdiciones;

    public GradoE(String nombre, int numAnios, int numEdiciones) {
        super(nombre);
        setNumAnios(numAnios);
        setNumEdiciones(numEdiciones);
    }

    public int getNumEdiciones(){
        return numEdiciones;
    }
    public void setNumEdiciones(int numEdiciones){
        if(numEdiciones <=0){
            throw new IllegalArgumentException("ERROR: el número de ediciones de un grado E no debe ser menor que 1.");
        }
        this.numEdiciones = numEdiciones;
    }

    @Override
    public void setNumAnios(int numAnios){
        if (numAnios !=1){
            throw new IllegalArgumentException("ERROR: El número de años de un grado E debe ser 1.");
        }
        this.numAnios= numAnios;
    }
    @Override
    public String toString(){
        return super.toString() + " - "+ numAnios + "año(s) - " + numEdiciones + "ediciones";
    }
}
