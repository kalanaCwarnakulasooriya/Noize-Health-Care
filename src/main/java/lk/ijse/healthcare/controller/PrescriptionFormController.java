package lk.ijse.healthcare.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import lk.ijse.healthcare.bo.BOFactory;
import lk.ijse.healthcare.bo.custom.impl.PatientsBOImpl;
import lk.ijse.healthcare.bo.custom.AppointmentBo;
import lk.ijse.healthcare.bo.custom.DoctorBO;
import lk.ijse.healthcare.bo.custom.PatientsBO;
import lk.ijse.healthcare.bo.custom.PrescriptionBO;
import lk.ijse.healthcare.bo.custom.impl.AppointmentBOImpl;
import lk.ijse.healthcare.bo.custom.impl.DoctorBOImpl;
import lk.ijse.healthcare.bo.custom.impl.PrescriptionBOImpl;
import lk.ijse.healthcare.db.DBConnection;
import lk.ijse.healthcare.dto.AppointmentFormDto;
import lk.ijse.healthcare.dto.DoctorFormDto;
import lk.ijse.healthcare.dto.PatientsFormDto;
import lk.ijse.healthcare.dto.PrescriptionFormDto;
import lk.ijse.healthcare.dto.tm.AppointmentTM;
import lk.ijse.healthcare.dto.tm.DoctorTM;
import lk.ijse.healthcare.dto.tm.PatientsTM;
import lk.ijse.healthcare.dto.tm.PrescriptionTM;
import lk.ijse.healthcare.util.AlertNotification;
import lk.ijse.healthcare.util.CheckRegex;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class PrescriptionFormController implements Initializable {
    PrescriptionBO prescriptionBO = (PrescriptionBO) BOFactory.getInstance().getBO(BOFactory.BOType.PRESCRIPTION);
    PatientsBO patientsBO = (PatientsBO) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT);
    AppointmentBo appointmentBo = (AppointmentBo) BOFactory.getInstance().getBO(BOFactory.BOType.APPOINTMENT);
    DoctorBO doctorBO = (DoctorBO) BOFactory.getInstance().getBO(BOFactory.BOType.DOCTOR);

    boolean isDosageValid = false;
    boolean isMediDetailsValid = false;
    boolean isDateValid = false;

    @FXML
    private Button btnDeleteItem;

    @FXML
    private Button btnReset;

    @FXML
    private Label lblDocName;

    @FXML
    private ComboBox<String> comboDocName;

    @FXML
    private Button btnSaveItem;

    @FXML
    private Button btnUpdateItem;

    @FXML
    private ComboBox<String> comboAppoID;

    @FXML
    private ComboBox<String> comboPID;

    @FXML
    private TableColumn<?, ?> dateCol;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableColumn<?, ?> dosageCol;

    @FXML
    private Label lblAge;

    @FXML
    private Label lblPName;

    @FXML
    private TableColumn<?, ?> mediDetailCol;

    @FXML
    private JFXButton btnReport;

    @FXML
    private TableView<PrescriptionFormDto> tblPrescription;

    @FXML
    private JFXTextField txtDosage;

    @FXML
    private JFXTextField txtMediDetails;

    @FXML
    void btnDeleteItemOnAction(ActionEvent event) throws SQLException {
        if (tblPrescription != null) {
            PrescriptionFormDto selectedPrescription = tblPrescription.getSelectionModel().getSelectedItem();
            if (selectedPrescription == null) {
                new AlertNotification(
                        "Error Message",
                        "Please select a prescription to delete.",
                        "unsuccess.png",
                        "ok"
                ).start();
                return;
            }
        }
        String prescriptionDate = tblPrescription.getSelectionModel().getSelectedItem().getDate();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Prescription ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get() == ButtonType.YES) {
            clearFields();
            refreshTable();
            boolean isDeleted = prescriptionBO.deletePrescription(prescriptionDate);
            if (isDeleted) {
                btnSaveItem.setDisable(false);
                btnUpdateItem.setDisable(true);
                btnDeleteItem.setDisable(true);
                btnReset.setDisable(false);
                new AlertNotification(
                        "Success Message",
                        "Prescription deleted successfully.",
                        "success.png",
                        "ok"
                ).start();
                refreshTable();
            } else {
                new AlertNotification(
                        "Error Message",
                        "An error occurred while deleting the prescription. Please try again later.",
                        "unsuccess.png",
                        "ok"
                ).start();
            }
        } else {
            new AlertNotification(
                    "Error Message",
                    "You have canceled the delete operation.",
                    "unsuccess.png",
                    "ok"
            ).start();
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtDosage.clear();
        txtMediDetails.clear();
        datePicker.setValue(null);
        comboAppoID.getSelectionModel().clearSelection();
        comboPID.getSelectionModel().clearSelection();
        comboDocName.getSelectionModel().clearSelection();
        lblAge.setText("");
        lblPName.setText("");
        lblDocName.setText("");

        btnDeleteItem.setDisable(true);
        btnUpdateItem.setDisable(true);
        btnSaveItem.setDisable(false);

        txtDosage.setStyle("");
        txtMediDetails.setStyle("");
    }

    @FXML
    void btnSaveItemOnAction(ActionEvent event) throws SQLException {
        isDateValid = datePicker.getValue() != null;

        if (isDateValid && !isDosageValid && !isMediDetailsValid) {
            new AlertNotification(
                    "Invalid input",
                    "Please check the input fields",
                    "unsuccess.png",
                    "ok"
            ).start();
            return;
        }
        LocalDate date = datePicker.getValue();

        if (prescriptionBO.savePrescription(new PrescriptionFormDto(tblPrescription.getItems().size() + 1,   txtDosage.getText(),txtMediDetails.getText(),String.valueOf(date), 1, comboDocName.getValue()))) {
            getPrescription();
            new AlertNotification(
                    "Success Message",
                    "Prescription added successfully",
                    "success.png",
                    "ok"
            ).start();
            getPrescription();
            clearFields();
            refreshTable();
        } else {
            new AlertNotification(
                    "Error Message",
                    "Failed to add to the Prescription list",
                    "unsuccess.png",
                    "ok"
            ).start();
        }
    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) throws SQLException {
        isDateValid = datePicker.getValue() != null;

        String mediDetails = txtMediDetails.getText();
        String dosage = txtDosage.getText();
        String doctorId = comboDocName.getValue();

        PrescriptionFormDto prescriptionTM = new PrescriptionFormDto(
                tblPrescription.getSelectionModel().getSelectedItem().getId(),
                String.valueOf(datePicker.getValue()),
                mediDetails,
                dosage,
                1,
                doctorId
        );
        boolean isUpdate = prescriptionBO.updatePrescription(prescriptionTM);

        if (isUpdate){
            refreshTable();
            clearFields();
            new AlertNotification(
                    "Success Message",
                    "Prescription updated successfully",
                    "success.png",
                    "ok"
            ).start();
        }else {
            new AlertNotification(
                    "Error Message",
                    "Failed to update to the Prescription list",
                    "unsuccess.png",
                    "ok"
            ).start();
        }
    }

    @FXML
    void datePickerOnAction(ActionEvent event) {
        datePicker.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: green;");
        LocalDate date = datePicker.getValue();
        if (date != null && date.isBefore(LocalDate.now())) {
            isDateValid = true;
        } else {
            isDateValid = false;
        }
    }

    @FXML
    void dosageRelease(KeyEvent event) {
        if (CheckRegex.checkRegex("dosage", txtDosage.getText())) {
            txtDosage.setStyle("-fx-text-fill: green;");
            isDosageValid = true;
        } else {
            txtDosage.setStyle("-fx-text-fill: red;");
            isDosageValid = false;
        }
    }

    @FXML
    void mediDetailRelease(KeyEvent event) {
        if (CheckRegex.checkRegex("dosage", txtMediDetails.getText())) {
            txtMediDetails.setStyle("-fx-text-fill: green;");
            isMediDetailsValid = true;
        } else {
            txtMediDetails.setStyle("-fx-text-fill: red;");
            isMediDetailsValid = false;
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        PrescriptionFormDto selectPrescription = (PrescriptionFormDto) tblPrescription.getSelectionModel().getSelectedItem();
        if (selectPrescription != null) {
            txtDosage.setText(selectPrescription.getDosage());
            txtMediDetails.setText(selectPrescription.getMediDetails());
            datePicker.setValue(LocalDate.parse(selectPrescription.getDate()));
            comboDocName.setValue(String.valueOf(selectPrescription.getDoctorId()));
//            comboAppoID.setValue(String.valueOf(selectPrescription.getAppoId()));
//            comboPID.setValue(String.valueOf(selectPrescription.getPatientId()));

            btnSaveItem.setDisable(true);
            btnUpdateItem.setDisable(false);
            btnDeleteItem.setDisable(false);
        }
    }

    @FXML
    void refreshTable() throws SQLException {
        tblPrescription.getItems().clear();
        ArrayList<PrescriptionFormDto> allPrescription = prescriptionBO.getAllPrescription();
        ObservableList<PrescriptionFormDto> appointmentList = FXCollections.observableArrayList(allPrescription);
        tblPrescription.setItems(appointmentList);
    }

    public void clearFields(){
        txtDosage.clear();
        txtMediDetails.clear();
        datePicker.setValue(null);
        comboAppoID.getSelectionModel().clearSelection();
        comboPID.getSelectionModel().clearSelection();
        comboDocName.getSelectionModel().clearSelection();
        lblAge.setText("");
        lblPName.setText("");
        lblDocName.setText("");
    }

    public void getPrescription() throws SQLException {
        ArrayList<PrescriptionFormDto> prescriptionTMS = prescriptionBO.getAllPrescription();
        tblPrescription.getItems().clear();
        tblPrescription.getItems().addAll(prescriptionTMS);
    }

    private void loadPatientsId() throws SQLException {
        ArrayList<String> patientsId  = patientsBO.getAllPatientMobile();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(patientsId);
        comboPID.setItems(observableList);
    }

    private void loadAppointmentsId() throws SQLException {
        ArrayList<String> appointmentsId  = appointmentBo.getAllSAppointment();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(appointmentsId);
        comboAppoID.setItems(observableList);
    }

    private void loadDoctorsId() throws SQLException {
        ArrayList<String> DoctorsId  = doctorBO.getAllSDoctor();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(DoctorsId);
        comboDocName.setItems(observableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        mediDetailCol.setCellValueFactory(new PropertyValueFactory<>("mediDetails"));
        dosageCol.setCellValueFactory(new PropertyValueFactory<>("dosage"));

        try {
            getPrescription();
            loadAppointmentsId();
            loadPatientsId();
            loadDoctorsId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void comboPatientOnAction(ActionEvent event) throws SQLException {
        comboPID.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: green;");
        String selectedDoctor = comboPID.getSelectionModel().getSelectedItem();
        PatientsFormDto patientTM = patientsBO.findPatientsById(selectedDoctor);

        if (patientTM != null) {
            lblPName.setText(patientTM.getPatientsName());
        }
    }

    public void comboAppointmentOnAction(ActionEvent event) throws SQLException {
        comboAppoID.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: green;");
        String selectedAppointment = comboAppoID.getSelectionModel().getSelectedItem();
        AppointmentFormDto appointmentTM = appointmentBo.findByAppointmentId(selectedAppointment);

        if (appointmentTM != null) {
            lblAge.setText(appointmentTM.getAge());
        }
    }

    public void comboDocNameOnAction(ActionEvent event) throws SQLException {
        comboDocName.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: green;");
        String selectedDoctor = (String) comboDocName.getSelectionModel().getSelectedItem();
        DoctorFormDto doctorTM = doctorBO.findByDoctorId(selectedDoctor);

        if (doctorTM != null) {
            lblDocName.setText(doctorTM.getName());
        }
    }

    public void reportOnAction(ActionEvent event) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("TODAY", LocalDate.now().toString());
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/reports/Prescription_Report.jrxml")
            );
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    connection
            );
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            new AlertNotification(
                    "Error Message",
                    "An error occurred while loading the report. Please try again later.",
                    "unsuccess.png",
                    "OK"
            ).start();
            e.printStackTrace();
        } catch (SQLException e) {
            new AlertNotification(
                    "Error Message",
                    "Data empty. Please try again later.",
                    "unsuccess.png",
                    "OK"
            ).start();
            e.printStackTrace();
        }
    }
}
