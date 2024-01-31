package lk.ijse.gdse66.hello.bo.custom;

import lk.ijse.gdse66.hello.bo.SuperBo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBo {
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException ;
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException ;

    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

}
