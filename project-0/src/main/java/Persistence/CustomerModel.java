package Persistence;

import Util.ContextStore;
import Util.MyLinkedList;
import java.util.Objects;

public class CustomerModel {
    private Integer customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private MyLinkedList<AccountModel> accountList;

    //constructors
    public CustomerModel() {}
    public CustomerModel(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        accountList = new MyLinkedList<>();
    }
    public CustomerModel(int customerID, String email, String password) {
        this.customerID = customerID;
        this.email = email;
        this.password = password;
    }
    public String toStringLess() {
        return "Customer: " + firstName + " " + lastName +
                "\n\tEmail: " + email + "\n";
    }
    @Override
    public String toString() {
        return "Customer: " + ContextStore.getCurrentCustomer().getFirstName() + " " +
                ContextStore.getCurrentCustomer().getLastName() +
                "\n\tEmail: " + email +
                "\n\tAccounts: " + accountList + "\n";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerModel customerModel = (CustomerModel) o;
        return Objects.equals(firstName, customerModel.firstName) && Objects.equals(lastName, customerModel.lastName) && Objects.equals(email, customerModel.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MyLinkedList<AccountModel> getAccountList() {
        return accountList;
    }

    public void setAccountList(MyLinkedList<AccountModel> accountList) {
        this.accountList = accountList;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }
}
