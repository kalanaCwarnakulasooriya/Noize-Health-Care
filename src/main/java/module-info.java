module com.noize.medicalcenter {
    requires javafx.fxml;
    requires java.sql;
    requires lombok;
    requires com.jfoenix;
    requires net.sf.jasperreports.core;
    requires mysql.connector.j;
    requires com.google.api.client;
    requires jbcrypt;
    requires org.controlsfx.controls;
    requires java.desktop;
    requires javafx.media;
    requires javax.mail.api;
    requires resend.java;
    requires org.json;
    requires webcam.capture;


    opens lk.ijse.healthcare.controller to javafx.fxml;
    opens lk.ijse.healthcare.model to javafx.base;
    opens lk.ijse.healthcare.dto to javafx.base;
    opens lk.ijse.healthcare.util.alert to javafx.base;
    opens lk.ijse.healthcare.util to javafx.base;
    opens lk.ijse.healthcare.dto.tm to javafx.base;

    exports lk.ijse.healthcare;
    exports lk.ijse.healthcare.controller;
    opens lk.ijse.healthcare.dao to javafx.base;
    opens lk.ijse.healthcare.dao.custom to javafx.base;
    opens lk.ijse.healthcare.dao.custom.impl to javafx.base;

}