package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.custom.PatientsDAO;
import lk.ijse.healthcare.dto.tm.PatientsTM;
import lk.ijse.healthcare.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientsDAOImpl implements PatientsDAO {
    @Override
    public ArrayList<PatientsTM> getAllPatients() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM patient");

        ArrayList<PatientsTM> patients = new ArrayList<>();
        while (rst.next()) {
            PatientsTM patientsDto = new PatientsTM(
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
    public ArrayList<PatientsTM> searchPatients(String name) throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from patient where Name like ?", name+"%");
        ArrayList<PatientsTM> patient = new ArrayList<>();
        while (rst.next()) {
            PatientsTM newPatients = new PatientsTM(
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
    public PatientsTM findById(String selectedContact) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM patient WHERE PatientId = ?", selectedContact);

        if (rst.next()) {
            return new PatientsTM(
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
    public ArrayList<String> getAllPatientMobile() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT PatientId FROM patient");
        ArrayList<String> patientMobile = new ArrayList<>();
        while (rst.next()) {
            patientMobile.add(rst.getString("PatientId"));
        }
        return patientMobile;
    }

    @Override
    public boolean deletePatient(String patientName) throws SQLException {
        return SQLUtil.execute("DELETE FROM patient WHERE Name = ?", patientName);
    }

    @Override
    public boolean updatePatient(PatientsTM patientsTM) throws SQLException {
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
}
