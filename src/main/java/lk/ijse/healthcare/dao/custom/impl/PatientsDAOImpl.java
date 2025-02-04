package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.custom.PatientsDAO;
import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dto.PatientsFormDto;
import lk.ijse.healthcare.dto.tm.AppointmentTM;
import lk.ijse.healthcare.dto.tm.PatientsTM;
import lk.ijse.healthcare.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class PatientsDAOImpl implements PatientsDAO {
    @Override
    public ArrayList<PatientsFormDto> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM patient");

        ArrayList<PatientsFormDto> patients = new ArrayList<>();
        while (rst.next()) {
            PatientsFormDto patientsDto = new PatientsFormDto(
                    rst.getString("Name"),
                    rst.getString("Address"),
                    rst.getString("ContactNumber"),
                    rst.getString("Email"),
                    rst.getDate("DOB"),
                    rst.getString("Gender"),
                    rst.getDate("RegistrationDate")
            );
            patients.add(patientsDto);
        }
        return patients;
    }

    @Override
    public ArrayList<PatientsFormDto> search(String name) throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from patient where Name like ?", name+"%");
        ArrayList<PatientsFormDto> patient = new ArrayList<>();
        while (rst.next()) {
            PatientsFormDto newPatients = new PatientsFormDto(
                    rst.getString("Name"),
                    rst.getString("Address"),
                    rst.getString("ContactNumber"),
                    rst.getString("Email"),
                    rst.getDate("DOB"),
                    rst.getString("Gender"),
                    rst.getDate("RegistrationDate")
            );
            patient.add(newPatients);
        }
        return patient;
    }

    @Override
    public PatientsFormDto findById(String selectedContact) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM patient WHERE PatientId = ?", selectedContact);

        if (rst.next()) {
            return new PatientsFormDto(
                    rst.getString("Name"),
                    rst.getString("Address"),
                    rst.getString("ContactNumber"),
                    rst.getString("Email"),
                    rst.getDate("DOB"),
                    rst.getString("Gender"),
                    rst.getDate("RegistrationDate")
            );
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllS() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT PatientId FROM patient");
        ArrayList<String> patientMobile = new ArrayList<>();
        while (rst.next()) {
            patientMobile.add(rst.getString("PatientId"));
        }
        return patientMobile;
    }

    @Override
    public boolean delete(String patientName) throws SQLException {
        return SQLUtil.execute("DELETE FROM patient WHERE Name = ?", patientName);
    }

    @Override
    public boolean update(PatientsFormDto patientsTM) throws SQLException {
        return SQLUtil.execute(
                "UPDATE patient SET Address = ?, ContactNumber = ?, Email = ?, DOB = ?, Gender = ?, RegistrationDate = ? WHERE Name = ?",
                patientsTM.getPatientsAddress(),
                patientsTM.getPatientsContactNumber(),
                patientsTM.getPatientsEmail(),
                patientsTM.getPatientsDob(),
                patientsTM.getPatientsGender(),
                patientsTM.getPatientsRegDate(),
                patientsTM.getPatientsName()
                );
    }

    @Override
    public int getIdBy(String name) throws SQLException {
        return 0;
    }

    @Override
    public boolean save(PatientsFormDto patientDTO) throws SQLException {
        return false;
    }

    @Override
    public HashMap<String, String> status() throws SQLException {
        return null;
    }

    @Override
    public boolean changePwd(PatientsFormDto user, String newPassword) throws SQLException {
        return false;
    }

    @Override
    public double getOrder(String orderId) throws SQLException {
        return 0;
    }

    @Override
    public boolean saveOrderDetails(ArrayList<PatientsFormDto> orderDetailsDto) throws SQLException {
        return false;
    }

    @Override
    public int getUserId() throws SQLException {
        return 0;
    }

    @Override
    public String getNewOrderId() throws SQLException {
        return "";
    }

    @Override
    public ResultSet btnLogin(PatientsFormDto loginFormDto) throws Exception {
        return null;
    }
}
