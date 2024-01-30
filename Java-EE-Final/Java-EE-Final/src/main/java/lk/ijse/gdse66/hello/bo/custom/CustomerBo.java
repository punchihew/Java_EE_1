package lk.ijse.gdse66.hello.bo.custom;


import lk.ijse.gdse66.hello.bo.SuperBo;
import lk.ijse.gdse66.hello.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo extends SuperBo {
    public ArrayList<CustomerDTO> getAllCustomers(Connection connection) throws SQLException, ClassNotFoundException;

    public boolean saveCustomer(Connection connection, CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public boolean updateCustomer(Connection connection , CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(Connection connection,String customerID) throws SQLException, ClassNotFoundException;
}
