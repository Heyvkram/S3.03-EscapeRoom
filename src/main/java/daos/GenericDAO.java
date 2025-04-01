package daos;

import entities.Notification;
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

    abstract String getTableName();
    abstract String getIdFieldName();

    final Connection getConnection() throws SQLException, ClassNotFoundException {
        DdBbConnection ddBbConnection = DdBbConnection.getInstance();
        return ddBbConnection.getConnection();
    }

    public boolean deleteById(Long id) throws SQLException, ClassNotFoundException {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("No user");
        }
        final String sqlDelete = "DELETE FROM "+getTableName()+" WHERE "+getIdFieldName()+"=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlDelete)) {
            ps.setLong(1, id);
            ps.execute();
            return true;
        }
    }
}
