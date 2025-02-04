package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.custom.PatientsDAO;
import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dto.PatientsFormDto;
import lk.ijse.healthcare.dto.tm.AppointmentTM;
import lk.ijse.healthcare.dto.tm.PatientsTM;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.entity.Patients;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class PatientsDAOImpl implements PatientsDAO {
    @Override
    public ArrayList<Patients> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM patient");

        ArrayList<Patients> patients = new ArrayList<>();
        while (rst.next()) {
            Patients dto = new Patients(
                    rst.getString("Name"),
                    rst.getString("Address"),
                    rst.getString("ContactNumber"),
                    rst.getString("Email"),
                    rst.getDate("DOB"),
                    rst.getString("Gender"),
                    rst.getDate("RegistrationDate")
            );
            patients.add(dto);
        }
        return patients;
    }

    @Override
    public ArrayList<Patients> search(String search) throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from patient where Name like ?", search+"%");
        ArrayList<Patients> patient = new ArrayList<>();
        while (rst.next()) {
            Patients dto = new Patients(
                    rst.getString("Name"),
                    rst.getString("Address"),
                    rst.getString("ContactNumber"),
                    rst.getString("Email"),
                    rst.getDate("DOB"),
                    rst.getString("Gender"),
                    rst.getDate("RegistrationDate")
            );
            patient.add(dto);
        }
        return patient;
    }

    @Override
    public Patients findById(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM patient WHERE PatientId = ?", id);

        if (rst.next()) {
            return new Patients(
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
        ArrayList<String> patient = new ArrayList<>();
        while (rst.next()) {
            patient.add(rst.getString("PatientId"));
        }
        return patient;
    }

    @Override
    public boolean delete(String delete) throws SQLException {
        return SQLUtil.execute("DELETE FROM patient WHERE Name = ?", delete);
    }

    @Override
    public boolean update(Patients update) throws SQLException {
        return SQLUtil.execute(
                "UPDATE patient SET Address = ?, ContactNumber = ?, Email = ?, DOB = ?, Gender = ?, RegistrationDate = ? WHERE Name = ?",
                update.getPatientsAddress(),
                update.getPatientsContactNumber(),
                update.getPatientsEmail(),
                update.getPatientsDob(),
                update.getPatientsGender(),
                update.getPatientsRegDate(),
                update.getPatientsName()
                );
    }

    @Override
    public int getIdBy(String id) throws SQLException {
        return 0;
    }

    @Override
    public boolean save(Patients save) throws SQLException {
        return false;
    }

    @Override
    public HashMap<String, String> status() throws SQLException {
        return null;
    }

    @Override
    public boolean changePwd(Patients user, String newPwd) throws SQLException {
        return false;
    }

    @Override
    public double getOrder(String orderId) throws SQLException {
        return 0;
    }

    @Override
    public boolean saveOrderDetails(ArrayList<Patients> saveOrder) throws SQLException {
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
    public ResultSet btnLogin(Patients login) throws Exception {
        return null;
    }
}
