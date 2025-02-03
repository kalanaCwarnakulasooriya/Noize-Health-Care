package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.custom.OrderDetailsDAO;
import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    private final ItemDAOImpl itemDAOImpl = new ItemDAOImpl();

    @Override
    public boolean saveOrderDetails(ArrayList<OrderDetailsFormDto> orderDetailsDto) throws SQLException {
        for (OrderDetailsFormDto orderDetailsDTO : orderDetailsDto) {
            boolean isOrderDetailsSaved = save(orderDetailsDTO);
            if (!isOrderDetailsSaved) {
                return false;
            }

            boolean isItemUpdated = itemDAOImpl.reduceQty(orderDetailsDTO);
            if (!isItemUpdated) {
                return false;
            }
        }
        return true;
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
    public boolean save(OrderDetailsFormDto orderDetailsDTO) throws SQLException {
        return SQLUtil.execute(
                "insert into orderdetail(Quantity,Price,OrderId,ItemId) values (?,?,?,?)",
                orderDetailsDTO.getQuantity(),
                orderDetailsDTO.getPrice(),
                orderDetailsDTO.getOrderId(),
                orderDetailsDTO.getItemId()
        );
    }

    @Override
    public double getOrder(String orderId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT SUM(Quantity * price) AS TotalAmount FROM orderdetail WHERE OrderId = ?", orderId);
        if (rst.next()) {
            return rst.getDouble("TotalAmount");
        }
        return 0.0;
    }

    @Override
    public ArrayList<OrderDetailsFormDto> getAll() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<OrderDetailsFormDto> search(String name) throws SQLException {
        return null;
    }

    @Override
    public OrderDetailsFormDto findById(String name) throws SQLException {
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
    public boolean update(OrderDetailsFormDto dto) throws SQLException {
        return false;
    }

    @Override
    public int getIdBy(String name) throws SQLException {
        return 0;
    }

    @Override
    public HashMap<String, String> status() throws SQLException {
        return null;
    }

    @Override
    public boolean changePwd(OrderDetailsFormDto user, String newPassword) throws SQLException {
        return false;
    }
}
