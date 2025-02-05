package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.DAOFactory;
import lk.ijse.healthcare.dao.custom.ItemDAO;
import lk.ijse.healthcare.dao.custom.OrderDetailsDAO;
import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    private final ItemDAO itemDAOImpl = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean saveOrderDetails(ArrayList<OrderDetails> saveOrder) throws SQLException {
        for (OrderDetails dto : saveOrder) {
            boolean isOrderDetailsSaved = save(dto);
            if (!isOrderDetailsSaved) {
                return false;
            }

            boolean isItemUpdated = itemDAOImpl.reduceQty(dto);
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
    public ResultSet btnLogin(OrderDetails login) throws Exception {
        return null;
    }

    @Override
    public boolean save(OrderDetails save) throws SQLException {
        return SQLUtil.execute(
                "insert into orderdetail(Quantity,Price,OrderId,ItemId) values (?,?,?,?)",
                save.getQuantity(),
                save.getPrice(),
                save.getOrderId(),
                save.getItemId()
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
    public ArrayList<OrderDetails> getAll() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<OrderDetails> search(String search) throws SQLException {
        return null;
    }

    @Override
    public OrderDetails findById(String id) throws SQLException {
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
    public boolean update(OrderDetails update) throws SQLException {
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
    public boolean changePwd(OrderDetails user, String newPwd) throws SQLException {
        return false;
    }
}
