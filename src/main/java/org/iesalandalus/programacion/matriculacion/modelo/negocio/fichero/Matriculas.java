package org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IMatriculas;
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

public class Matriculas implements IMatriculas {

    private static final String RUTA_FICHERO="datos/matriculas.xml";
    private static final String RAIZ="Matriculas";
    private static final String MATRICULA="Matricula";
    private static final String ID="Id";
    private static final String ALUMNO="Alumno";
    private static final String CURSO="Curso";
    private static final String FECHAMATRICULACION="FechaMatriculacion";
    private static final String CICLOFORMATIVO="CicloFormativo";
    private static final String FECHAANULACION="FechaAnulacion";
    private static final String ASIGNATURAS="Asignaturas";
    private static final String ASIGNATURA="Asignatura";
    private static final String CODIGO="Codigo";

private ArrayList<Matricula> coleccionMatriculas;
    private static Matriculas instancia=null;

    public Matriculas() {

        coleccionMatriculas = new ArrayList<>();
        comenzar();

    }
    public static Matriculas getInstancia() {
        if (instancia==null) {
            instancia=new Matriculas();
        }
        return instancia;
    }

    @Override
    public void comenzar() {
        leerXML();
    }

    private void leerXML() {
        Document document;
        NodeList matriculas;
        Node matriculaNodo;

        try{
            document = UtilidadesXML.xmlToDom(RUTA_FICHERO);
            if(document!=null) {
                document = UtilidadesXML.crearDomVacio(RAIZ);
            }
            document.getDocumentElement().normalize();
            matriculas = document.getElementsByTagName(MATRICULA);

            for(int i=0;i<matriculas.getLength();i++) {
                matriculaNodo = matriculas.item(i);

                if(matriculaNodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) matriculaNodo;
                    Matricula m = elementToMatricula(elemento);
                    this.coleccionMatriculas.add(m);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Matricula elementToMatricula(Element elemento) throws OperationNotSupportedException {
        String codigo = elemento.getAttribute(CODIGO);
        String id = elemento.getAttribute(ID);
        String alumno = elemento.getAttribute(ALUMNO);
        String curso = elemento.getElementsByTagName(CURSO).item(0).getTextContent();
        String fechaMatriculacion = elemento.getElementsByTagName(FECHAMATRICULACION).item(0).getTextContent();
        String fechaAnulacion = elemento.getElementsByTagName(FECHAANULACION).item(0).getTextContent();
        String asignatura = elemento.getElementsByTagName(ASIGNATURA).item(0).getTextContent();

        //Asignaturas codigoAs = Asignaturas.getInstancia();
        //ArrayList<Asignatura> codigoAsignatura = new ArrayList<>;
        //codigoAsignatura = codigoAs.buscar(new Asignatura(codigo,"ficticio",0, Curso.PRIMERO,0, EspecialidadProfesorado.valueOf("informatica"), new CicloFormativo(1,"ficticio", new GradoE("Grado E", 1, 2), "ficticio",300)));


        ArrayList<Asignatura> asignaturasList = new ArrayList<>();
        NodeList asignaturasXML = ((Element) elemento.getElementsByTagName("Asignaturas").item(0)).getElementsByTagName("Asignatura");
        for (int i = 0; i < asignaturasXML.getLength(); i++) {
            Element asignaturaElement = (Element) asignaturasXML.item(i);
            String codigoAsignatura = asignaturaElement.getAttribute("Codigo");

            CicloFormativo cicloFormativo = new CicloFormativo(7771, "null", new GradoD("null", 2, Modalidad.PRESENCIAL), "null", 120);
            Asignatura asignaturaBuscar = new Asignatura(codigoAsignatura, "null", 200, Curso.PRIMERO, 2, EspecialidadProfesorado.FOL, cicloFormativo);

            Asignatura asignaturaEncontrada = Asignaturas.getInstancia().buscar(asignaturaBuscar);
            if (asignatura != null) {
                asignaturasList.add(asignaturaEncontrada);
            }
        }


        NodeList nodosFechaAnulacion = elemento.getElementsByTagName(FECHAANULACION);

        Alumnos alumnoBuscar = Alumnos.getInstancia();
        Alumno alumnoMatricula = alumnoBuscar.buscar(new Alumno(alumno, "ficticio", "ficticio", "ficticio", LocalDate.of(1991,1,1)));

        LocalDate fechaMatri = LocalDate.parse(fechaMatriculacion, DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA));
        //LocalDate fechaAnular = LocalDate.parse(fechaAnulacion, DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA));
        Matricula matricula = new Matricula(Integer.parseInt(id), curso, fechaMatri, alumnoMatricula, asignaturasList);
        if (nodosFechaAnulacion != null) {
            if (nodosFechaAnulacion.getLength() > 0) {
                String fechaAnulacionTexto = nodosFechaAnulacion.item(0).getTextContent();
                if (fechaAnulacionTexto != null && !fechaAnulacionTexto.isBlank()) {
                    LocalDate fechaAnular = LocalDate.parse(fechaAnulacionTexto, DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA));
                    matricula.setFechaAnulacion(fechaAnular);
                }
            }


        }
        return matricula;
    }

    @Override
    public void terminar() {
        escribirXML();

    }

    private void escribirXML() {
        Document documento = UtilidadesXML.crearDomVacio(RAIZ);

        for(Matricula m : coleccionMatriculas) {
            Element elemento = MatriculasToElement(documento, m);
            documento.getDocumentElement().appendChild(elemento);
        }
        UtilidadesXML.domToXml(documento, RUTA_FICHERO);
    }

    private Element MatriculasToElement(Document documento, Matricula m) {
        Element elementoMatricula = documento.createElement(MATRICULA);
        elementoMatricula.setAttribute(ID,String.valueOf(m.getIdMatricula()));
        elementoMatricula.setAttribute(ALUMNO,m.getAlumno().getDni());

        Element elementoCurso = documento.createElement(CURSO);
        elementoCurso.setTextContent(m.getCursoAcademico());
        elementoMatricula.appendChild(elementoCurso);

        Element elementoFechaMatriculacion = documento.createElement(FECHAMATRICULACION);
        elementoFechaMatriculacion.setTextContent(m.getFechaMatriculacion().toString());
        elementoMatricula.appendChild(elementoFechaMatriculacion);

        Element elementoFechaAnulacion = documento.createElement(FECHAANULACION);
        elementoFechaAnulacion.setTextContent(m.getFechaAnulacion().toString());
        elementoMatricula.appendChild(elementoFechaAnulacion);

        Element elementoAsignaturas = documento.createElement(ASIGNATURAS);
        for(Asignatura a : m.getColeccionAsignaturas()) {
            Element elementoAsignatura = documento.createElement(ASIGNATURA);
            elementoAsignatura.setAttribute(CODIGO,a.getCodigo());
            elementoAsignaturas.appendChild(elementoAsignatura);
        }
        elementoMatricula.appendChild(elementoAsignaturas);
        return elementoMatricula;


    }

    //copia profunda
    public ArrayList<Matricula> get() throws OperationNotSupportedException {
        return copiaProfundaMatriculas();
    }

    private ArrayList<Matricula> copiaProfundaMatriculas() throws OperationNotSupportedException {
        ArrayList<Matricula> copiaMatriculas = new ArrayList<>();
        for (Matricula matricula : coleccionMatriculas) {
            copiaMatriculas.add(new Matricula(matricula));
        }
        return copiaMatriculas;
    }
    //Insertar Matricula
    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        Objects.requireNonNull(matricula, "ERROR: No se puede insertar una matrícula nula.");

        int indice = this.coleccionMatriculas.indexOf(matricula);

        if (indice == -1) {
            this.coleccionMatriculas.add(new Matricula(matricula));

        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una matrícula con ese identificador.");
        }
    }

    //buscar Matricula
    public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede buscar una Matricula nula.");
        }

        int indice = this.coleccionMatriculas.indexOf(matricula);
        if (indice == -1) {
            return null;
        } else {
            return new Matricula(this.coleccionMatriculas.get(indice));
        }
    }
    //borrar Matricula
    public void borrar (Matricula matricula) throws OperationNotSupportedException {
        Objects.requireNonNull(matricula,"ERROR: No se puede borrar una matrícula nula.");

        int indice = this.coleccionMatriculas.indexOf(matricula);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula como la indicada.");
        }
        this.coleccionMatriculas.remove(indice);
    }





    public int getTamano() {
        return this.coleccionMatriculas.size();
    }

    public ArrayList<Matricula> get(Alumno alumno) throws OperationNotSupportedException {
        ArrayList<Matricula> auxiliar = new ArrayList<>();
        for (Matricula matricula:coleccionMatriculas) {
            if (matricula != null && matricula.getAlumno().equals(alumno)) {
                auxiliar.add(new Matricula(matricula));

            }
        }

        return auxiliar;
        //int contador = 0;

        //Contar cuántas coincidencias del alumno hay en matrículas:
        //Recorre las matrículas
       /* for (Matricula matricula : coleccionMatriculas) {
            //Si en la matricula actual, su alumno = al alumno pasado...
            if (matricula.getAlumno().equals(alumno)) {
                contador++;
            }
        }

        //Crear array con el número de coincidencias del alumno en las matrículas:
        Matricula[] coleccionMatriculasAlumn = new Matricula[contador];

        int i = 0;
        //Para asignar las matrículas con coincidencias al nuevo array:
        for (Matricula matriculaAlumno : coleccionMatriculas){
            if (matriculaAlumno.getAlumno().equals(alumno)){
                coleccionMatriculasAlumn[i] = matriculaAlumno;
                i++;
            }
        }
        return coleccionMatriculasAlumn;*/
        //return auxiliar.toArray(new Matricula[0]);
    }


    public ArrayList<Matricula> get(String cursoAcademico) throws OperationNotSupportedException {
        ArrayList<Matricula> auxiliar = new ArrayList<>();
        for (Matricula matricula:coleccionMatriculas) {
            if (matricula != null && matricula.getCursoAcademico().equals(cursoAcademico)) {
                auxiliar.add(new Matricula(matricula));

            }
        }
        return auxiliar;
        /*int contador = 0;

        for (Matricula matricula : coleccionMatriculas) {
            if (matricula.getCursoAcademico().equals(cursoAcademico)) {
                contador++;
            }
        }

        Matricula[] coleccionMatriculasCurso = new Matricula[contador];

        int i = 0;

        for (Matricula matriculaCurso : coleccionMatriculas) {

            if (matriculaCurso.getCursoAcademico().equals(cursoAcademico)){
                coleccionMatriculasCurso[i] = matriculaCurso;
                i++;
            }
        }
        return coleccionMatriculasCurso;*/
    }


    public ArrayList<Matricula> get(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        ArrayList<Matricula> auxiliar = new ArrayList<>();
        for (Matricula matricula:coleccionMatriculas) {
            if (matricula != null ) {
                for (Asignatura asignatura : matricula.getColeccionAsignaturas()) {

                    if (asignatura != null && asignatura.getCicloFormativo().equals(cicloFormativo)) {
                        auxiliar.add(new Matricula(matricula));
                        break;
                    }

                }
            }
        }

        return auxiliar;

    }


}
