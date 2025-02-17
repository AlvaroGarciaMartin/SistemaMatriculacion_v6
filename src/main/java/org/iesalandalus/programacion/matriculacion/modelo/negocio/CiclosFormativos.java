package org.iesalandalus.programacion.matriculacion.modelo.negocio;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;
import java.util.*;


public class CiclosFormativos {
    private ArrayList<CicloFormativo> coleccionCiclosFormativos;

    public CiclosFormativos() {
        this.coleccionCiclosFormativos = new ArrayList<>();
    }

    //copia profunda
    public ArrayList<CicloFormativo> get() {
        return copiaProfundaCiclosFormativos();
    }

    private ArrayList<CicloFormativo> copiaProfundaCiclosFormativos() {
        ArrayList<CicloFormativo> copiaCiclosFormativos = new ArrayList<>();
        for (CicloFormativo cicloFormativo : coleccionCiclosFormativos) {
            copiaCiclosFormativos.add(new CicloFormativo(cicloFormativo));
        }
        return copiaCiclosFormativos;
    }

    //Insertar Ciclo
    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        Objects.requireNonNull(cicloFormativo, "ERROR: No se puede insertar un ciclo formativo nulo.");

        int indice =this.coleccionCiclosFormativos.indexOf(cicloFormativo);
        if (indice==-1) {
            coleccionCiclosFormativos.add(new CicloFormativo(cicloFormativo));
            return ;
        }
            throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese código.");

    }


    //buscar ciclo
    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede buscar un Ciclo Formativo nulo.");
        }

        int indice = this.coleccionCiclosFormativos.indexOf(cicloFormativo);
        if (indice==-1) {
            return null;
        } else {
            return new CicloFormativo(this.coleccionCiclosFormativos.get(indice));
        }
    }
    //borrar ciclo
    public void borrar (CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        //Objects.requireNonNull(cicloFormativo,"ERROR: No se puede borrar un ciclo formativo nulo.");
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede borrar un ciclo formativo nulo.");
        }
        int indice = this.coleccionCiclosFormativos.indexOf(cicloFormativo);
        if (indice==-1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún ciclo formativo como el indicado.");
        }

    }





    public int getTamano() {
        return this.coleccionCiclosFormativos.size();
    }

}
