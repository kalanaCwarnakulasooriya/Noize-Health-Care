package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.OrdersBO;
import lk.ijse.healthcare.dao.DAOFactory;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.ItemDAO;
import lk.ijse.healthcare.dao.custom.OrderDetailsDAO;
import lk.ijse.healthcare.dao.custom.OrdersDAO;
import lk.ijse.healthcare.dao.custom.impl.ItemDAOImpl;
import lk.ijse.healthcare.dao.custom.impl.OrderDetailsDAOImpl;
import lk.ijse.healthcare.dao.custom.impl.OrdersDAOImpl;
import lk.ijse.healthcare.db.DBConnection;
import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dto.OrdersFormDto;
import lk.ijse.healthcare.entity.OrderDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersBOImpl implements OrdersBO {
    OrderDetailsDAO orderDetailsDAOImpl = (OrderDetailsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);
    OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public boolean save(OrderDetailsFormDto save) throws SQLException {
        OrderDetails dto = new OrderDetails(save.getOrderId(), save.getItemId(), save.getQuantity(), save.getPrice());
        return orderDetailsDAOImpl.save(dto);
    }

    @Override
    public double getOrder(String orderId) throws SQLException {
        return ordersDAO.getOrder(orderId);
    }

    @Override
    public String getNewOrderId() throws SQLException {
        return ordersDAO.getNewOrderId();
    }

    @Override
    public boolean saveOrder(OrdersFormDto save) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean isOrderSaved = SQLUtil.execute("insert into orders values (?,?,?)", save.getOrderId(), save.getOrderDate(), save.getPatientId());

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
