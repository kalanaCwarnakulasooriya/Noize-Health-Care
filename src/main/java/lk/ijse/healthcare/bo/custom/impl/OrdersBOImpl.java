package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.OrdersBO;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.OrderDetailsDAO;
import lk.ijse.healthcare.dao.custom.OrdersDAO;
import lk.ijse.healthcare.dao.custom.impl.OrderDetailsDAOImpl;
import lk.ijse.healthcare.dao.custom.impl.OrdersDAOImpl;
import lk.ijse.healthcare.db.DBConnection;
import lk.ijse.healthcare.dto.OrdersFormDto;

import java.sql.Connection;
import java.sql.SQLException;

public class OrdersBOImpl implements OrdersBO {
    OrderDetailsDAO orderDetailsDAOImpl = new OrderDetailsDAOImpl();
    OrdersDAO ordersDAO = new OrdersDAOImpl();

    @Override
    public String getNewOrderId() throws SQLException {
        return ordersDAO.getNewOrderId();
    }

    @Override
    public boolean saveOrder(OrdersFormDto orderDTO) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean isOrderSaved = SQLUtil.execute(
                    "insert into orders values (?,?,?)",
                    orderDTO.getOrderId(),
                    orderDTO.getOrderDate(),
                    orderDTO.getPatientId()
            );

            if (isOrderSaved) {
                boolean isOrderDetailListSaved = orderDetailsDAOImpl.saveOrderDetails(orderDTO.getOrderDetailsFormDtos());
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
