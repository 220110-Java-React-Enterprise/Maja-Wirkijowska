package Views;

import Persistence.AccountModel;
import Persistence.AccountRepo;
import Persistence.CustomerRepo;
import Persistence.CustomerModel;
import Util.ContextStore;
import Util.MyLinkedList;
import Util.ViewManager;
import sun.rmi.runtime.NewThreadAction;

import java.io.IOException;
import java.sql.SQLException;

public class NewAccountView extends View {

    /*
    creates a new account for current user
    adds new account to database
    prints new account number back to the customer
    returns to user view
     */

    public NewAccountView() {
        viewName = "newAccount";
        viewManager = ViewManager.getViewManager();
    }

    @Override
    public void renderView() throws SQLException, IOException {

        System.out.println("Opening new account...");
        int accountID;
        AccountModel newAccount = new AccountModel();
        AccountRepo repo = new AccountRepo();
        newAccount.setAccountID(repo.create(newAccount));
        ContextStore.setCurrentAccount(newAccount);

        System.out.println("Your new account number is: " + ContextStore.getCurrentAccount().getAccountID());

        viewManager.navigate("userView");
    }
}
