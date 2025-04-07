package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;

public abstract class Vista {
    protected Controlador controlador;

    public Controlador getControlador() {
        return controlador;
    }

    public void setControlador(Controlador controlador) {
        if (controlador == null) {
            throw new NullPointerException("ERROR: El controlador no puede ser nulo");
        }
        this.controlador = controlador;
    }

    public abstract void comenzar();

    public abstract void terminar();

}
