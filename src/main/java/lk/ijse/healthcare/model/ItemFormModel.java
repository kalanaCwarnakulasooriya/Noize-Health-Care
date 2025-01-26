package lk.ijse.healthcare.model;

import lk.ijse.healthcare.dto.ItemFormDto;
import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dto.tm.ItemTM;
import lk.ijse.healthcare.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemFormModel {
    public ArrayList<String> getAllItemNames() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT Name FROM item");
        ArrayList<String> itemNames = new ArrayList<>();
        while (rst.next()) {
            itemNames.add(rst.getString("Name"));
        }
        return itemNames;
    }

    public ItemFormDto findById(String name) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM item WHERE Name = ?", name);
        if (rst.next()) {
            return new ItemFormDto(
                    rst.getInt("ItemId"),
                    rst.getString("Name"),
                    rst.getString("Description"),
                    rst.getString("ExpireDate").toString(),
                    rst.getString("PackSize"),
                    rst.getDouble("UnitPrice"),
                    rst.getInt("StockQuantity")
            );
        }
        return null;
    }

    public boolean reduceQty(OrderDetailsFormDto orderDetailsFormDto) throws SQLException {
        return SQLUtil.execute(
                "UPDATE item SET StockQuantity = StockQuantity - ? WHERE ItemId = ?",
                orderDetailsFormDto.getQuantity(),
                orderDetailsFormDto.getItemId()
        );
    }
    public ArrayList<ItemTM> getStock(){
        String sql = "SELECT * FROM item";
        ArrayList<ItemTM> stock = new ArrayList<>();
        try{
            ResultSet rst = SQLUtil.execute(sql);
            while (rst.next()){
                ItemTM item = new ItemTM();
                item.setName(rst.getString("Name"));
                item.setDescription(rst.getString("Description"));
                item.setExpireDate(String.valueOf(rst.getDate("ExpireDate")));
                item.setPackSize(rst.getString("PackSize"));
                item.setUnitPrice(rst.getDouble("UnitPrice"));
                item.setStockQty(rst.getInt("StockQuantity"));

                stock.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stock;
    }

    public Boolean isUpdateStock(ItemFormDto itemFormDto) throws SQLException {
        return SQLUtil.execute(
                "UPDATE item SET Description = ?,ExpireDate = ?, PackSize = ?, UnitPrice = ?, StockQuantity = ? WHERE Name = ?",
                itemFormDto.getDescription(),
                itemFormDto.getExpireDate(),
                itemFormDto.getPackSize(),
                itemFormDto.getPrice(),
                itemFormDto.getQty(),
                itemFormDto.getName()
        );
    }

    public Boolean isAddStock(String name, String description, String expireDate, String packSize, double unitPrice, int qty) {
        String sql = "INSERT INTO item(Name,Description,ExpireDate,PackSize,UnitPrice,StockQuantity) VALUES (?,?,?,?,?,?)";
        try {
            SQLUtil.execute(sql, name,description,expireDate,packSize,unitPrice,qty);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getQty(String name) {
        String sql = "SELECT StockQuantity FROM item WHERE Name = ?";
        try {
            ResultSet rst = SQLUtil.execute(sql, name);
            if (rst.next()) {
                return rst.getInt("StockQuantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean deleteItem(String name) throws SQLException {
        return SQLUtil.execute(
                "DELETE FROM item WHERE Name = ?",
                name
        );
    }

    public ArrayList<ItemTM> searchStock(String name) throws SQLException {
        String sql = "select * from item where Name like ?";
        ResultSet rst = SQLUtil.execute(sql, name+"%");
        ArrayList<ItemTM> stock = new ArrayList<>();
        while (rst.next()) {
            ItemTM stockItem = new ItemTM(
                    rst.getString("Name"),
                    rst.getString("Description"),
                    rst.getString("ExpireDate"),
                    rst.getString("PackSize"),
                    rst.getDouble("UnitPrice"),
                    rst.getInt("StockQuantity")
            );
            stock.add(stockItem);
        }
        return stock;
    }
}
