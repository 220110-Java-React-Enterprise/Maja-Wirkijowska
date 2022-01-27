package Views;

import Persistence.AccountModel;
import Persistence.AccountRepo;
import Util.ContextStore;
import Util.MyLinkedList;
import Util.ViewManager;

import java.io.IOException;
import java.sql.SQLException;

public class AllAccounts extends View {
    public AllAccounts() {
        viewName = "allAccounts";
        viewManager = ViewManager.getViewManager();
    }

    /*
     view that prints all existing accounts a customer has based on customer id
     returns to user view
     */
    @Override
    public void renderView() throws SQLException, IOException {
        AccountRepo repo = new AccountRepo();
        MyLinkedList<AccountModel> accountList = repo.getAllAccountsByCustomerID(ContextStore.getCurrentCustomer().getCustomerID());
        if (accountList.getSize() < 1) {
            System.out.println("You do not have any accounts to display.");
            viewManager.navigate("userView");
        }
        for (int i = 0; i < accountList.getSize(); i++){
            System.out.println(accountList.get(i));
        }
        viewManager.navigate("userView");
    }
}
