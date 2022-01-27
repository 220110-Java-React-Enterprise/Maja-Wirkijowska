package Util;

import Persistence.AccountModel;
import Persistence.CustomerModel;

public class ContextStore {
    private static CustomerModel currentCustomer;
    private static AccountModel currentAccount;

    public static void setCurrentCustomer(CustomerModel user) {
        currentCustomer = user;
    }

    public static CustomerModel getCurrentCustomer() {
        return currentCustomer;
    }

    public static void setCurrentAccount(AccountModel account){
        currentAccount = account;
    }

    public static AccountModel getCurrentAccount() {
        return currentAccount;
    }
}
