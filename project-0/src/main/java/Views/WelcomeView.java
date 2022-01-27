package Views;

import Util.ViewManager;
import java.util.Scanner;

public class WelcomeView  extends View {

    public WelcomeView() {
        viewName = "welcome";
        viewManager = ViewManager.getViewManager();
    }

    /*
    welcome screen
    options to register, log in, and quit
     */
    @Override
    public void renderView() {
        System.out.println("Welcome to Yet-Another-Bank Bank!\n" +
                "Please select an option: " +
                "\n\t 1. Register" +
                "\n\t 2. Log In" +
                "\n\t Q. Quit");
        String input = viewManager.getScanner().nextLine();
        switch (input) {
            case "1":
                viewManager.navigate("register");
                break;
            case "2":
                viewManager.navigate("login");
                break;
            case "Q":
                System.out.println("Thank you for using Yet-Another-Bank Bank!");
                System.exit(0);
            default:
                System.out.println("Please select a valid option\n\n\n\n");
                break;
        }
    }
}
