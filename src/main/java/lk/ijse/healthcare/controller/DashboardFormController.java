package lk.ijse.healthcare.controller;

import lk.ijse.healthcare.model.DashboardFormModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {
    DashboardFormModel dashboardModel = new DashboardFormModel();

    @FXML
    private Label lblAppointmentCount;

    @FXML
    private Label lblDoctorCount;

    @FXML
    private Label lblPatientCount;

    @FXML
    private Label lblPrescriptionCount;

    @FXML
    private Label lblItemCount;

    @FXML
    private Label lblOrderCount;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private CategoryAxis yAxis;

    @FXML
    private BarChart<String, Number> barChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HashMap<String,String> status = dashboardModel.status();

        lblPatientCount.setText(status.get("patient"));
        lblDoctorCount.setText(status.get("doctor"));
        lblPrescriptionCount.setText(status.get("prescription"));
        lblAppointmentCount.setText(status.get("appointment"));
        lblOrderCount.setText(status.get("orders"));
        lblItemCount.setText(status.get("item"));

//        loadTable();
    }
}
