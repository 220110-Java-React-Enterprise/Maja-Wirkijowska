package Views;

import Util.ViewManager;
import java.io.IOException;
import java.sql.SQLException;

public class UserView extends View {

    public UserView() {
        viewName = "userView";
        viewManager = ViewManager.getViewManager();
    }

    /*
    show existing customer possible actions
     */
    @Override
    public void renderView() throws SQLException, IOException {

        System.out.println("Please select an option: " +
                "\n\t 1. Open New Account" +
                "\n\t 2. Deposit" +
                "\n\t 3. Withdraw" +
                "\n\t 4. Transfer Between Accounts " +
                "\n\t 5. View All Accounts " +
                "\n\t 6. Log Out\n");
        String input = viewManager.getScanner().nextLine();
        switch (input) {
            case "1":
                viewManager.navigate("newAccount");
                break;
            case "2":
                viewManager.navigate("deposit");
                break;
            case "3":
                viewManager.navigate("withdraw");
                break;
            case "4":
                viewManager.navigate("transfer");
                break;
            case "5":
                viewManager.navigate("allAccounts");
                break;
            case "6":
                System.out.println("Log Out successful!\n\n\n\n");
                viewManager.navigate("welcome");
                break;
            default:
                System.out.println("Please select a valid option");
                break;
        }
    }
}
