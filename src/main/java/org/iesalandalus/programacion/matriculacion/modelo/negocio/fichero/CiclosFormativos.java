package org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.ICiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero.utilidades.UtilidadesXML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Integer.parseInt;


public class CiclosFormativos implements ICiclosFormativos {
    private static final String RUTA_FICHERO="datos/CiclosFormativos.xml";
    private static final String RAIZ="CiclosFormativos";
    private static final String CICLOFORMATIVO="CicloFormativo";
    private static final String CODIGO="Codigo";
    private static final String NOMBRE="Nombre";
    private static final String FAMILIAPROFESIONAL="FamiliaProfesional";
    private static final String HORAS="Horas";
    private static final String GRADO="Grado";
    private static final String TIPO="Tipo";
    private static final String NUMANIOS="NumAnios";
    private static final String MODALIDAD="Modalidad";
    private static final String NUMEDICIONES="NumEdiciones";
    private static final String NOMBREGRADO="NombreGrado";


    private ArrayList<CicloFormativo> coleccionCiclosFormativos;
    private static CiclosFormativos instancia=null;

    public static CiclosFormativos getInstancia() {
        if (instancia==null) {
            instancia=new CiclosFormativos();
        }
        return instancia;
    }

    public CiclosFormativos() {
        this.coleccionCiclosFormativos = new ArrayList<>();
        comenzar();
    }

    @Override
    public void comenzar() {
        leerXML();
    }

    private void leerXML() {
        Document document;
        NodeList ciclosFormativos;
        Node cicloNodo;

        try{
            document = UtilidadesXML.xmlToDom(RUTA_FICHERO);
            if(document!=null) {
                document = UtilidadesXML.crearDomVacio(RAIZ);
            }
            document.getDocumentElement().normalize();
            ciclosFormativos = document.getElementsByTagName(CICLOFORMATIVO);

            for(int i=0;i<ciclosFormativos.getLength();i++) {
                cicloNodo = ciclosFormativos.item(i);

                if(cicloNodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) cicloNodo;
                    CicloFormativo c = elementToCicloFormativo(elemento);
                    this.coleccionCiclosFormativos.add(c);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CicloFormativo elementToCicloFormativo(Element elemento) {

        String codigo = elemento.getAttribute(CODIGO);
        String nombre = elemento.getElementsByTagName(NOMBRE).item(0).getTextContent();
        String familiaProfesional = elemento.getElementsByTagName(FAMILIAPROFESIONAL).item(0).getTextContent();
        String horas = elemento.getElementsByTagName(HORAS).item(0).getTextContent();
        String nombreGrado = elemento.getElementsByTagName(NOMBREGRADO).item(0).getTextContent();
        String numAnios = elemento.getElementsByTagName(NUMANIOS).item(0).getTextContent();

        Grado grado;



        CicloFormativo c = null;
        Element elementoGrado = (Element) elemento.getElementsByTagName(GRADO).item(0);
        String tipo = elementoGrado.getAttribute(TIPO);

        if(tipo.equals("Grado D")) {
            String modalidad = elemento.getElementsByTagName(MODALIDAD).item(0).getTextContent();
            grado = new GradoD(nombreGrado, parseInt(numAnios), Modalidad.valueOf(modalidad.toUpperCase()));

            c = new CicloFormativo(parseInt(codigo), familiaProfesional, grado, nombre, parseInt(horas));

        }
        if(tipo.equals("Grado E")) {
            String numEdiciones = elemento.getElementsByTagName(NUMEDICIONES).item(0).getTextContent();
            grado = new GradoE(nombreGrado, parseInt(numAnios), parseInt(numEdiciones));

            c = new CicloFormativo(parseInt(codigo), familiaProfesional, grado, nombre, parseInt(horas));

        }
        return c;


    }

    @Override
    public void terminar() {
        escribirXML();
    }

    private void escribirXML() {
        Document documento = UtilidadesXML.crearDomVacio(RAIZ);

        for(CicloFormativo c : coleccionCiclosFormativos) {
            Element elemento = CiclosFormativosToElement(documento, c);
            documento.getDocumentElement().appendChild(elemento);
        }
        UtilidadesXML.domToXml(documento, RUTA_FICHERO);
    }

    private Element CiclosFormativosToElement(Document documento, CicloFormativo c) {


        Element elementoCiclo = documento.createElement(CICLOFORMATIVO);
        elementoCiclo.setAttribute(CODIGO, String.valueOf(c.getCodigo()));

        Element elementoNombre = documento.createElement(NOMBRE);
        elementoNombre.setTextContent(c.getNombre());
        elementoCiclo.appendChild(elementoNombre);

        Element elementoFamiliaProfesional = documento.createElement(FAMILIAPROFESIONAL);
        elementoFamiliaProfesional.setTextContent(c.getFamiliaProfesional());
        elementoCiclo.appendChild(elementoFamiliaProfesional);

        Element elementoHoras = documento.createElement(HORAS);
        elementoHoras.setTextContent(String.valueOf(c.getHoras()));
        elementoCiclo.appendChild(elementoHoras);


        Grado grado = c.getGrado();
        Element gradoElemento = documento.createElement(GRADO);
        gradoElemento.setAttribute(TIPO, grado.getClass().getSimpleName());

        Element elementoNombreGrado = documento.createElement(NOMBREGRADO);
        elementoNombreGrado.setTextContent(grado.getNombre());
        gradoElemento.appendChild(elementoNombreGrado);

        Element elementoNumAnios = documento.createElement(NUMANIOS);
        elementoNumAnios.setTextContent(String.valueOf(grado.getNumAnios()));
        gradoElemento.appendChild(elementoNumAnios);

        if(grado instanceof GradoD) {
            Element elementoModalidad = documento.createElement(MODALIDAD);
            elementoModalidad.setTextContent(((GradoD) grado).getModalidad().toString());
            gradoElemento.appendChild(elementoModalidad);
        }
        if(grado instanceof GradoE) {
            Element elementoNumEdiciones = documento.createElement(NUMEDICIONES);
            elementoNumEdiciones.setTextContent(String.valueOf(((GradoE) grado).getNumEdiciones()));
            gradoElemento.appendChild(elementoNumEdiciones);
        }

        elementoCiclo.appendChild(gradoElemento);
        return elementoCiclo;

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
            if (indice == -1) {
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
        this.coleccionCiclosFormativos.remove(indice);
    }





    public int getTamano() {
        return this.coleccionCiclosFormativos.size();
    }

}
