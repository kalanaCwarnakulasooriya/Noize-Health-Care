package lk.ijse.healthcare.dao;

import lk.ijse.healthcare.dto.tm.AppointmentTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> {
    public ArrayList<T> getAll() throws SQLException;
    public ArrayList<T> search(String name) throws SQLException;
    public T findById(String name) throws SQLException;
    public ArrayList<String> getAllS() throws SQLException;
    public boolean delete(String delete) throws SQLException;
    public boolean update(T dto) throws SQLException;
    public int getIdBy(String name) throws SQLException;
    public boolean save(T dto) throws SQLException;
}
