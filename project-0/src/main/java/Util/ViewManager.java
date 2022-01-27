package Util;

import Views.View;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class ViewManager {
    private static ViewManager viewManager;
    private boolean running;
    private final Scanner kb;
    MyLinkedList<View> viewList;
    View nextView;

    private ViewManager() {
        //starting values
        running = true;
        kb = new Scanner(System.in);
        viewList = new MyLinkedList<>();
    }

    public static ViewManager getViewManager() {
        if (viewManager == null) {
            viewManager = new ViewManager();
        }
        return viewManager;
    }

    public void navigate(String destination) {
        for (View view : viewList) {
            if (view.getViewName().equals(destination)) {
                nextView = view;
            }
        }
    }

    public void registerView(View view) {
        viewList.add(view);
    }

    public void render() throws SQLException, IOException {
        nextView.renderView();
    }

    public Scanner getScanner() {
        return kb;
    }

    public void quit() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

}
