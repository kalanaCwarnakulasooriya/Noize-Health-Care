package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.ItemBO;
import lk.ijse.healthcare.dao.DAOFactory;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.ItemDAO;
import lk.ijse.healthcare.dao.custom.impl.ItemDAOImpl;
import lk.ijse.healthcare.dto.ItemFormDto;
import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dto.tm.ItemTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public ArrayList<String> getAllSItem() throws SQLException {
        ArrayList<String> itemNames = itemDAO.getAllS();
        ArrayList<String> names = new ArrayList<>();
        for (String name : itemNames) {
            names.add(name);
        }
        return names;
    }

    @Override
    public ItemFormDto findByItemId(String id) throws SQLException {
        return itemDAO.findById(id);
    }

    @Override
    public boolean reduceQty(OrderDetailsFormDto reduce) throws SQLException {
        return itemDAO.reduceQty(reduce);
    }
    @Override
    public ArrayList<ItemFormDto> getAllItem() throws SQLException {
        ArrayList<ItemFormDto> stock = itemDAO.getAll();
        ArrayList<ItemFormDto> items = new ArrayList<>();
        for (ItemFormDto item : stock) {
            ItemFormDto dto = new ItemFormDto();
            dto.setItemId(item.getItemId());
            dto.setName(item.getName());
            dto.setDescription(item.getDescription());
            dto.setExpireDate(item.getExpireDate());
            dto.setPackSize(item.getPackSize());
            dto.setUnitPrice(item.getUnitPrice());
            dto.setStockQty(item.getStockQty());
            items.add(dto);
        }
        return items;
    }

    @Override
    public boolean updateItem(ItemFormDto update) throws SQLException {
        ItemFormDto items = new ItemFormDto(update.getItemId(),update.getName(), update.getDescription(), update.getExpireDate(), update.getPackSize(), update.getUnitPrice(), update.getStockQty());
        return itemDAO.update(items);
    }

    @Override
    public boolean saveItem(ItemFormDto save) throws SQLException {
        ItemFormDto items = new ItemFormDto(save.getItemId(),save.getName(), save.getDescription(), save.getExpireDate(), save.getPackSize(), save.getUnitPrice(), save.getStockQty());
        return itemDAO.save(items);
    }

    @Override
    public int getIdByItem(String id) throws SQLException {
        return itemDAO.getIdBy(id);
    }

    @Override
    public boolean deleteItem(String delete) throws SQLException {
        return itemDAO.delete(delete);
    }

    @Override
    public ArrayList<ItemFormDto> searchItem(String search) throws SQLException {
        ArrayList<ItemFormDto> items = itemDAO.search(search);
        ArrayList<ItemFormDto> itemDto = new ArrayList<>();
        for (ItemFormDto item : items) {
            ItemFormDto dto = new ItemFormDto(
                    item.getItemId(),
                    item.getName(),
                    item.getDescription(),
                    item.getExpireDate(),
                    item.getPackSize(),
                    item.getUnitPrice(),
                    item.getStockQty()
            );
            itemDto.add(dto);
        }
        return itemDto;
    }
}
