package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum TiposGrado {

    GRADOD("Grado D"),
    GRADOE("Grado E");

    private String cadenaAMostrar;

    private TiposGrado(String cadenaAMostrar) {
            this.cadenaAMostrar=cadenaAMostrar;
    }
    public String imprimir() {
            int digito;
            if (cadenaAMostrar == GRADOD.cadenaAMostrar) {
                digito=1;
            } else if (cadenaAMostrar == GRADOE.cadenaAMostrar){
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
