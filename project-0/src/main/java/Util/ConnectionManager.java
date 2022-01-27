package Util;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionManager {

    private static Connection connection;

    private ConnectionManager() {}

    public static Connection getConnection() {
        if(connection == null) {
            try {
                Properties props = new Properties();
                FileReader connectionProperties = new FileReader("src/main/resources/jdbc.properties");
                props.load(connectionProperties);

                String connectionString = "jdbc:mariadb://" +
                        props.getProperty("hostname") + ":" +
                        props.getProperty("port") + "/" +
                        props.getProperty("dbname") + "?user=" +
                        props.getProperty("username") + "&password=" +
                        props.getProperty("password");
                //Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(connectionString);
                //System.out.println(connectionString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

}