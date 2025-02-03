package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.custom.PrescriptionDAO;
import lk.ijse.healthcare.dto.PrescriptionFormDto;
import lk.ijse.healthcare.dto.tm.PrescriptionTM;
import lk.ijse.healthcare.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class PrescriptionDAOImpl implements PrescriptionDAO {
    @Override
    public ArrayList<PrescriptionTM> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM prescription");
        ArrayList<PrescriptionTM> prescription = new ArrayList<>();
        while (rst.next()) {
            PrescriptionTM prescriptionTM = new PrescriptionTM(
                    rst.getInt("PrescriptionId"),
                    rst.getString("Dosage"),
                    rst.getString("PrescriptionDate"),
                    rst.getString("MedicineDetails"),
                    rst.getInt("UserId"),
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
                "INSERT INTO prescription(MedicineDetails,Dosage,PrescriptionDate,UserId,DoctorId) VALUES (?,?,?,?,?)",
                prescription.getMediDetails(),
                prescription.getDosage(),
                prescription.getDate(),
                prescription.getUserId(),
                prescription.getDoctorId()
        );
    }

    @Override
    public HashMap<String, String> status() throws SQLException {
        return null;
    }

    @Override
    public boolean changePwd(PrescriptionTM user, String newPassword) throws SQLException {
        return false;
    }

    @Override
    public double getOrder(String orderId) throws SQLException {
        return 0;
    }

    @Override
    public boolean saveOrderDetails(ArrayList<PrescriptionTM> orderDetailsDto) throws SQLException {
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
    public ResultSet btnLogin(PrescriptionTM loginFormDto) throws Exception {
        return null;
    }

    @Override
    public boolean update(PrescriptionTM prescriptionTM) throws SQLException {
        return SQLUtil.execute("UPDATE prescription SET MedicineDetails = ?, Dosage = ?, WHERE PrescriptionDate = ?",
                prescriptionTM.getMediDetails(),
                prescriptionTM.getDosage(),
                prescriptionTM.getDate()
        );
    }

    @Override
    public int getIdBy(String name) throws SQLException {
        return 0;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM prescription WHERE PrescriptionDate = ?", id);
    }

}
