package Views;

import Persistence.AccountModel;
import Persistence.AccountRepo;
import Util.ContextStore;
import Util.MyLinkedList;
import Util.ViewManager;
import java.io.IOException;
import java.sql.SQLException;

public class WithdrawView extends View {

    public WithdrawView() {
        viewName = "withdraw";
        viewManager = ViewManager.getViewManager();
    }

    /*
    similar to deposit view
    removes money from selected account
    updates changes in the database
     */
    @Override
    public void renderView() throws SQLException, IOException {
        AccountRepo repo = new AccountRepo();
        MyLinkedList<AccountModel> accountList = repo.getAllAccountsByCustomerID(ContextStore.getCurrentCustomer().getCustomerID());
        for (int i = 0; i < accountList.getSize(); i++){
            System.out.println(accountList.get(i));
        }
        System.out.println("\nEnter the account ID you want to withdraw from: ");
        int accountID = viewManager.getScanner().nextInt();
        viewManager.getScanner().nextLine();
        AccountModel account = repo.read(accountID);
        System.out.println("Enter the amount you want to withdraw: ");
        double withdraw = viewManager.getScanner().nextDouble();
        viewManager.getScanner().nextLine();
        if (repo.read(accountID).getBalance() < 0){
            System.out.println("Current balance is $0.00. Withdraw not possible.\nPlease try again.\n\n\n\n");
            viewManager.navigate("userView");
            return;
        } else if (withdraw < 0) {
            System.out.println("Make sure you are entering a positive amount\nPlease try again.\n\n\n\n");
            viewManager.navigate("userView");
            return;
        } else if (repo.read(accountID).getBalance() < withdraw) {
            System.out.println("Withdraw amount cannot be greater than account balance.\nPlease try again.\n\n\n\n");
            viewManager.navigate("userView");
            return;
        }
        double balance = account.getBalance() - withdraw;
        account.setBalance(balance);
        repo.update(account);
        System.out.printf("Withdraw successful!" +
                            "\nNew balance for account number %d: $%,.2f\n", accountID, balance);
        viewManager.navigate("userView");
    }
}
