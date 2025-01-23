package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum Grado {
    GDCFGB("GDCFGB"),GDCFGM("GDCFGM"),GDCFGS("GDCFGS");
    private String cadenaAMostrar;
    private Grado(String cadenaAMostrar)
    {
        this.cadenaAMostrar=cadenaAMostrar;
    }
    public String imprimir() {
        int digito=0;
        if (cadenaAMostrar == GDCFGB.cadenaAMostrar) {
            digito=1;
        } else if (cadenaAMostrar == GDCFGM.cadenaAMostrar){
            digito=2;
        } else {
            digito=3;
        }
        return digito+".-"+cadenaAMostrar;
    }
    //Crear cadenaAMostrar//

    @Override
    public String toString() {
        return "Grado seleccionado:"  + cadenaAMostrar;
    }
}
