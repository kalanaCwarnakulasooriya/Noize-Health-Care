package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.custom.PrescriptionDAO;
import lk.ijse.healthcare.dto.PrescriptionFormDto;
import lk.ijse.healthcare.dto.tm.PrescriptionTM;
import lk.ijse.healthcare.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrescriptionDAOImpl implements PrescriptionDAO {
    @Override
    public ArrayList<PrescriptionTM> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM prescription");
        ArrayList<PrescriptionTM> prescription = new ArrayList<>();
        while (rst.next()) {
            PrescriptionTM prescriptionTM = new PrescriptionTM(
                    rst.getString("MedicineDetails"),
                    rst.getString("Dosage"),
                    rst.getString("PrescriptionDate"),
                    rst.getString("UserId"),
                    rst.getString("DoctorId")
            );
            prescription.add(prescriptionTM);
        }
        return prescription;
    }

    @Override
    public ArrayList<PrescriptionTM> search(String name) throws SQLException {
        return null;
    }

    @Override
    public PrescriptionTM findById(String selectedContact) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getAllS() throws SQLException {
        return null;
    }

    @Override
    public boolean save(PrescriptionTM prescription) throws SQLException {
        return SQLUtil.execute(
                "INSERT INTO prescription(PrescriptionDate,MedicineDetails,Dosage,UserId,DoctorId) VALUES (?,?,?,?,?)",
                prescription.getDate(),
                prescription.getMediDetails(),
                prescription.getDosage(),
                prescription.getUserId(),
                prescription.getDoctorId()
        );
    }

    @Override
    public boolean update(PrescriptionTM prescriptionTM) throws SQLException {
        return SQLUtil.execute("UPDATE prescription SET MedicineDetails = ?, Dosage = ? WHERE PrescriptionDate = ?",
                prescriptionTM.getMediDetails(),
                prescriptionTM.getDosage(),
                prescriptionTM.getDate()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM prescription WHERE PrescriptionDate = ?", id);
    }

    @Override
    public int getIdByDescription(String description) throws SQLException {
        return 0;
    }
}
