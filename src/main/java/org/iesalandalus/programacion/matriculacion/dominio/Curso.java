package org.iesalandalus.programacion.matriculacion.dominio;

public enum Curso {
    PRIMERO("Primero"),SEGUNDO("Segundo");
    private String cadenaAMostrar;
    private Curso(String cadenaAMostrar)
    {
        this.cadenaAMostrar=cadenaAMostrar;
    }
    public String imprimir() {
        int digito=0;
        if (cadenaAMostrar == PRIMERO.cadenaAMostrar) {
            digito=0;
        } else {
            digito=1;
        }
        return digito+".-"+cadenaAMostrar;
    }
    //Crear cadenaAMostrar//

    @Override
    public String toString() {
        return "Curso seleccionado:" + cadenaAMostrar;
    }
}
