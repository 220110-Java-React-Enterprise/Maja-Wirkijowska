package Views;

import Persistence.CustomerModel;
import Persistence.CustomerRepo;
import Util.ContextStore;
import Util.ViewManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class RegisterView extends View {
    /*
    asks new customer for first and last name, email, and password
    checks for a valid email using regex
    creates new customer and adds customer to database
    logs new customer in into user view
     */
    public RegisterView() {
        viewName = "register";
        viewManager = ViewManager.getViewManager();
    }
    @Override
    public void renderView() throws SQLException, IOException {
        System.out.println("Register a New Customer");
        System.out.println("Enter your first name: ");
        String firstName = viewManager.getScanner().nextLine();
        System.out.println("Enter your last name: ");
        String lastName = viewManager.getScanner().nextLine();
        System.out.println("Enter your email: ");
        String email = viewManager.getScanner().nextLine();

        if (!isValidEmail(email)) {
            System.out.println("Please make sure the email you enter is valid." +
                    "\nPlease try again");
            return;
        }
        System.out.println("Enter your password: ");
        String password = viewManager.getScanner().nextLine();

        CustomerModel newCustomer = new CustomerModel(firstName, lastName, email, password);
        CustomerRepo repo = new CustomerRepo();
        newCustomer.setCustomerID(repo.create(newCustomer));

        ContextStore.setCurrentCustomer(newCustomer);

        viewManager.navigate("userView");
    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        // does not allow (|) or (')
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
