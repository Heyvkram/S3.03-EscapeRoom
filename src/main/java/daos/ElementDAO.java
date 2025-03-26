package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ElementDAO {

    public boolean deleteUserById(Integer id) throws SQLException, ClassNotFoundException {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("No user");
        }
        final String sqlDeleteUser = "DELETE FROM users WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlDeleteUser)) {
            ps.setInt(1, id);
            ps.execute();
            return true;
        }
    }

}
