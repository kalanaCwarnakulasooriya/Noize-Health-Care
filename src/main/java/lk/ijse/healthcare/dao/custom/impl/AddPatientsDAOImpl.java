package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.custom.AddPatientsDAO;
import lk.ijse.healthcare.dto.AddPatientFormDto;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dto.tm.AppointmentTM;
import lk.ijse.healthcare.entity.AddPatient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class AddPatientsDAOImpl implements AddPatientsDAO {
    @Override
    public ArrayList<AddPatient> getAll() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<AddPatient> search(String search) throws SQLException {
        return null;
    }

    @Override
    public AddPatient findById(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String delete) throws SQLException {
        return false;
    }

    @Override
    public boolean update(AddPatient update) throws SQLException {
        return false;
    }

    @Override
    public int getIdBy(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT GenderId FROM Gender WHERE Description = ?", id);
            if (rst.next()) {
                return rst.getInt("GenderId");
            }
        return -1;
    }

    @Override
    public ArrayList<String> getAllS() throws SQLException {
        ResultSet rstGender = SQLUtil.execute("SELECT Description FROM gender");

        ArrayList<String> gender = new ArrayList<>();
        while (rstGender.next()) {
            gender.add(rstGender.getString("Description"));
        }
        return gender;
    }

    @Override
    public boolean save(AddPatient save) throws SQLException {
        return SQLUtil.execute(
                "INSERT INTO patient VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                save.getId(),
                save.getName(),
                save.getEmail(),
                save.getContactNumber(),
                save.getAddress(),
                save.getDob(),
                save.getGenderId(),
                save.getRegDate(),
                save.getUserId(),
                save.getPrescriptionId()
        );
    }

    @Override
    public HashMap<String, String> status() throws SQLException {
        return null;
    }

    @Override
    public boolean changePwd(AddPatient user, String newPwd) throws SQLException {
        return false;
    }

    @Override
    public double getOrder(String orderId) throws SQLException {
        return 0;
    }

    @Override
    public boolean saveOrderDetails(ArrayList<AddPatient> saveOrder) throws SQLException {
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
    public ResultSet btnLogin(AddPatient login) throws Exception {
        return null;
    }

}
