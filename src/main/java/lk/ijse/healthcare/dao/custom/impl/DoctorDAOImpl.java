package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.custom.DoctorDAO;
import lk.ijse.healthcare.dto.DoctorFormDto;
import lk.ijse.healthcare.dao.SQLUtil;

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
    public ArrayList<DoctorFormDto> getAll() throws SQLException {
        ArrayList<DoctorFormDto> doctorTMS = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("select * from doctor");
        while (rst.next()){
            DoctorFormDto newDoctors = new DoctorFormDto(
                    rst.getInt("DoctorId"),
                    rst.getString("Name"),
                    rst.getString("Email"),
                    rst.getString("ContactNumber"),
                    rst.getString("Address"),
                    rst.getInt("UserId")
            );
            doctorTMS.add(newDoctors);
        }
        return doctorTMS;
    }

    @Override
    public ArrayList<DoctorFormDto> search(String name) throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from doctor where Name like ?", name+"%");
        ArrayList<DoctorFormDto> doctorTMS = new ArrayList<>();
        while (rst.next()) {
            DoctorFormDto newDoctors = new DoctorFormDto(
                    rst.getInt("DoctorId"),
                    rst.getString("Name"),
                    rst.getString("Email"),
                    rst.getString("ContactNumber"),
                    rst.getString("Address"),
                    rst.getInt("UserId")
            );
            doctorTMS.add(newDoctors);
        }
        return doctorTMS;
    }

    @Override
    public DoctorFormDto findById(String selectedName) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM doctor WHERE DoctorId = ?", selectedName);
        if (rst.next()) {
            return new DoctorFormDto(
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
    public boolean delete(String name) throws SQLException {
        return SQLUtil.execute("DELETE FROM doctor WHERE Name = ?", name);
    }

    @Override
    public int getIdBy(String description) throws SQLException {
        return 0;
    }

    @Override
    public boolean update(DoctorFormDto doctorFormDto) throws SQLException {
        return SQLUtil.execute("UPDATE doctor SET Email = ?, ContactNumber = ?, Address = ? WHERE Name = ?",
                doctorFormDto.getEmail(),
                doctorFormDto.getContactNumber(),
                doctorFormDto.getAddress(),
                doctorFormDto.getName()
        );
    }

    @Override
    public boolean save(DoctorFormDto doctorFormDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO doctor VALUES (?,?,?,?,?,?)",
                doctorFormDto.getId(),
                doctorFormDto.getName(),
                doctorFormDto.getEmail(),
                doctorFormDto.getContactNumber(),
                doctorFormDto.getAddress(),
                doctorFormDto.getUserId()
        );
    }

    @Override
    public HashMap<String, String> status() throws SQLException {
        return null;
    }

    @Override
    public boolean changePwd(DoctorFormDto user, String newPassword) throws SQLException {
        return false;
    }

    @Override
    public double getOrder(String orderId) throws SQLException {
        return 0;
    }

    @Override
    public boolean saveOrderDetails(ArrayList<DoctorFormDto> orderDetailsDto) throws SQLException {
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
    public ResultSet btnLogin(DoctorFormDto loginFormDto) throws Exception {
        return null;
    }
}
