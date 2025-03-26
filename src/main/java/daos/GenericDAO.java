package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class GenericDAO {

    final String TABLE_NAME = "";
    final String connectionUrl = "jdbc:mysql://localhost:3306/escaperoom?serverTimezone=UTC";

    final Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(connectionUrl,"root", "");
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
