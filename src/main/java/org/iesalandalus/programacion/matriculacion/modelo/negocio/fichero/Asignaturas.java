package org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero;


import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAsignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero.utilidades.UtilidadesXML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class Asignaturas implements IAsignaturas {

    private static final String RUTA_FICHERO="datos/asignaturas.xml";
    private static final String RAIZ="Asignaturas";
    private static final String ASIGNATURA="Asignatura";
    private static final String CODIGO="Codigo";
    private static final String CURSO="Curso";
    private static final String NOMBRE="Nombre";
    private static final String ESPECIALIDADPROFESORADO="EspecialidadProfesorado";
    private static final String CICLOFORMATIVO="CicloFormativo";
    private static final String HORAS="Horas";
    private static final String HORASANUALES="HorasAnuales";
    private static final String HORASDESDOBLE="HorasDesdoble";

    private ArrayList<Asignatura> coleccionAsignaturas;
    private static Asignaturas instancia=null;

    public Asignaturas(){

        coleccionAsignaturas = new ArrayList<>();
        comenzar();
    }


    @Override
    public void comenzar() {
       leerXML(); 

    }
    public static Asignaturas getInstancia() {
        if (instancia==null) {
            instancia=new Asignaturas();
        }
        return instancia;
    }

    private void leerXML() {
        Document document;
        NodeList asignaturas;
        Node asignaturaNodo;

        try{
            document = UtilidadesXML.xmlToDom(RUTA_FICHERO);
            if(document!=null) {
                document = UtilidadesXML.crearDomVacio(RAIZ);
            }
            document.getDocumentElement().normalize();
            asignaturas = document.getElementsByTagName(ASIGNATURA);

            for(int i=0;i<asignaturas.getLength();i++) {
                asignaturaNodo = asignaturas.item(i);

                if(asignaturaNodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) asignaturaNodo;
                    Asignatura as = elementToAsignatura(elemento);
                    this.coleccionAsignaturas.add(as);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Asignatura elementToAsignatura(Element elemento) {
        String codigo = elemento.getAttribute(CODIGO);
        String nombre = elemento.getElementsByTagName(NOMBRE).item(0).getTextContent();
        String curso = elemento.getElementsByTagName(CURSO).item(0).getTextContent();
        String especialidadProfesorado = elemento.getElementsByTagName(ESPECIALIDADPROFESORADO).item(0).getTextContent();
        String cicloFormativo = elemento.getElementsByTagName(CICLOFORMATIVO).item(0).getTextContent();
        //String horas = elemento.getElementsByTagName(HORAS).item(0).getTextContent();
        String horasAnuales = elemento.getElementsByTagName(HORASANUALES).item(0).getTextContent();
        String horasDesdoble = elemento.getElementsByTagName(HORASDESDOBLE).item(0).getTextContent();
        CiclosFormativos cf = CiclosFormativos.getInstancia();
        CicloFormativo ciclo = cf.buscar(new CicloFormativo(Integer.parseInt(codigo), "ficticio", new GradoE("Grado E", 1, 2), "ficticio",300));

        return new Asignatura(codigo,nombre,Integer.parseInt(horasAnuales),Curso.valueOf(curso),Integer.parseInt(horasDesdoble), EspecialidadProfesorado.valueOf(especialidadProfesorado),ciclo);
    }

    @Override
    public void terminar() {
        escribirXML();
    }

    private void escribirXML() {
        Document documento = UtilidadesXML.crearDomVacio(RAIZ);

        for(Asignatura as : coleccionAsignaturas) {
            Element elemento = AsignaturasToElement(documento, as);
            documento.getDocumentElement().appendChild(elemento);
        }
        UtilidadesXML.domToXml(documento, RUTA_FICHERO);
    }

    private Element AsignaturasToElement(Document documento, Asignatura as) {
        Element elementoAsignatura = documento.createElement(ASIGNATURA);
        elementoAsignatura.setAttribute(CODIGO, as.getCodigo());

        Element elementoNombre = documento.createElement(NOMBRE);
        elementoNombre.setTextContent(as.getNombre());
        elementoAsignatura.appendChild(elementoNombre);

        Curso curso = as.getCurso();
        Element cursoElemento = documento.createElement(CURSO);
        cursoElemento.setTextContent(curso.toString());
        elementoAsignatura.appendChild(cursoElemento);

        EspecialidadProfesorado especialidadProfesorado = as.getEspecialidadProfesorado();
        Element especialidadProfesoradoElemento = documento.createElement(ESPECIALIDADPROFESORADO);
        especialidadProfesoradoElemento.setTextContent(especialidadProfesorado.toString());
        elementoAsignatura.appendChild(especialidadProfesoradoElemento);

        CicloFormativo cicloFormativo = as.getCicloFormativo();
        Element cicloFormativoElemento = documento.createElement(CICLOFORMATIVO);
        cicloFormativoElemento.setTextContent(cicloFormativo.toString());
        elementoAsignatura.appendChild(cicloFormativoElemento);

        Element horas = documento.createElement(HORAS);

        Element horasAnuales = documento.createElement(HORASANUALES);
        horasAnuales.setTextContent(String.valueOf(as.getHorasAnuales()));
        horas.appendChild(horasAnuales);

        Element horasDesdoble = documento.createElement(HORASDESDOBLE);
        horasDesdoble.setTextContent(String.valueOf(as.getHorasDesdoble()));
        horas.appendChild(horasDesdoble);

        elementoAsignatura.appendChild(horas);
        return elementoAsignatura;
    }

    //copia profunda
    public ArrayList<Asignatura> get() {
        return copiaProfundaAsignaturas();
    }

    private ArrayList<Asignatura> copiaProfundaAsignaturas() {
        ArrayList<Asignatura> copiaAsignaturas = new ArrayList<>();
        for (Asignatura asignatura : coleccionAsignaturas) {
            copiaAsignaturas.add(new Asignatura(asignatura));
        }
        return copiaAsignaturas;
    }

    //Insertar Asignatura
    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        Objects.requireNonNull(asignatura, "ERROR: No se puede insertar una asignatura nula.");

        int indice = this.coleccionAsignaturas.indexOf(asignatura);
        if (indice==-1) {
            this.coleccionAsignaturas.add(new Asignatura(asignatura));
        }
        else {
            throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese código.");
        }
    }

    //buscar Asignatura
    public Asignatura buscar(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede buscar una asignatura nula.");
        }
        int indice = this.coleccionAsignaturas.indexOf(asignatura);
        if (indice==-1) {
            return null;
        } else {
            return new Asignatura(this.coleccionAsignaturas.get(indice));
        }
    }
    //borrar Asignatura
    public void borrar (Asignatura asignatura) throws OperationNotSupportedException {
        Objects.requireNonNull(asignatura,"ERROR: No se puede borrar una asignatura nula.");

        int indice = this.coleccionAsignaturas.indexOf(asignatura);
        if (indice==-1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna asignatura como la indicada.");
        }
        this.coleccionAsignaturas.remove(indice);
    }

    public int getTamano() {
        return this.coleccionAsignaturas.size();
    }

}
