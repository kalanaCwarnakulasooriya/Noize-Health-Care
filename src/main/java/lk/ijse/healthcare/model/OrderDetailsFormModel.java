package lk.ijse.healthcare.model;

import lk.ijse.healthcare.dao.custom.impl.ItemDAOImpl;
import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsFormModel {
    private final ItemDAOImpl itemDAOImpl = new ItemDAOImpl();

    public boolean saveOrderDetailsList(ArrayList<OrderDetailsFormDto> orderDetailsDto) throws SQLException {
        for (OrderDetailsFormDto orderDetailsDTO : orderDetailsDto) {
            boolean isOrderDetailsSaved = saveOrderDetails(orderDetailsDTO);
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


    public boolean reduceQty(OrderDetailsFormDto orderDetailsFormDto) throws SQLException {
        return SQLUtil.execute("UPDATE item SET StockQuantity = StockQuantity - ? WHERE ItemId = ?", orderDetailsFormDto.getQuantity(), orderDetailsFormDto.getItemId());
    }

    private boolean saveOrderDetails(OrderDetailsFormDto orderDetailsDTO) throws SQLException {
        return SQLUtil.execute(
                "insert into orderdetail(Quantity,Price,OrderId,ItemId) values (?,?,?,?)",
                orderDetailsDTO.getQuantity(),
                orderDetailsDTO.getPrice(),
                orderDetailsDTO.getOrderId(),
                orderDetailsDTO.getItemId()
        );
    }

    public double getOrderTotalAmount(String orderId) throws SQLException {
        String query = "SELECT SUM(Quantity * price) AS TotalAmount FROM orderdetail WHERE OrderId = ?";
        ResultSet rst = SQLUtil.execute(query, orderId);

        if (rst.next()) {
            return rst.getDouble("TotalAmount");
        }
        return 0.0;
    }

}
