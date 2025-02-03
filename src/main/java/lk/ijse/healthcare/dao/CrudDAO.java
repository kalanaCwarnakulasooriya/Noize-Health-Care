package lk.ijse.healthcare.dao;

import lk.ijse.healthcare.dto.ForgetPasswordFormDto;
import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dto.tm.AppointmentTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface CrudDAO<T> {
    public ArrayList<T> getAll() throws SQLException;
    public ArrayList<T> search(String name) throws SQLException;
    public T findById(String name) throws SQLException;
    public ArrayList<String> getAllS() throws SQLException;
    public boolean delete(String delete) throws SQLException;
    public boolean update(T dto) throws SQLException;
    public int getIdBy(String name) throws SQLException;
    public boolean save(T dto) throws SQLException;
    public HashMap<String, String> status() throws SQLException;
    public boolean changePwd(T user, String newPassword) throws SQLException;
    public double getOrder(String orderId) throws SQLException;
    public boolean saveOrderDetails(ArrayList<OrderDetailsFormDto> orderDetailsDto) throws SQLException;
    public int getUserId() throws SQLException;
    public String getNewOrderId() throws SQLException;
}
