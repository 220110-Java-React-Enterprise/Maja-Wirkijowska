import Persistence.CustomerModel;
import Persistence.CustomerRepo;
import Persistence.AccountModel;
import Persistence.AccountRepo;
import Util.ConnectionManager;
import Util.MyLinkedList;
import Util.MyList;
import Util.ViewManager;
import Views.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ViewManager.getViewManager().registerView(new WelcomeView());
        ViewManager.getViewManager().registerView(new RegisterView());
        ViewManager.getViewManager().registerView(new LogInView());
        ViewManager.getViewManager().registerView(new NewAccountView());
        ViewManager.getViewManager().registerView(new TransferView());
        ViewManager.getViewManager().registerView(new UserView());
        ViewManager.getViewManager().registerView(new WithdrawView());
        ViewManager.getViewManager().registerView(new DepositView());
        ViewManager.getViewManager().registerView(new AllAccounts());



        try {
            Connection connection = ConnectionManager.getConnection();
            ViewManager.getViewManager().navigate("welcome");
            while (ViewManager.getViewManager().isRunning()) {
                ViewManager.getViewManager().render();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner kb = new Scanner(System.in);
        int input = kb.nextInt();
        kb.nextLine();
    }
}
