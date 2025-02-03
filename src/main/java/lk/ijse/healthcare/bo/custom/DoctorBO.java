package lk.ijse.healthcare.bo.custom;

import lk.ijse.healthcare.bo.SuperBO;
import lk.ijse.healthcare.dto.tm.DoctorTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DoctorBO extends SuperBO {
    public ArrayList<String> getAllSDoctor() throws SQLException;
    public ArrayList<DoctorTM> getAllDoctor() throws SQLException;
    public ArrayList<DoctorTM> searchDoctor(String name) throws SQLException;
    public DoctorTM findByDoctorId(String selectedName) throws SQLException;
    public boolean deleteDoctor(String name) throws SQLException;
    public int getIdByDescription(String description) throws SQLException;
    public boolean updateDoctor(DoctorTM doctorFormDto) throws SQLException;
    public boolean saveDoctor(DoctorTM doctorFormDto) throws SQLException;
}
