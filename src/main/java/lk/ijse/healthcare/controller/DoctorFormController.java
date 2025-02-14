package lk.ijse.healthcare.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import lk.ijse.healthcare.bo.BOFactory;
import lk.ijse.healthcare.bo.custom.DoctorBO;
import lk.ijse.healthcare.bo.custom.impl.DoctorBOImpl;
import lk.ijse.healthcare.db.DBConnection;
import lk.ijse.healthcare.dto.DoctorFormDto;
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
import javafx.scene.layout.AnchorPane;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class DoctorFormController implements Initializable {
    private boolean isNameValid = false;
    private boolean isCoNumValid = false;
    private boolean isEmailValid = false;
    private boolean isAddressValid = false;

    DoctorBO doctorBO = (DoctorBO) BOFactory.getInstance().getBO(BOFactory.BOType.DOCTOR);

    @FXML
    private AnchorPane addPane;

    @FXML
    private TableColumn<DoctorTM, String> addressCol;

    @FXML
    private Button btnDeleteItem;

    @FXML
    private Button btnReset;

    @FXML
    private JFXButton btnReport;

    @FXML
    private Button btnSaveItem;

    @FXML
    private Button btnUpdateItem;

    @FXML
    private TableColumn<DoctorTM, String> coNumCol;

    @FXML
    private TableColumn<DoctorTM, String> emailCol;

    @FXML
    private TextField lblSearch;

    @FXML
    private TableColumn<DoctorTM, String> namCol;

    @FXML
    private TableView<DoctorFormDto> tblDoctor;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtDocName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtMobile;

    @FXML
    void addressReleased(KeyEvent event) {
        if (CheckRegex.checkRegex("address", txtAddress.getText())) {
            txtAddress.setStyle("-fx-text-fill: green;");
            isAddressValid = true;
        } else {
            txtAddress.setStyle("-fx-text-fill: red;");
            isAddressValid = false;
        }
    }

    @FXML
    void btnDeleteItemOnAction(ActionEvent event) throws SQLException {
        if (tblDoctor != null) {
            DoctorFormDto selectedDoctor = tblDoctor.getSelectionModel().getSelectedItem();
            if (selectedDoctor == null) {
                new AlertNotification(
                        "Error Message",
                        "Please select a doctor to delete.",
                        "unsuccess.png",
                        "ok"
                ).start();
                return;
            }
        }
        String DocName = tblDoctor.getSelectionModel().getSelectedItem().getName();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Doctor ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get() == ButtonType.YES) {
            clearFields();
            refreshTable();
            boolean isDeleted = doctorBO.deleteDoctor(DocName);
            if (isDeleted) {
                new AlertNotification(
                        "Success Message",
                        "Doctor deleted successfully !",
                        "success.png",
                        "ok"
                ).start();
                refreshTable();
            } else {
                new AlertNotification(
                        "Error Message",
                        "An error occurred while deleting the doctor. Please try again later.",
                        "unsuccess.png",
                        "ok"
                ).start();
            }
        }else {
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
        txtDocName.clear();
        txtEmail.clear();
        txtMobile.clear();
        txtAddress.clear();

        btnDeleteItem.setDisable(true);
        btnUpdateItem.setDisable(true);
        btnSaveItem.setDisable(false);

        txtDocName.setStyle("");
        txtEmail.setStyle("");
        txtMobile.setStyle("");
        txtAddress.setStyle("");
    }

    @FXML
    void btnSaveItemOnAction(ActionEvent event) throws SQLException {
        if (isNameValid && isCoNumValid && isEmailValid && isAddressValid) {
            Boolean isAddedDoctor = doctorBO.saveDoctor(
                    new DoctorFormDto(
                            0,
                            txtDocName.getText(),
                            txtEmail.getText(),
                            txtMobile.getText(),
                            txtAddress.getText(),
                            1
                    )
            );

            if (isAddedDoctor) {
                refreshTable();
                new AlertNotification(
                        "Success Message",
                        "Doctor added successfully to the list",
                        "success.png",
                        "GREEN"
                ).start();
                clearFields();
            } else {
                new AlertNotification(
                        "Error Message",
                        "Failed to add doctor to the list",
                        "unsuccess.png",
                        "RED"
                ).start();
            }
        } else {
            new AlertNotification(
                    "Error Message",
                    "Please fill all fields correctly and try again",
                    "unsuccess.png",
                    "RED"
            ).start();
        }
    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) throws SQLException {
        String name = txtDocName.getText();
        String email = txtEmail.getText();
        String mobile = txtMobile.getText();
        String address = txtAddress.getText();

        DoctorFormDto doctorTM = new DoctorFormDto(
                0,
                name,
                email,
                mobile,
                address,
                1
        );
        boolean isUpdate = doctorBO.updateDoctor(doctorTM);

        if (isUpdate){
            refreshTable();
            clearFields();
            new AlertNotification(
                    "Success Message",
                    "Doctor updated successfully to the list",
                    "success.png",
                    "ok"
            ).start();
        }else {
            new AlertNotification(
                    "Error Message",
                    "Failed to update to doctor Please try again",
                    "unsuccess.png",
                    "ok"
            ).start();
        }
    }

    @FXML
    void docNameReleased(KeyEvent event) {
        if (CheckRegex.checkRegex("docName", txtDocName.getText())) {
            txtDocName.setStyle("-fx-text-fill: green;");
            isNameValid = true;
        } else {
            txtDocName.setStyle("-fx-text-fill: red;");
            isNameValid = false;
        }
    }

    @FXML
    void emailReleased(KeyEvent event) {
        if (CheckRegex.checkRegex("email", txtEmail.getText())) {
            txtEmail.setStyle("-fx-text-fill: green;");
            isEmailValid = true;
        } else {
            txtEmail.setStyle("-fx-text-fill: red;");
            isEmailValid = false;
        }
    }

    @FXML
    void mobileReleased(KeyEvent event) {
        if (CheckRegex.checkRegex("contactNumber", txtMobile.getText())) {
            txtMobile.setStyle("-fx-text-fill: green;");
            isCoNumValid = true;
        } else {
            txtMobile.setStyle("-fx-text-fill: red;");
            isCoNumValid = false;
        }
    }

    @FXML
    void searchPatients(KeyEvent event) throws SQLException {
        ArrayList<DoctorFormDto> doctors = doctorBO.searchDoctor(lblSearch.getText());
        ObservableList<DoctorFormDto> doctorList = FXCollections.observableArrayList();
        for (DoctorFormDto doctor : doctors) {
            doctorList.add(doctor);
        }
        tblDoctor.setItems(doctorList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        namCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        coNumCol.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        try {
            getDoctor();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtDocName.clear();
        txtEmail.clear();
        txtMobile.clear();
        txtAddress.clear();
    }

    private void refreshTable() throws SQLException {
        tblDoctor.getItems().clear();
        ArrayList<DoctorFormDto> allDoctors = doctorBO.getAllDoctor();
        ObservableList<DoctorFormDto> doctorList = FXCollections.observableArrayList(allDoctors);
        tblDoctor.setItems(doctorList);
    }

    public void getDoctor() throws SQLException {
        ArrayList<DoctorFormDto> doctorDtos = doctorBO.getAllDoctor();
        tblDoctor.getItems().clear();
        tblDoctor.getItems().addAll(doctorDtos);
    }

    public void onClickTable(MouseEvent mouseEvent) {
        DoctorFormDto selectDoctor = tblDoctor.getSelectionModel().getSelectedItem();
        if (selectDoctor != null) {
            txtDocName.setText(selectDoctor.getName());
            txtMobile.setText(selectDoctor.getContactNumber());
            txtEmail.setText(selectDoctor.getEmail());
            txtAddress.setText(selectDoctor.getAddress());

            btnSaveItem.setDisable(true);
            btnUpdateItem.setDisable(false);
            btnDeleteItem.setDisable(false);
        }
    }

    public void reportOnAction(ActionEvent event) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("TODAY", LocalDate.now().toString());
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/reports/Doctors_Report.jrxml")
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
