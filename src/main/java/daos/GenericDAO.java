package daos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public abstract class GenericDAO {

    String TABLE_NAME = "";

    final Connection getConnection() throws SQLException, ClassNotFoundException {

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

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return DriverManager.getConnection(connectionUrl,user, pssw);
    }

    public boolean deleteById(Integer id) throws SQLException, ClassNotFoundException {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("No user");
        }
        final String sqlDelete = "DELETE FROM "+TABLE_NAME+" WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlDelete)) {
            ps.setInt(1, id);
            ps.execute();
            return true;
        }
    }

}
