package lk.ijse.healthcare.bo.custom;

import lk.ijse.healthcare.bo.SuperBO;
import lk.ijse.healthcare.dto.OrdersFormDto;

import java.sql.SQLException;

public interface OrdersBO extends SuperBO {
    public String getNewOrderId() throws SQLException;
    public boolean saveOrder(OrdersFormDto orderDTO) throws SQLException;
}
