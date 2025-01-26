package lk.ijse.healthcare.model;

import lk.ijse.healthcare.dto.DoctorFormDto;
import lk.ijse.healthcare.dto.tm.DoctorTM;
import lk.ijse.healthcare.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorFormModel {
    public ArrayList<String> getAllDocId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT DoctorId FROM doctor");
        ArrayList<String> docId = new ArrayList<>();
        while (rst.next()) {
            docId.add(rst.getString("DoctorId"));
        }
        return docId;
    }

    public ArrayList<DoctorTM> getAllDoctors() throws SQLException {
        String sql = "select * from doctor";
        ArrayList<DoctorTM> doctorTMS = new ArrayList<>();
        try{
            ResultSet rst = SQLUtil.execute(sql);
            while (rst.next()){
                DoctorTM newDoctors = new DoctorTM(
                        rst.getString("Name"),
                        rst.getString("Email"),
                        rst.getString("ContactNumber"),
                        rst.getString("Address")
                );
                doctorTMS.add(newDoctors);
            }
            return doctorTMS;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctorTMS;
    }

    public ArrayList<DoctorTM> searchDoctors(String name) throws SQLException {
        String sql = "select * from doctor where Name like ?";
        ResultSet rst = SQLUtil.execute(sql, name+"%");
        ArrayList<DoctorTM> doctorTMS = new ArrayList<>();
        while (rst.next()) {
            DoctorTM newDoctors = new DoctorTM(
                    rst.getString("Name"),
                    rst.getString("Email"),
                    rst.getString("ContactNumber"),
                    rst.getString("Address")
            );
            doctorTMS.add(newDoctors);
        }
        return doctorTMS;
    }

    public DoctorTM findById(String selectedName) throws SQLException {
        ResultSet rst = SQLUtil.execute(
                "SELECT * FROM doctor WHERE DoctorId = ?",
                selectedName
        );
        if (rst.next()) {
            return new DoctorTM(
                    rst.getString("Name"),
                    rst.getString("Email"),
                    rst.getString("ContactNumber"),
                    rst.getString("Address")
            );
        }
        return null;
    }

    public boolean deleteDoctor(String name) throws SQLException {
        return SQLUtil.execute(
                "DELETE FROM doctor WHERE Name = ?",
                name
        );
    }

    public boolean isUpdateDoctor(DoctorFormDto doctorFormDto) throws SQLException {
        return SQLUtil.execute("UPDATE doctor SET Name = ?, Email = ?, ContactNumber = ?, Address = ? WHERE DoctorId = ?",
                doctorFormDto.getName(),
                doctorFormDto.getEmail(),
                doctorFormDto.getContactNumber(),
                doctorFormDto.getAddress(),
                doctorFormDto.getId());
    }

    public boolean isSaveDoctor(DoctorFormDto doctorFormDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO doctor VALUES (?,?,?,?,?,?)",
                doctorFormDto.getId(),
                doctorFormDto.getName(),
                doctorFormDto.getEmail(),
                doctorFormDto.getContactNumber(),
                doctorFormDto.getAddress(),
                doctorFormDto.getUserId()
        );
    }

    public ArrayList<DoctorTM> getDoctor(){
        String sql = "SELECT * FROM doctor";
        ArrayList<DoctorTM> doctor = new ArrayList<>();
        try{
            ResultSet rst = SQLUtil.execute(sql);
            while (rst.next()){
                DoctorTM doctorTM = new DoctorTM();
                doctorTM.setName(rst.getString("Name"));
                doctorTM.setEmail(rst.getString("Email"));
                doctorTM.setContactNumber(rst.getString("ContactNumber"));
                doctorTM.setAddress(rst.getString("Address"));

                doctor.add(doctorTM);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return doctor;
    }
}
