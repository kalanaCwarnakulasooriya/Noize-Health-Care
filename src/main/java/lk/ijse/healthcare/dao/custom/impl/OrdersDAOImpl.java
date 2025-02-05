package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.custom.OrderDetailsDAO;
import lk.ijse.healthcare.dao.custom.OrdersDAO;
import lk.ijse.healthcare.db.DBConnection;
import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dto.OrdersFormDto;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.entity.Orders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class OrdersDAOImpl implements OrdersDAO {
    private final OrderDetailsDAO orderDetailsDAOImpl = new OrderDetailsDAOImpl();

    @Override
    public ArrayList<Orders> getAll() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Orders> search(String search) throws SQLException {
        return null;
    }

    @Override
    public Orders findById(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getAllS() throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String delete) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Orders update) throws SQLException {
        return false;
    }

    @Override
    public int getIdBy(String id) throws SQLException {
        return 0;
    }

    @Override
    public HashMap<String, String> status() throws SQLException {
        return null;
    }

    @Override
    public boolean changePwd(Orders user, String newPwd) throws SQLException {
        return false;
    }

    @Override
    public double getOrder(String orderId) throws SQLException {
        return 0;
    }

    @Override
    public boolean saveOrderDetails(ArrayList<Orders> saveOrder) throws SQLException {
        return false;
    }

    @Override
    public int getUserId() throws SQLException {
        return 0;
    }

    @Override
    public String getNewOrderId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT OrderId FROM orders ORDER BY OrderId DESC LIMIT 1");

        if (rst.next()) {
            int lastId = rst.getInt("OrderId");
            int newIdIndex = lastId + 1;
            return String.format("%1d", newIdIndex);
        }
        return "1";
    }

    @Override
    public ResultSet btnLogin(Orders login) throws Exception {
        return null;
    }

    @Override
    public boolean save(Orders save) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean isOrderSaved = SQLUtil.execute(
                    "insert into orders values (?,?,?)",
                    save.getOrderId(),
                    save.getOrderDate(),
                    save.getPatientId()
            );

            if (isOrderSaved) {
                boolean isOrderDetailListSaved = orderDetailsDAOImpl.saveOrderDetails(save.getOrderDetailsFormDtos());
                if (isOrderDetailListSaved) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
