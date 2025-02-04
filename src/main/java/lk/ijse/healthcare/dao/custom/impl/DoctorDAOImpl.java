package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.custom.DoctorDAO;
import lk.ijse.healthcare.dto.DoctorFormDto;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.entity.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDAOImpl implements DoctorDAO {
    @Override
    public ArrayList<String> getAllS() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT DoctorId FROM doctor");
        ArrayList<String> docId = new ArrayList<>();
        while (rst.next()) {
            docId.add(rst.getString("DoctorId"));
        }
        return docId;
    }

    @Override
    public ArrayList<Doctor> getAll() throws SQLException {
        ArrayList<Doctor> doctorDto = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("select * from doctor");
        while (rst.next()){
            Doctor doctor = new Doctor(
                    rst.getInt("DoctorId"),
                    rst.getString("Name"),
                    rst.getString("Email"),
                    rst.getString("ContactNumber"),
                    rst.getString("Address"),
                    rst.getInt("UserId")
            );
            doctorDto.add(doctor);
        }
        return doctorDto;
    }

    @Override
    public ArrayList<Doctor> search(String search) throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from doctor where Name like ?", search+"%");
        ArrayList<Doctor> doctorDto = new ArrayList<>();
        while (rst.next()) {
            Doctor doctor = new Doctor(
                    rst.getInt("DoctorId"),
                    rst.getString("Name"),
                    rst.getString("Email"),
                    rst.getString("ContactNumber"),
                    rst.getString("Address"),
                    rst.getInt("UserId")
            );
            doctorDto.add(doctor);
        }
        return doctorDto;
    }

    @Override
    public Doctor findById(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM doctor WHERE DoctorId = ?", id);
        if (rst.next()) {
            return new Doctor(
                    rst.getInt("DoctorId"),
                    rst.getString("Name"),
                    rst.getString("Email"),
                    rst.getString("ContactNumber"),
                    rst.getString("Address"),
                    rst.getInt("UserId")
            );
        }
        return null;
    }

    @Override
    public boolean delete(String delete) throws SQLException {
        return SQLUtil.execute("DELETE FROM doctor WHERE Name = ?", delete);
    }

    @Override
    public int getIdBy(String id) throws SQLException {
        return 0;
    }

    @Override
    public boolean update(Doctor update) throws SQLException {
        return SQLUtil.execute("UPDATE doctor SET Email = ?, ContactNumber = ?, Address = ? WHERE Name = ?",
                update.getEmail(),
                update.getContactNumber(),
                update.getAddress(),
                update.getName()
        );
    }

    @Override
    public boolean save(Doctor save) throws SQLException {
        return SQLUtil.execute("INSERT INTO doctor VALUES (?,?,?,?,?,?)",
                save.getId(),
                save.getName(),
                save.getEmail(),
                save.getContactNumber(),
                save.getAddress(),
                save.getUserId()
        );
    }

    @Override
    public HashMap<String, String> status() throws SQLException {
        return null;
    }

    @Override
    public boolean changePwd(Doctor user, String newPwd) throws SQLException {
        return false;
    }

    @Override
    public double getOrder(String orderId) throws SQLException {
        return 0;
    }

    @Override
    public boolean saveOrderDetails(ArrayList<Doctor> saveOrder) throws SQLException {
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
    public ResultSet btnLogin(Doctor login) throws Exception {
        return null;
    }
}
