package lk.ijse.healthcare.model;

import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsFormModel {
    private final ItemFormModel itemFormModel = new ItemFormModel();

    public boolean saveOrderDetailsList(ArrayList<OrderDetailsFormDto> orderDetailsDto) throws SQLException {
        for (OrderDetailsFormDto orderDetailsDTO : orderDetailsDto) {
            boolean isOrderDetailsSaved = saveOrderDetails(orderDetailsDTO);
            if (!isOrderDetailsSaved) {
                return false;
            }

            boolean isItemUpdated = itemFormModel.reduceQty(orderDetailsDTO);
            if (!isItemUpdated) {
                return false;
            }
        }
        return true;
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
