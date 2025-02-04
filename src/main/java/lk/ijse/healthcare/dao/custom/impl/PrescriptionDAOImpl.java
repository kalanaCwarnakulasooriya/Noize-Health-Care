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
    public ArrayList<PrescriptionFormDto> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM prescription");
        ArrayList<PrescriptionFormDto> prescription = new ArrayList<>();
        while (rst.next()) {
            PrescriptionFormDto dto = new PrescriptionFormDto(
                    rst.getInt("PrescriptionId"),
                    rst.getString("Dosage"),
                    rst.getString("PrescriptionDate"),
                    rst.getString("MedicineDetails"),
                    rst.getInt("UserId"),
                    rst.getString("DoctorId")
            );
            prescription.add(dto);
        }
        return prescription;
    }

    @Override
    public ArrayList<PrescriptionFormDto> search(String search) throws SQLException {
        return null;
    }

    @Override
    public PrescriptionFormDto findById(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getAllS() throws SQLException {
        return null;
    }

    @Override
    public boolean save(PrescriptionFormDto save) throws SQLException {
        return SQLUtil.execute(
                "INSERT INTO prescription(MedicineDetails,Dosage,PrescriptionDate,UserId,DoctorId) VALUES (?,?,?,?,?)",
                save.getMediDetails(),
                save.getDosage(),
                save.getDate(),
                save.getUserId(),
                save.getDoctorId()
        );
    }

    @Override
    public HashMap<String, String> status() throws SQLException {
        return null;
    }

    @Override
    public boolean changePwd(PrescriptionFormDto user, String newPwd) throws SQLException {
        return false;
    }

    @Override
    public double getOrder(String orderId) throws SQLException {
        return 0;
    }

    @Override
    public boolean saveOrderDetails(ArrayList<PrescriptionFormDto> saveOrder) throws SQLException {
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
    public ResultSet btnLogin(PrescriptionFormDto login) throws Exception {
        return null;
    }

    @Override
    public boolean update(PrescriptionFormDto update) throws SQLException {
        return SQLUtil.execute("UPDATE prescription SET MedicineDetails = ?, Dosage = ?, WHERE PrescriptionDate = ?",
                update.getMediDetails(),
                update.getDosage(),
                update.getDate()
        );
    }

    @Override
    public int getIdBy(String id) throws SQLException {
        return 0;
    }

    @Override
    public boolean delete(String delete) throws SQLException {
        return SQLUtil.execute("DELETE FROM prescription WHERE PrescriptionDate = ?", delete);
    }

}
