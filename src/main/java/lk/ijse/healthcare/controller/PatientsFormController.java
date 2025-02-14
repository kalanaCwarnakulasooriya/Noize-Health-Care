package lk.ijse.healthcare.controller;

import com.jfoenix.controls.JFXButton;
import lk.ijse.healthcare.bo.BOFactory;
import lk.ijse.healthcare.bo.custom.impl.PatientsBOImpl;
import lk.ijse.healthcare.bo.custom.PatientsBO;
import lk.ijse.healthcare.db.DBConnection;
import lk.ijse.healthcare.dto.PatientsFormDto;
import lk.ijse.healthcare.dto.tm.PatientsTM;
import lk.ijse.healthcare.util.AlertNotification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class PatientsFormController implements Initializable {
    PatientsBO patientsBO = (PatientsBO) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT);

    @FXML
    private TableColumn<PatientsTM, String> addressCol;

    @FXML
    private TableColumn<PatientsTM, String> coNumCol;

    @FXML
    private TableColumn<PatientsTM, Date> dobCol;

    @FXML
    private TableColumn<PatientsTM, String> emailCol;

    @FXML
    private TableColumn<PatientsTM, String> genderCol;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField lblSearch;

    @FXML
    private JFXButton btnReport;

    @FXML
    private TableColumn<PatientsTM, String> namCol;

    @FXML
    private TableColumn<PatientsTM, Date> registerCol;

    @FXML
    private TableView<PatientsFormDto> tblPatient;

    @FXML
    void addPatientClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addPatientsForm.fxml"));
        Parent root = loader.load();
        Stage windows = new Stage();
        windows.centerOnScreen();
        windows.setResizable(false);
        windows.setTitle("  Add Patients");
        windows.getIcons().add(
                new Image(
                        getClass().getResourceAsStream("/asset/icon/app_logo.png")
                )
        );
        windows.initModality(Modality.APPLICATION_MODAL);
        windows.initOwner(lblSearch.getScene().getWindow());
        windows.setScene(new Scene(root));
        windows.show();
    }

    @FXML
    void refreshTable() throws SQLException {
        tblPatient.getItems().clear();
        ArrayList<PatientsFormDto> allStaff = patientsBO.getAllPatients();
        ObservableList<PatientsFormDto> patientsDtos = FXCollections.observableArrayList();
        for (PatientsFormDto patientsDto : allStaff) {
            patientsDtos.add(patientsDto);
        }
        tblPatient.setItems(patientsDtos);
    }

    @FXML
    void searchPatients(KeyEvent event) throws SQLException {
        ArrayList<PatientsFormDto> patients = patientsBO.searchPatients(lblSearch.getText());
        ObservableList<PatientsFormDto> patientTMS = FXCollections.observableArrayList();
        for (PatientsFormDto patientsDto : patients) {
            System.out.println(patientsDto.toString());
            patientTMS.add(patientsDto);
        }
        tblPatient.setItems(patientTMS);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        namCol.setCellValueFactory(new PropertyValueFactory<>("patientsName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("patientsAddress"));
        coNumCol.setCellValueFactory(new PropertyValueFactory<>("patientsContactNumber"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("patientsEmail"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("patientsDob"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("patientsGender"));
        registerCol.setCellValueFactory(new PropertyValueFactory<>("patientsRegDate"));
        try {
            refreshTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addPatientsForm.fxml"));
        Parent root = loader.load();
        Stage windows = new Stage();
        windows.centerOnScreen();
        windows.setResizable(false);
        windows.setTitle("  Add Patients");
        windows.getIcons().add(
                new Image(
                        getClass().getResourceAsStream("/asset/icon/app_logo.png")
                )
        );
//        windows.initModality(Modality.APPLICATION_MODAL);
        windows.initOwner(lblSearch.getScene().getWindow());
        windows.setScene(new Scene(root));
        windows.show();
    }


    @FXML
    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException {
        if (tblPatient != null) {
            PatientsFormDto selectedPatient = tblPatient.getSelectionModel().getSelectedItem();
            if (selectedPatient == null) {
                new AlertNotification(
                        "Error Message",
                        "Please select a patient to delete.",
                        "unsuccess.png",
                        "ok"
                ).start();
                return;
            }
        }
        String PatientName = tblPatient.getSelectionModel().getSelectedItem().getPatientsName();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this patient ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get() == ButtonType.YES) {
            boolean isDeleted = patientsBO.deletePatient(PatientName);
            if (isDeleted) {
                new AlertNotification(
                        "Success Message",
                        "Patient deleted successfully !",
                        "success.png",
                        "ok"
                ).start();
                refreshTable();
            } else {
                new AlertNotification(
                        "Error Message",
                        "An error occurred while deleting the patient. Please try again later.",
                        "unsuccess.png",
                        "ok"
                ).start();
            }
        }else {
            new AlertNotification(
                    "Error Message",
                    "Please select a patient to delete.",
                    "unsuccess.png",
                    "ok"
            ).start();
        }
    }

    public void onClicked(MouseEvent mouseEvent) {
    }

    public void reportOnAction(ActionEvent event) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("TODAY", LocalDate.now().toString());
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/reports/Patients_Report.jrxml")
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
