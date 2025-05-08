package org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAlumnos;
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


public class Alumnos implements IAlumnos {

    private static final String RUTA_FICHERO="datos/alumnos.xml";
    private static final String RAIZ="Alumnos";
    private static final String ALUMNO="Alumno";
    private static final String DNI="Dni";
    private static final String NOMBRE="Nombre";
    private static final String TELEFONO="Telefono";
    private static final String CORREO="Correo";
    private static final String FECHA_NACIMIENTO="FechaNacimiento";



    private ArrayList<Alumno> coleccionAlumnos;
    private static Alumnos instancia=null;





    public Alumnos() {
        this.coleccionAlumnos = new ArrayList<>();
        comenzar();
    }

    public static Alumnos getInstancia() {
        if (instancia==null) {
            instancia=new Alumnos();
        }
        return instancia;
    }




    @Override
    public void comenzar() {
        leerXML();
    }



    @Override
    public void terminar() {
        escribirXML();

    }



    public ArrayList<Alumno> get() {
        return copiaProfundaAlumnos();
    }

    private ArrayList<Alumno> copiaProfundaAlumnos() {

        //Alumno[] copiaAlumnos = new Alumno[capacidad];
        ArrayList<Alumno> copiaAlumnos = new ArrayList<>();
        for (Alumno alumno:coleccionAlumnos) {
            copiaAlumnos.add(new Alumno(alumno));
        }
        return copiaAlumnos;
    }


    //Insertar Alumno
    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
        }

        int indice = this.coleccionAlumnos.indexOf(alumno);

        if (indice==-1) {
            this.coleccionAlumnos.add(new Alumno(alumno));
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
        }
    }
    //Buscar Alumno
    public Alumno buscar(Alumno alumno){
       Objects.requireNonNull(alumno, "ERROR: No se puede buscar un alumno nulo.");
        int indice = this.coleccionAlumnos.indexOf(alumno);
        if (indice==-1) {
            return null;
        }
        return new Alumno(this.coleccionAlumnos.get(indice));
    }



    //borrar alumno
    public void borrar (Alumno alumno) throws OperationNotSupportedException {
        // Objects.requireNonNull(alumno,"ERROR: No se puede borrar un alumno nulo.");
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
        }
        int indice = this.coleccionAlumnos.indexOf(alumno);
        if (indice==-1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
        }

            this.coleccionAlumnos.remove(indice);

    }




    public int getTamano() {

        return this.coleccionAlumnos.size();
    }
    private void leerXML() {
        Document document;
        NodeList alumnos;
        Node alumnoNodo;

        try{
          document = UtilidadesXML.xmlToDom(RUTA_FICHERO);
          if(document!=null) {
              document = UtilidadesXML.crearDomVacio(RAIZ);
          }
          document.getDocumentElement().normalize();
          alumnos = document.getElementsByTagName(ALUMNO);

          for(int i=0;i<alumnos.getLength();i++) {
              alumnoNodo = alumnos.item(i);

              if(alumnoNodo.getNodeType() == Node.ELEMENT_NODE) {
                  Element elemento = (Element) alumnoNodo;
                  Alumno a = elementToAlumno(elemento);
                  this.coleccionAlumnos.add(a);
              }
          }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Alumno elementToAlumno(Element elemento) {
        if (elemento == null) return null;

        String dni = elemento.getAttribute(DNI);
        String nombre = elemento.getElementsByTagName(NOMBRE).item(0).getTextContent();
        String telefono = elemento.getElementsByTagName(TELEFONO).item(0).getTextContent();
        String correo = elemento.getElementsByTagName(CORREO).item(0).getTextContent();
        String fechaNacimiento = elemento.getElementsByTagName(FECHA_NACIMIENTO).item(0).getTextContent();
        //convertir fechaNacimiento a LocalDate
        LocalDate fecha = LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA));

        Alumno a = new Alumno(nombre,telefono,correo,dni,fecha);

        return a;
    }

    private void escribirXML() {
        Document documento = UtilidadesXML.crearDomVacio(RAIZ);

        for(Alumno a : coleccionAlumnos) {
            Element elemento = alumnoToElement(documento, a);
            documento.getDocumentElement().appendChild(elemento);
        }
        UtilidadesXML.domToXml(documento, RUTA_FICHERO);
    }

    private Element alumnoToElement(Document documento, Alumno a) {
        if(documento == null || a == null) return null;

        Element elementoAlumno = documento.createElement(ALUMNO);
        elementoAlumno.setAttribute(DNI, a.getDni());

        Element elementoNombre = documento.createElement(NOMBRE);
        elementoNombre.setTextContent(a.getNombre());
        elementoAlumno.appendChild(elementoNombre);

        Element elementoTelefono = documento.createElement(TELEFONO);
        elementoTelefono.setTextContent(a.getTelefono());
        elementoAlumno.appendChild(elementoTelefono);

        Element elementoCorreo = documento.createElement(CORREO);
        elementoCorreo.setTextContent(a.getCorreo());
        elementoAlumno.appendChild(elementoCorreo);

        Element elementoFechaNacimiento = documento.createElement(FECHA_NACIMIENTO);
        elementoFechaNacimiento.setTextContent(a.getFechaNacimiento().format(DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA)));
        elementoAlumno.appendChild(elementoFechaNacimiento);

        return elementoAlumno;
    }
}
