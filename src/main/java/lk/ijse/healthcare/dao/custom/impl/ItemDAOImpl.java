package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.custom.ItemDAO;
import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dto.tm.ItemTM;
import lk.ijse.healthcare.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public ItemTM findById(String name) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM item WHERE Name = ?", name);
        if (rst.next()) {
            return new ItemTM(
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

//    public boolean reduceQty(OrderDetailsFormDto orderDetailsFormDto) throws SQLException {
//        return SQLUtil.execute("UPDATE item SET StockQuantity = StockQuantity - ? WHERE ItemId = ?", orderDetailsFormDto.getQuantity(), orderDetailsFormDto.getItemId());
//    }
    @Override
    public ArrayList<ItemTM> getAll() throws SQLException {
        ArrayList<ItemTM> stock = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM item");
        while (rst.next()){
            ItemTM item = new ItemTM();
            item.setName(rst.getString("Name"));
            item.setDescription(rst.getString("Description"));
            item.setExpireDate(String.valueOf(rst.getDate("ExpireDate")));
            item.setPackSize(rst.getString("PackSize"));
            item.setUnitPrice(rst.getDouble("UnitPrice"));
            item.setQty(rst.getInt("StockQuantity"));

            stock.add(item);
        }
        return stock;
    }

    @Override
    public boolean update(ItemTM item) throws SQLException {
        return SQLUtil.execute(
                "UPDATE item SET Description = ?,ExpireDate = ?, PackSize = ?, UnitPrice = ?, StockQuantity = ? WHERE Name = ?",
                item.getDescription(),
                item.getExpireDate(),
                item.getPackSize(),
                item.getUnitPrice(),
                item.getQty(),
                item.getName()
        );
    }

    @Override
    public boolean save(ItemTM item) throws SQLException {
        return SQLUtil.execute("INSERT INTO item(Name,Description,ExpireDate,PackSize,UnitPrice,StockQuantity) VALUES (?,?,?,?,?,?)", item.getName(), item.getDescription(), item.getExpireDate(), item.getPackSize(), item.getUnitPrice(), item.getQty());
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
    public ArrayList<ItemTM> search(String name) throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from item where Name like ?", name+"%");
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
