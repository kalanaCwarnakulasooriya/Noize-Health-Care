package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.custom.ItemDAO;
import lk.ijse.healthcare.dto.ItemFormDto;
import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dto.tm.ItemTM;
import lk.ijse.healthcare.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<String> getAllS() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT Name FROM item");
        ArrayList<String> itemNames = new ArrayList<>();
        while (rst.next()) {
            itemNames.add(rst.getString("Name"));
        }
        return itemNames;
    }

    @Override
    public ItemFormDto findById(String name) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM item WHERE Name = ?", name);
        if (rst.next()) {
            return new ItemFormDto(
                    rst.getInt("ItemId"),
                    rst.getString("Name"),
                    rst.getString("Description"),
                    String.valueOf(rst.getDate("ExpireDate")),
                    rst.getString("PackSize"),
                    rst.getDouble("UnitPrice"),
                    rst.getInt("StockQuantity")
            );
        }
        return null;
    }

    @Override
    public boolean reduceQty(OrderDetailsFormDto orderDetailsFormDto) throws SQLException {
        return SQLUtil.execute("UPDATE item SET StockQuantity = StockQuantity - ? WHERE ItemId = ?", orderDetailsFormDto.getQuantity(), orderDetailsFormDto.getItemId());
    }

    @Override
    public ArrayList<ItemFormDto> getAll() throws SQLException {
        ArrayList<ItemFormDto> stock = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM item");
        while (rst.next()){
            ItemFormDto item = new ItemFormDto();
            item.setItemId(rst.getInt("ItemId"));
            item.setName(rst.getString("Name"));
            item.setDescription(rst.getString("Description"));
            item.setExpireDate(String.valueOf(rst.getDate("ExpireDate")));
            item.setPackSize(rst.getString("PackSize"));
            item.setUnitPrice(rst.getDouble("UnitPrice"));
            item.setStockQty(rst.getInt("StockQuantity"));

            stock.add(item);
        }
        return stock;
    }

    @Override
    public boolean update(ItemFormDto item) throws SQLException {
        return SQLUtil.execute(
                "UPDATE item SET Description = ?,ExpireDate = ?, PackSize = ?, UnitPrice = ?, StockQuantity = ? WHERE Name = ?",
                item.getDescription(),
                item.getExpireDate(),
                item.getPackSize(),
                item.getUnitPrice(),
                item.getStockQty(),
                item.getName()
        );
    }

    @Override
    public boolean save(ItemFormDto item) throws SQLException {
        return SQLUtil.execute("INSERT INTO item(Name,Description,ExpireDate,PackSize,UnitPrice,StockQuantity) VALUES (?,?,?,?,?,?)", item.getName(), item.getDescription(), item.getExpireDate(), item.getPackSize(), item.getUnitPrice(), item.getStockQty());
    }

    @Override
    public HashMap<String, String> status() throws SQLException {
        return null;
    }

    @Override
    public boolean changePwd(ItemFormDto user, String newPassword) throws SQLException {
        return false;
    }

    @Override
    public double getOrder(String orderId) throws SQLException {
        return 0;
    }

    @Override
    public boolean saveOrderDetails(ArrayList<ItemFormDto> orderDetailsDto) throws SQLException {
        return false;
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
    public ResultSet btnLogin(ItemFormDto loginFormDto) throws Exception {
        return null;
    }

    @Override
    public int getIdBy(String name) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT StockQuantity FROM item WHERE Name = ?", name);
        if (rst.next()) {
            return rst.getInt("StockQuantity");
        }
        return -1;
    }

    @Override
    public boolean delete(String name) throws SQLException {
        return SQLUtil.execute("DELETE FROM item WHERE Name = ?", name);
    }

    @Override
    public ArrayList<ItemFormDto> search(String name) throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from item where Name like ?", name+"%");
        ArrayList<ItemFormDto> stock = new ArrayList<>();
        while (rst.next()) {
            ItemFormDto stockItem = new ItemFormDto(
                    rst.getInt("ItemId"),
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
