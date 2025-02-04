package lk.ijse.healthcare.bo.custom;

import lk.ijse.healthcare.bo.SuperBO;
import lk.ijse.healthcare.dto.DoctorFormDto;
import lk.ijse.healthcare.dto.tm.DoctorTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DoctorBO extends SuperBO {
    public ArrayList<String> getAllSDoctor() throws SQLException;
    public ArrayList<DoctorFormDto> getAllDoctor() throws SQLException;
    public ArrayList<DoctorFormDto> searchDoctor(String search) throws SQLException;
    public DoctorFormDto findByDoctorId(String id) throws SQLException;
    public boolean deleteDoctor(String delete) throws SQLException;
    public int getIdByDescription(String description) throws SQLException;
    public boolean updateDoctor(DoctorFormDto update) throws SQLException;
    public boolean saveDoctor(DoctorFormDto save) throws SQLException;
}
