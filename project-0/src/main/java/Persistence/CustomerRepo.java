package Persistence;

import Util.ConnectionManager;

import java.io.IOException;
import java.sql.*;

public class CustomerRepo implements DataSourceCRUD<CustomerModel> {
    private final Connection connection;

    public CustomerRepo() {
        connection = ConnectionManager.getConnection();
    }

    @Override
    //adds new customer to database
    public Integer create(CustomerModel customerModel) {
        //JDBC logic here
        int customerID = 0;
        try {
            String sql = "INSERT INTO customers (first_name, last_Name, email, password) VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customerModel.getFirstName());
            ps.setString(2, customerModel.getLastName());
            ps.setString(3, customerModel.getEmail());
            ps.setString(4, customerModel.getPassword());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                customerID = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerID;
    }

    @Override
    // reads existing customer from database to be used in java
    public CustomerModel read(Integer id) {
        try {
            String sql = "SELECT * FROM customers WHERE customer_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            CustomerModel newModel = new CustomerModel();
            while (rs.next()) {
                newModel.setEmail(rs.getString("email"));
                newModel.setFirstName(rs.getString("first_name"));
                newModel.setLastName(rs.getString("last_name"));
            }
            return newModel;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    //updates existing customer in database
    public CustomerModel update(CustomerModel customerModel) {
        try {
            String sql = "UPDATE customers SET first_name = ?, last_name = ?, email = ?, password = ? WHERE customer_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,customerModel.getFirstName());
            ps.setString(2, customerModel.getLastName());
            ps.setString(3, customerModel.getEmail());
            ps.setString(4, customerModel.getPassword());
            ps.setInt(5, customerModel.getCustomerID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerModel;
    }

    @Override
    //deletes customer from database
    public void delete(Integer id) {
        try {
            String sql = "DELETE FROM customers WHERE customer_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // authenticates password to email match in the database
    public CustomerModel authenticate(String email, String password) throws SQLException, IOException {
        String sql = "SELECT * FROM customers WHERE email = ?";
        PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        if (rs.next() && rs.getString("password").equals(password)) {
            return new CustomerModel(rs.getInt("customer_id"), rs.getString("email"), rs.getString("password"));
        }
        return null;
    }

}
