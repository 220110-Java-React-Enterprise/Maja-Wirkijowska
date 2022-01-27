package Persistence;

import java.util.Objects;

public class AccountModel {
    private Integer accountID;
    private double balance;
    private int customerID; //FK


    public AccountModel() {}
    public AccountModel(int accountID, double balance, int customerID) {
        this.balance = 0;
        this.customerID = customerID;
    }
    public AccountModel(int accountID, int customerID) {
        this.balance = 0.00;
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return String.format("Account Number: %d" +
                "\n\tBalance: $%,.2f\n", accountID, balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountModel that = (AccountModel) o;
        return accountID.equals(that.accountID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountID);
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
