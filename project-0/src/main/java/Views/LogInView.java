package Views;

import Persistence.CustomerModel;
import Persistence.CustomerRepo;
import Util.ContextStore;
import Util.ViewManager;
import java.io.IOException;
import java.sql.SQLException;

public class LogInView extends View {

    public LogInView() {
        viewName = "login";
        viewManager = ViewManager.getViewManager();
    }

    /*
    logs in existing user by authenticating email and password in database
    returns user to welcome view if credentials are incorrect
    shows user view if credentials are correct
     */
    @Override
    public void renderView() throws SQLException, IOException {
        System.out.println("User Log In");
        System.out.println("Enter your email: ");
        String email = viewManager.getScanner().nextLine();
        System.out.println("Enter your password: ");
        String password = viewManager.getScanner().nextLine();

        CustomerRepo repo = new CustomerRepo();
        CustomerModel user = repo.authenticate(email, password);
        if (user == null) {
            System.out.println("Incorrect credentials... \n\n\n\n");
            viewManager.navigate("welcome");
            return;
        }

        ContextStore.setCurrentCustomer(user);
        viewManager.navigate("userView");
    }
}
