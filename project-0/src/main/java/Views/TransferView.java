package Views;
import Persistence.AccountModel;
import Persistence.AccountRepo;
import Persistence.CustomerRepo;
import Persistence.CustomerModel;
import Util.ContextStore;
import Util.MyLinkedList;
import Util.ViewManager;
import java.io.IOException;
import java.sql.SQLException;

public class TransferView extends  View {

    public TransferView() {
        viewName = "transfer";
        viewManager = ViewManager.getViewManager();
    }
    /*
    checks that current user has at least 2 accounts
    transfers money from one account to another
    updates changes to balance in the database
     */

    @Override
    public void renderView() throws SQLException, IOException {
        AccountRepo repo = new AccountRepo();
        MyLinkedList<AccountModel> accountList = repo.getAllAccountsByCustomerID(ContextStore.getCurrentCustomer().getCustomerID());
        if (accountList.getSize() < 2) {
            System.out.println("You must have at least 2 accounts to be able to transfer funds");
            viewManager.navigate("userView");
            return;
        }
        for (int i = 0; i < accountList.getSize(); i++){
            System.out.println(accountList.get(i));
        }
        int sourceId = 0, destinationID = 0;
        double transferAmount = 0.00;
        System.out.println("Enter the account ID of the account you want transfer from:");
        sourceId = viewManager.getScanner().nextInt();
        viewManager.getScanner().nextLine();
        if (repo.read(sourceId).getBalance() < 0){
            System.out.println("Current balance is $0.00. Transfer not possible.\nPlease try again.\n\n\n\n");
            viewManager.navigate("userView");
            return;
        }
        System.out.println("Enter the account ID of the account you want to transfer to: ");
        destinationID = viewManager.getScanner().nextInt();
        viewManager.getScanner().nextLine();
        System.out.println("Enter the amount you want to transfer: ");
        transferAmount = viewManager.getScanner().nextDouble();
        viewManager.getScanner().nextLine();
        if (repo.read(sourceId).getBalance() < transferAmount) {
            System.out.println("The transfer amount cannot be greater than the balance of the source account\nPlease try again.\n\n\n\n");
            viewManager.navigate("userView");
            return;
        } else if (transferAmount < 0) {
            System.out.println("Make sure you are entering a positive amount\nPlease try again.\n\n\n\n");
            viewManager.navigate("userView");
            return;
        }
        AccountModel sourceAccount = repo.read(sourceId);
        AccountModel destinationAccount = repo.read(destinationID);
        double sourceBalance = sourceAccount.getBalance() - transferAmount;
        sourceAccount.setBalance(sourceBalance);
        double destinationBalance = destinationAccount.getBalance() + transferAmount;
        destinationAccount.setBalance(destinationBalance);
        repo.update(sourceAccount);
        repo.update(destinationAccount);
        System.out.printf("Transfer successful!" +
                            "\n\tAccount number %d Balance: $%,.2f" +
                            "\n\tAccount number %d Balance: $%,.2f\n", sourceId, sourceBalance,destinationID,destinationBalance);
        viewManager.navigate("userView");
    }
}
