package lk.ijse.healthcare.bo.custom;

import lk.ijse.healthcare.bo.SuperBO;
import lk.ijse.healthcare.dto.PrescriptionFormDto;
import lk.ijse.healthcare.dto.tm.PrescriptionTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PrescriptionBO extends SuperBO {
    public ArrayList<PrescriptionFormDto> getAllPrescription() throws SQLException;
    public ArrayList<PrescriptionFormDto> searchPrescription(String search) throws SQLException;
    public PrescriptionFormDto findById(String id) throws SQLException;
    public ArrayList<String> getAllS() throws SQLException;
    public boolean savePrescription(PrescriptionFormDto save) throws SQLException;
    public boolean updatePrescription(PrescriptionFormDto update) throws SQLException;
    public boolean deletePrescription(String delete) throws SQLException;
    public int getIdByDescription(String description) throws SQLException;
}
