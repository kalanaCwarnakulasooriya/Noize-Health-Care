package lk.ijse.healthcare.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import lk.ijse.healthcare.db.DBConnection;
import lk.ijse.healthcare.util.alert.AlertSound;
import lk.ijse.healthcare.dto.ItemFormDto;
import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dto.OrdersFormDto;
import lk.ijse.healthcare.dto.tm.OrdersTM;
import lk.ijse.healthcare.dto.tm.PatientsTM;
import lk.ijse.healthcare.model.ItemFormModel;
import lk.ijse.healthcare.model.OrdersFormModel;
import lk.ijse.healthcare.dao.custom.impl.PatientsDAOImpl;
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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class OrderFormController implements Initializable {
    private final OrdersFormModel orderFormModel = new OrdersFormModel();
    private final ItemFormModel itemFormModel = new ItemFormModel();
    private final PatientsDAOImpl patientsDAOImpl = new PatientsDAOImpl();
    private final AlertSound alertSound = new AlertSound();
    private final ObservableList<OrdersTM> obList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> actionCol;

    @FXML
    private TableColumn<OrdersTM, Integer> cartQtyCol;

    @FXML
    private AnchorPane paymentPane;

    @FXML
    private AnchorPane orderPane;

    @FXML
    private ComboBox<String> comboMediName;

    @FXML
    private ComboBox<String> comboMobile;

    @FXML
    private Label lblOrderDate;

    @FXML
    private JFXButton btnAddCart;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblPName;

    @FXML
    private JFXButton btnPay;

    @FXML
    private JFXButton btnOrderReport;

    @FXML
    private Label lblUPrice;

    @FXML
    private TableColumn<OrdersTM, String> nameCol;

    @FXML
    private TableView<OrdersTM> tblPlaceOrder;

    @FXML
    private TableColumn<OrdersTM, Double> totalCol;

    @FXML
    private Label lblQoh;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXButton btnReset;

    @FXML
    private Label lblPack;

    @FXML
    private Label lblTotal;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private ComboBox<String> comboMethod;

    @FXML
    private Label lblExpire;

    @FXML
    private TableColumn<OrdersTM, Double> unitPriceCol;

    private Boolean isQtyValid = false;

    @FXML
    void addToCartOnAction(ActionEvent event) {
        String selectMediName = comboMediName.getValue();
        String selectMobile = comboMobile.getValue();

        if (selectMediName == null) {
            new AlertNotification(
                    "Alert Message",
                    "Please select Medicine Name",
                    "unsuccess.png",
                    "ok"
            ).start();
            return;
        }

        if (selectMobile == null) {
            new AlertNotification(
                    "Alert Message",
                    "Please select ID of Patient",
                    "unsuccess.png",
                    "ok"
            ).start();
            return;
        }

        if (txtQty.getText().isEmpty()) {
            new AlertNotification(
                    "Alert Message",
                    "Please enter quantity of medicine",
                    "unsuccess.png",
                    "ok"
            ).start();
            return;
        }

        if (!isQtyValid) {
            new AlertNotification(
                    "Alert Message",
                    "Invalid quantity please enter valid quantity",
                    "unsuccess.png",
                    "ok"
            ).start();
            return;
        }

        int cartQty = Integer.parseInt(txtQty.getText());
        int qtyOnHand = Integer.parseInt(lblQoh.getText());

        if (qtyOnHand < cartQty) {
            new AlertNotification(
                    "Alert Message",
                    "Not enough items..!",
                    "unsuccess.png",
                    "ok"
            ).start();
            return;
        }

        txtQty.setText("");

        double unitPrice = Double.parseDouble(lblUPrice.getText());
        double total = unitPrice * cartQty;

        boolean found = false;
        for (OrdersTM orderTM : obList) {
            if (orderTM.getName().equals(selectMediName)) {
                int newQty = orderTM.getCartQty() + cartQty;
                orderTM.setCartQty(newQty);
                orderTM.setTotal(unitPrice * newQty);
                tblPlaceOrder.refresh();
                found = true;
                break;
            }
        }

        if (!found) {
            Button btn = new Button("Remove");

            OrdersTM newOrderTM = new OrdersTM(
                    obList.size() + 1,
                    selectMediName,
                    cartQty,
                    unitPrice,
                    total,
                    btn
            );

            btn.setOnAction(actionEvent -> {
                obList.remove(newOrderTM);
                tblPlaceOrder.refresh();
            });

            obList.add(newOrderTM);
        }
    }

    @FXML
    void placeOrderOnAction(ActionEvent event) throws SQLException {
        if (tblPlaceOrder.getItems().isEmpty()) {
            new AlertNotification(
                    "Alert Message",
                    "Please add items to cart",
                    "unsuccess.png",
                    "ok"
            ).start();
            return;
        }
        orderPane.setVisible(false);
        btnAddCart.setVisible(false);
        btnPlaceOrder.setDisable(true);
        btnOrderReport.setDisable(false);
        paymentPane.setVisible(true);
        btnReset.setDisable(true);
        btnOrderReport.setDisable(true);
    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException {
        refeshPage();
    }

    @FXML
    void qtyRelease(KeyEvent event) {
        if (CheckRegex.checkRegex("qty", txtQty.getText())) {
            txtQty.setStyle("-fx-text-fill: green;");
            isQtyValid= true;
        } else {
            txtQty.setStyle("-fx-text-fill: red;");
            isQtyValid = false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValues();
        try {
            refeshPage();
            btnOrderReport.setDisable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCellValues(){
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        cartQtyCol.setCellValueFactory(new PropertyValueFactory<>("CartQty"));
        unitPriceCol.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("Total"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("RemoveBtn"));

        tblPlaceOrder.setItems(obList);
    }

    private void refeshPage() throws SQLException {
        lblOrderId.setText(String.valueOf(orderFormModel.getNewOrderId()));
        lblOrderDate.setText(LocalDate.now().toString());

        loadPatientMobile();
        loadMediName();
        loadPayments();

        comboMobile.getSelectionModel().clearSelection();
        comboMediName.getSelectionModel().clearSelection();
        comboMethod.getSelectionModel().clearSelection();
        lblPName.setText("");
        lblQoh.setText("");
        txtQty.setText("");
        lblUPrice.setText("");
        lblExpire.setText("");
        lblPack.setText("");

        obList.clear();
        tblPlaceOrder.refresh();
    }

    private void refeshFieldsOnly() throws SQLException {
        lblOrderId.setText(String.valueOf(orderFormModel.getNewOrderId()));
        lblOrderDate.setText(LocalDate.now().toString());

//        loadPatientMobile();
        loadMediName();
        loadPayments();

//        comboMobile.getSelectionModel().clearSelection();
        comboMediName.getSelectionModel().clearSelection();
        comboMethod.getSelectionModel().clearSelection();
//        lblPName.setText("");
        lblQoh.setText("");
        txtQty.setText("");
        lblUPrice.setText("");
        lblExpire.setText("");
        lblPack.setText("");

//        obList.clear();
//        tblPlaceOrder.refresh();
    }

    private void loadPatientMobile() throws SQLException {
        ArrayList<String> patientsMobile  = patientsDAOImpl.getAllPatientMobile();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(patientsMobile);
        comboMobile.setItems(observableList);
    }

    private void loadMediName() throws SQLException {
        ArrayList<String> itemNames = itemFormModel.getAllItemNames();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(itemNames);
        comboMediName.setItems(observableList);
    }

    public void comboMediNameOnAction(ActionEvent actionEvent) throws SQLException {
        comboMediName.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: green;");
        String selectMediName = comboMediName.getSelectionModel().getSelectedItem();
        ItemFormDto itemFormDto = itemFormModel.findById(selectMediName);

        if (itemFormDto != null) {
            lblUPrice.setText(String.valueOf(itemFormDto.getPrice()));
            lblQoh.setText(String.valueOf(itemFormDto.getQty()));
            lblExpire.setText(String.valueOf(itemFormDto.getExpireDate()));
            lblPack.setText(String.valueOf(itemFormDto.getPackSize()));
        }
    }

    public void comboMobileOnAction(ActionEvent actionEvent) throws SQLException {
        comboMobile.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: green;");
        String selectedMobile = comboMobile.getSelectionModel().getSelectedItem();
        PatientsTM patientsTM = patientsDAOImpl.findById(selectedMobile);

        if (patientsTM != null) {
            lblPName.setText(patientsTM.getPatientsName());
        }
    }

    public void onTotalRelease(KeyEvent keyEvent) {
    }

    public void paymentOnAction(ActionEvent actionEvent) throws SQLException {
        if (tblPlaceOrder.getItems().isEmpty()) {
            new AlertNotification(
                    "Alert Message",
                    "Please add items to cart",
                    "unsuccess.png",
                    "ok"
            ).start();
            return;
        }
        if (comboMobile.getSelectionModel().isEmpty()) {
            new AlertNotification(
                    "Alert Message",
                    "Please select Patient mobile number",
                    "unsuccess.png",
                    "ok"
            ).start();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Pay this Bill ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get() == ButtonType.YES) {
            String orderId = lblOrderId.getText();
            Date dateOfOrder = Date.valueOf(lblOrderDate.getText());
            String patientId = comboMobile.getValue();

            ArrayList<OrderDetailsFormDto> orderDetailsDTOS = new ArrayList<>();

            for (OrdersTM cartTM : obList) {

                OrderDetailsFormDto orderDetailsDTO = new OrderDetailsFormDto(
                        Integer.parseInt(orderId),
                        cartTM.getItemId(),
                        cartTM.getCartQty(),
                        cartTM.getUnitPrice()
                );

                orderDetailsDTOS.add(orderDetailsDTO);
            }
            OrdersFormDto orderDTO = new OrdersFormDto(
                    Integer.parseInt(orderId),
                    dateOfOrder,
                    Integer.parseInt(patientId),
                    orderDetailsDTOS
            );

            boolean isSaved = orderFormModel.saveOrder(orderDTO);

            if (isSaved) {
                paymentPane.setVisible(false);
                orderPane.setVisible(true);
                btnPlaceOrder.setDisable(true);
                btnAddCart.setVisible(false);
                btnOrderReport.setDisable(false);
                btnPay.setDisable(true);
                refeshPage();
                new AlertNotification(
                        "Alert Message",
                        "Order placed successfully",
                        "success.png",
                        "ok"
                ).start();

                refeshPage();
            } else {
                new AlertNotification(
                        "Alert Message",
                        "Failed to place order",
                        "unsuccess.png",
                        "ok"
                ).start();
            }
        } else {
            new AlertNotification(
                    "Error Message",
                    "You have cancelled the payment",
                    "unsuccess.png",
                    "ok"
            ).start();
        }
    }

    public void backOnClicked(MouseEvent mouseEvent) throws SQLException {
        paymentPane.setVisible(false);
        orderPane.setVisible(true);
        btnAddCart.setVisible(true);
        btnPlaceOrder.setDisable(false);
        btnOrderReport.setDisable(false);
        btnPay.setDisable(false);
        btnReset.setDisable(false);
        refeshFieldsOnly();
    }

    void loadPayments() {
        comboMethod.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: green;");
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(
                "Card",
                "Cash",
                "Online"
        );
        comboMethod.setItems(observableList);
    }

    public void comboMethodOnAction(ActionEvent event) {
    }

    public void orderReportOnAction(ActionEvent event) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("TODAY", LocalDate.now().toString());
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/reports/Order_Report.jrxml")
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
