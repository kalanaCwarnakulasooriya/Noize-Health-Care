package lk.ijse.healthcare.bo.custom;

import lk.ijse.healthcare.dto.tm.PrescriptionTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PrescriptionBO {
    public ArrayList<PrescriptionTM> getAllPrescription() throws SQLException;
    public ArrayList<PrescriptionTM> searchPrescription(String name) throws SQLException;
    public PrescriptionTM findById(String selectedContact) throws SQLException;
    public ArrayList<String> getAllS() throws SQLException;
    public boolean savePrescription(PrescriptionTM dto) throws SQLException;
    public boolean updatePrescription(PrescriptionTM prescriptionTM) throws SQLException;
    public boolean deletePrescription(String id) throws SQLException;
    public int getIdByDescription(String description) throws SQLException;
}
