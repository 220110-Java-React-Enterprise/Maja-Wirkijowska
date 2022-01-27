package Persistence;

import Util.ConnectionManager;
import Util.ContextStore;
import Util.MyLinkedList;
import java.io.IOException;
import java.sql.*;

public class AccountRepo implements DataSourceCRUD<AccountModel>{
    private final Connection connection;

    public AccountRepo() {
        connection = ConnectionManager.getConnection();
    }

    @Override
    //creates new account
    public Integer create(AccountModel accountModel) {
        int accountID = 0;
        try {
            String sql = "INSERT INTO accounts (balance, customer_id) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, accountModel.getBalance());
            ps.setInt(2, ContextStore.getCurrentCustomer().getCustomerID());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                accountID = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountID;
    }

    @Override
    //reads account into IDE
    public AccountModel read(Integer id) {
        try {
            String sql = "SELECT * FROM accounts WHERE account_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            AccountModel model = new AccountModel();
            while (rs.next()) {
                model.setAccountID(rs.getInt("account_id"));
                model.setBalance(rs.getDouble("balance"));
                model.setCustomerID(rs.getInt("customer_id"));
            }
            return model;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    //updates account
    public AccountModel update(AccountModel accountModel) {
        try {
            String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, accountModel.getBalance());
            ps.setInt(2, accountModel.getAccountID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountModel;
    }

    @Override
    //deletes account
    public void delete(Integer id) {
        try {
            String sql = "DELETE FROM accounts WHERE account_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    //returns a linked list of accounts from database based on customer id
    public MyLinkedList<AccountModel> getAllAccountsByCustomerID(Integer id) throws SQLException, IOException {
        String sql = "SELECT * FROM accounts WHERE customer_id = ?";
        PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        MyLinkedList<AccountModel> accountList = new MyLinkedList<>();
        while (rs.next()) {
            AccountModel account = new AccountModel();
            account.setAccountID(rs.getInt("account_id"));
            account.setBalance(rs.getDouble("balance"));
            account.setCustomerID(rs.getInt("customer_id"));
            accountList.add(account);
        }
        return accountList;
    }
}
