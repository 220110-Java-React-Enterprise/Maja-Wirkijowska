package Views;

import Persistence.AccountModel;
import Persistence.AccountRepo;
import Util.ContextStore;
import Util.MyLinkedList;
import Util.ViewManager;
import java.io.IOException;
import java.sql.SQLException;

public class DepositView extends View {

    public DepositView() {
        viewName = "deposit";
        viewManager = ViewManager.getViewManager();
    }

    @Override
    public void renderView() throws SQLException, IOException {
        /*
        prints all accounts current customer has
        asks for account id that gets deposit
        asks for amount to deposit
        calculates new balance, updates balance in database
        prints new balance
         */
        AccountRepo repo = new AccountRepo();
        MyLinkedList<AccountModel> accountList = repo.getAllAccountsByCustomerID(ContextStore.getCurrentCustomer().getCustomerID());
        for (int i = 0; i < accountList.getSize(); i++){
            System.out.println(accountList.get(i));
        }
        System.out.println("\nEnter the account ID you want to deposit to: ");
        int accountID = viewManager.getScanner().nextInt();
        viewManager.getScanner().nextLine();
        AccountModel account = repo.read(accountID);
        System.out.println("Enter the amount you want to deposit: ");
        double deposit = viewManager.getScanner().nextDouble();
        viewManager.getScanner().nextLine();
        if (deposit < 0) {
            System.out.println("Make sure you are entering a positive amount\nPlease try again.\n\n\n\n");
            viewManager.navigate("userView");
        }
        double balance = account.getBalance() + deposit;
        account.setBalance(balance);
        repo.update(account);
        System.out.printf("Deposit Successful!\n" +
                        "New balance for account number %d: $%,.2f\n", accountID, balance);
        viewManager.navigate("userView");

    }
}
