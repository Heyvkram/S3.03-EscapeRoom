package daos;

import utils.DdBbConnection;

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
        DdBbConnection ddBbConnection = new DdBbConnection();
        return ddBbConnection.getConnection();
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
