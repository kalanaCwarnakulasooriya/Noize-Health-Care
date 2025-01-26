package lk.ijse.healthcare.model;

import lk.ijse.healthcare.db.DBConnection;
import lk.ijse.healthcare.dto.OrdersFormDto;
import lk.ijse.healthcare.dao.SQLUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersFormModel {
    private final OrderDetailsFormModel orderDetailsFormModel = new OrderDetailsFormModel();

    public String getNewOrderId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT OrderId FROM orders ORDER BY OrderId DESC LIMIT 1");

        if (rst.next()) {
            int lastId = rst.getInt("OrderId");
            int newIdIndex = lastId + 1;
            return String.format("%1d", newIdIndex);
        }
        return "1";
    }

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
                boolean isOrderDetailListSaved = orderDetailsFormModel.saveOrderDetailsList(orderDTO.getOrderDetailsFormDtos());
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
