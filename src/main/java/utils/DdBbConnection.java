package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DdBbConnection {

    private static final Logger log = LogManager.getLogger(DdBbConnection.class);

    public final Connection getConnection() throws SQLException, ClassNotFoundException {

        String connectionUrl = null;
        String user = null;
        String pssw = null;

        Properties properties= new Properties();
        try {

            properties.load(new FileInputStream(new File("configuration.properties")));

            Class.forName(properties.getProperty("MYSQL_DRIVER"));
            connectionUrl = properties.getProperty("CONNECTION_URL");
            user = properties.getProperty("USER");
            pssw = properties.getProperty("PASSWORD");

        } catch (IOException e) {
            log.error(e);
        }

        return DriverManager.getConnection(connectionUrl,user, pssw);
    }

}
