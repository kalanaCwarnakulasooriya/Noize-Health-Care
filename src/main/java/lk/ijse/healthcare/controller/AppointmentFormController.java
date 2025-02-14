package lk.ijse.healthcare.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import lk.ijse.healthcare.bo.BOFactory;
import lk.ijse.healthcare.bo.custom.AppointmentBo;
import lk.ijse.healthcare.bo.custom.DoctorBO;
import lk.ijse.healthcare.bo.custom.impl.AppointmentBOImpl;
import lk.ijse.healthcare.bo.custom.impl.DoctorBOImpl;
import lk.ijse.healthcare.db.DBConnection;
import lk.ijse.healthcare.dto.AppointmentFormDto;
import lk.ijse.healthcare.dto.DoctorFormDto;
import lk.ijse.healthcare.dto.tm.AppointmentTM;
import lk.ijse.healthcare.dto.tm.DoctorTM;
import lk.ijse.healthcare.dao.custom.impl.DoctorDAOImpl;
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

public class AppointmentFormController implements Initializable {
    AppointmentBo appointment = (AppointmentBo) BOFactory.getInstance().getBO(BOFactory.BOType.APPOINTMENT);
    DoctorBO doctorBO = (DoctorBO) BOFactory.getInstance().getBO(BOFactory.BOType.DOCTOR);

    boolean isNameValid = false;
    boolean isAgeValid = false;
    boolean isDescriptionValid = false;
    boolean isDateValid = false;

    @FXML
    private TableColumn<AppointmentTM, String> ageCol;

    @FXML
    private TableColumn<AppointmentTM, Date> dateCol;

    @FXML
    private Button btnDeleteItem;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSaveItem;

    @FXML
    private Button btnUpdateItem;

    @FXML
    private JFXButton btnReport;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label lblName;

    @FXML
    private ComboBox<String> comboDoctor;

    @FXML
    private ComboBox<String> comboStatus;

    @FXML
    private TableColumn<AppointmentTM, String> descCol;

    @FXML
    private TableColumn<AppointmentTM, String> patientNameCol;

    @FXML
    private TableColumn<AppointmentTM, String> statusCol;

    @FXML
    private TableView<AppointmentFormDto> tblAppointment;
    
    @FXML
    private TextField lblSearch;

    @FXML
    private JFXTextField txtAge;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    void ageRelease(KeyEvent event) {
        if (CheckRegex.checkRegex("password", txtAge.getText())) {
            txtAge.setStyle("-fx-text-fill: green;");
            isAgeValid = true;
        } else {
            txtAge.setStyle("-fx-text-fill: red;");
            isAgeValid = false;
        }
    }

    @FXML
    void btnDeleteItemOnAction(ActionEvent event) throws SQLException {
        if (tblAppointment != null) {
            AppointmentFormDto selectedAppointment = tblAppointment.getSelectionModel().getSelectedItem();
            if (selectedAppointment == null) {
                new AlertNotification(
                        "Error Message",
                        "Please select a appointment to delete.",
                        "unsuccess.png",
                        "ok"
                ).start();
                return;
            }
        }
        String appointmentName = tblAppointment.getSelectionModel().getSelectedItem().getAge();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Appointment ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get() == ButtonType.YES) {
            clearFields();
            refreshTable();
            boolean isDeleted = appointment.deleteAppointment(appointmentName);
            if (isDeleted) {
                btnSaveItem.setDisable(false);
                btnUpdateItem.setDisable(true);
                btnDeleteItem.setDisable(true);
                btnReset.setDisable(false);
                new AlertNotification(
                        "Success Message",
                        "Appointment deleted successfully.",
                        "success.png",
                        "ok"
                ).start();
                refreshTable();
            } else {
                new AlertNotification(
                        "Error Message",
                        "An error occurred while deleting the appointment. Please try again later.",
                        "unsuccess.png",
                        "ok"
                ).start();
            }
        }else {
            new AlertNotification(
                    "Error Message",
                    "You have canceled the delete operation for this appointment.",
                    "unsuccess.png",
                    "ok"
            ).start();
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException {
        txtAge.clear();
        comboStatus.getSelectionModel().clearSelection();
        txtDescription.clear();
        datePicker.setValue(null);
        comboDoctor.setValue(null);
        lblName.setText("");
        tblAppointment.getSelectionModel().clearSelection();

        loadDoctorsId();

        btnDeleteItem.setDisable(true);
        btnUpdateItem.setDisable(true);
        btnSaveItem.setDisable(false);

        txtAge.setStyle("");
        txtDescription.setStyle("");
    }

    @FXML
    void btnSaveItemOnAction(ActionEvent event) throws SQLException {
        isDateValid = datePicker.getValue() != null;

        if (isDateValid && !isNameValid && !isAgeValid && !isDescriptionValid) {
            new AlertNotification(
                    "Invalid input",
                    "Please check the input fields and try again.",
                    "unsuccess.png",
                    "ok"
            ).start();
            return;
        }
        LocalDate date = datePicker.getValue();

        if (appointment.saveAppointment(new AppointmentFormDto(
                txtAge.getText(),
                comboStatus.getValue(),
                txtDescription.getText(),
                String.valueOf(date),
                comboDoctor.getValue(),
                1
        ))) {
            getAppointments();
            new AlertNotification(
                    "Success Message",
                    "Appointment added successfully to the list",
                    "success.png",
                    "ok"
            ).start();
            getAppointments();
            clearFields();
            refreshTable();
        } else {
            new AlertNotification(
                    "Error Message",
                    "Failed to add to the appointment list",
                    "unsuccess.png",
                    "ok"
            ).start();
        }
    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) throws SQLException {
        isDateValid = datePicker.getValue() != null;

        String age = txtAge.getText();
        String description = txtDescription.getText();
        String status = comboStatus.getValue();
        String doctorId = comboDoctor.getValue();

        AppointmentFormDto appointmentFormDto = new AppointmentFormDto(
                age,
                status,
                description,
                String.valueOf(datePicker.getValue()),
                doctorId,
                1
        );
        boolean isUpdate = appointment.updateAppointment(appointmentFormDto);

        if (isUpdate){
            refreshTable();
            clearFields();
            new AlertNotification(
                    "Success Message",
                    "Appointment updated successfully to the list",
                    "success.png",
                    "ok"
            ).start();
        }else {
            new AlertNotification(
                    "Error Message",
                    "Failed to update to the appointment list",
                    "unsuccess.png",
                    "ok"
            ).start();
        }
    }

    @FXML
    void descRelease(KeyEvent event) {
        if (CheckRegex.checkRegex("desc", txtDescription.getText())) {
            txtDescription.setStyle("-fx-text-fill: green;");
            isDescriptionValid = true;
        } else {
            txtDescription.setStyle("-fx-text-fill: red;");
            isDescriptionValid = false;
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        AppointmentFormDto selectPatient = tblAppointment.getSelectionModel().getSelectedItem();
        if (selectPatient != null) {
            txtAge.setText(String.valueOf(selectPatient.getAge()));
            comboStatus.setValue(selectPatient.getStatus());
            txtDescription.setText(selectPatient.getDescription());
            datePicker.setValue(LocalDate.parse(selectPatient.getDate()));
            comboDoctor.setValue(String.valueOf(selectPatient.getDoctorId()));

            btnSaveItem.setDisable(true);
            btnUpdateItem.setDisable(false);
            btnDeleteItem.setDisable(false);
        }
    }

    @FXML
    void refreshTable() throws SQLException {
        tblAppointment.getItems().clear();
        ArrayList<AppointmentFormDto> allAppointments = appointment.getAllAppointment();
        ObservableList<AppointmentFormDto> appointmentList = FXCollections.observableArrayList(allAppointments);
        tblAppointment.setItems(appointmentList);
    }

    public void datePickerOnAction(ActionEvent event) {
        datePicker.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: green;");
        LocalDate date = datePicker.getValue();
        if (date != null && date.isBefore(LocalDate.now())) {
            isDateValid = true;
        } else {
            isDateValid = false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        try {
            getAppointments();
            getStatus();
            loadDoctorsId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAppointments() throws SQLException {
        ArrayList<AppointmentFormDto> appointments = appointment.getAllAppointment();
        tblAppointment.getItems().clear();
        tblAppointment.getItems().addAll(appointments);
    }

    public  void getStatus(){
        ArrayList<String> status = new ArrayList<>();
        status.add("Pending");
        status.add("Done");
        comboStatus.getItems().clear();
        comboStatus.getItems().addAll(status);
    }

    public void searchAppointment() throws SQLException {
        ArrayList<AppointmentFormDto> appointments = appointment.searchAppointment(lblName.getText());
        ObservableList<AppointmentFormDto> appointmentList = FXCollections.observableArrayList();
        for (AppointmentFormDto appointment : appointments) {
            appointmentList.add(appointment);
        }
        tblAppointment.setItems(appointmentList);
    }

    public void clearFields(){
        txtAge.clear();
        comboStatus.getSelectionModel().clearSelection();
        txtDescription.clear();
        datePicker.setValue(null);
        comboDoctor.getSelectionModel().clearSelection();
        lblName.setText("");
    }

    public void comboStatusOnAction(ActionEvent event) {
        comboStatus.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: green;");
    }

    private void loadDoctorsId() throws SQLException {
        ArrayList<String> DoctorsId  = doctorBO.getAllSDoctor();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(DoctorsId);
        comboDoctor.setItems(observableList);
    }

    public void comboDoctorOnAction(ActionEvent event) throws SQLException {
        comboDoctor.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: green;");
        String selectedDoctor = (String) comboDoctor.getSelectionModel().getSelectedItem();
        DoctorFormDto doctorTM = doctorBO.findByDoctorId(selectedDoctor);

        if (doctorTM != null) {
            lblName.setText(doctorTM.getName());
        }
    }

    public void searchAppointment(KeyEvent keyEvent) throws SQLException {
        ArrayList<AppointmentFormDto> patients = appointment.searchAppointment(lblSearch.getText());
        ObservableList<AppointmentFormDto> patientTMS = FXCollections.observableArrayList();
        for (AppointmentFormDto patientsDto : patients) {
            patientTMS.add(patientsDto);
        }
        tblAppointment.setItems(patientTMS);
    }

    public void reportOnAction(ActionEvent event) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("TODAY", LocalDate.now().toString());
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/reports/Appointments_Report.jrxml")
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
