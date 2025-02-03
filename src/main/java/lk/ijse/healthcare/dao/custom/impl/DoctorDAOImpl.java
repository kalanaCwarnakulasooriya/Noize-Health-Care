package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.custom.DoctorDAO;
import lk.ijse.healthcare.dto.DoctorFormDto;
import lk.ijse.healthcare.dto.tm.DoctorTM;
import lk.ijse.healthcare.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public ArrayList<DoctorTM> getAll() throws SQLException {
        ArrayList<DoctorTM> doctorTMS = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("select * from doctor");
        while (rst.next()){
            DoctorTM newDoctors = new DoctorTM(
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
    public ArrayList<DoctorTM> search(String name) throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from doctor where Name like ?", name+"%");
        ArrayList<DoctorTM> doctorTMS = new ArrayList<>();
        while (rst.next()) {
            DoctorTM newDoctors = new DoctorTM(
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
    public DoctorTM findById(String selectedName) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM doctor WHERE DoctorId = ?", selectedName);
        if (rst.next()) {
            return new DoctorTM(
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
    public boolean update(DoctorTM doctorFormDto) throws SQLException {
        return SQLUtil.execute("UPDATE doctor SET Name = ?, Email = ?, ContactNumber = ?, Address = ? WHERE DoctorId = ?",
                doctorFormDto.getName(),
                doctorFormDto.getEmail(),
                doctorFormDto.getContactNumber(),
                doctorFormDto.getAddress(),
                doctorFormDto.getId());
    }

    @Override
    public boolean save(DoctorTM doctorFormDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO doctor VALUES (?,?,?,?,?,?)",
                doctorFormDto.getId(),
                doctorFormDto.getName(),
                doctorFormDto.getEmail(),
                doctorFormDto.getContactNumber(),
                doctorFormDto.getAddress(),
                doctorFormDto.getUserId()
        );
    }
}
