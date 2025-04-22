package org.iesalandalus.programacion.matriculacion.vista.grafica.controladores;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.matriculacion.vista.grafica.utilidades.Dialogos;

public class ControladorCrearCiclosFormativos {

    @FXML private Button btnAceptarCrearCiclo;
    @FXML private ComboBox <TiposGrado> cbElegirGrado;
    @FXML private TextField tfFamiliaCiclo;
    @FXML private TextField tfHorasCiclo;
    @FXML private TextField tfNombreCiclo;
    @FXML private TextField tfcodigoCiclo;
    @FXML private ComboBox<?> cbEdiciones;
    @FXML private ComboBox<Modalidad> cbModalidad;
    @FXML private RadioButton check1ano;
    @FXML private RadioButton check2anos;
    @FXML private RadioButton check3anos;


    @FXML
    public void initialize() {

        cargarOpcionesGrados();

    }

    private ObservableList<TiposGrado> obsListadoOpcionesChoice=
            FXCollections.observableArrayList(TiposGrado.values());

    private void cargarOpcionesGrados() {
        cbElegirGrado.setItems(obsListadoOpcionesChoice);
        cbElegirGrado.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->modificadoComboBoxListadoOpciones(oldValue,newValue));
    }

    private void modificadoComboBoxListadoOpciones(TiposGrado oldValue, TiposGrado newValue) {
        System.out.println(oldValue+" -> "+newValue);
    }

    @FXML
    void seleccionarGrado(ActionEvent event) {
        System.out.println(cbElegirGrado.getValue());
    }

    @FXML
    void CrearCicloFormativo(ActionEvent event) {

        Stage escenarioCiclos = (Stage) btnAceptarCrearCiclo.getScene().getWindow();
        try {

            int codigo = Integer.parseInt(tfcodigoCiclo.getText());
            String familia = tfFamiliaCiclo.getText();
            TiposGrado tg = cbElegirGrado.getSelectionModel().getSelectedItem();
            int anios;
            if (check1ano.isSelected()){
                anios = 1;
            }
            if (check2anos.isSelected()){
                anios = 2;
            }
            if (check3anos.isSelected()){
                anios = 3;
            }
            Grado grado = null;
            if(tg.toString().equals("Grado D")){
                 grado = new GradoD("Grado D", 2, Modalidad.PRESENCIAL);
            }else{
                grado = new GradoE("Grado E", 1, 1);
            }
            String nombre = tfNombreCiclo.getText();
            int horas = Integer.parseInt(tfHorasCiclo.getText());

            if (codigo < 0 || codigo > 9999 || familia.trim().isBlank() || grado == null || nombre.trim().isBlank() || horas < 0) {
               return;
            }
            CicloFormativo ciclo = new CicloFormativo(codigo, familia, grado, nombre, horas);
            VistaGrafica.getInstancia().getControlador().insertar(ciclo);
            Dialogos.mostrarDialogoInformacion("Ciclo Formativo insertado","Ciclo Formativo insertado correctamente");
            escenarioCiclos.close();
        } catch (Exception e) {
            Dialogos.mostrarDialogoError("Error al insertar Ciclo Formativo",e.getMessage());
        }
    }

//    `    private void modificadoListaGrados(String oldValue, String newValue)
//    {
//        System.out.println("Modificado valor del ListView");
//        System.out.println("El nuevo valor seleccionado es: " + newValue);

//        if (newValue.equals("Grado D")) {
//            lbAnios.setVisible(true);
//            tfAnios.setVisible(true);
//            lbModaidad.setVisible(true);
//            cbModalidad.setVisible(true);
//            lbEdiciones.setVisible(false);
//            tfEdiciones.setVisible(false);
//        } else if (newValue.equals("Grado E")) {
//            lbEdiciones.setVisible(true);
//            tfEdiciones.setVisible(true);
//            lbModaidad.setVisible(false);
//            cbModalidad.setVisible(false);
//            lbAnios.setVisible(true);
//            tfAnios.setVisible(true);
//        }
//    }

}
