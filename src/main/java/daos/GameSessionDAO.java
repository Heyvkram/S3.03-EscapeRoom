package daos;

import entities.GameSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameSessionDAO extends GenericDAO {

    private static final Logger log = LogManager.getLogger(UserDAO.class);
    final String TABLE_NAME = "game_sessions";
    final String ID_FIELD_NAME = "game_id";

    @Override
    String getTableName() {
        return TABLE_NAME;
    }

    @Override
    String getIdFieldName() {
        return ID_FIELD_NAME;
    }

    public boolean insertNewUserSession(GameSession gameSession) {
        if (gameSession == null) return false;
        String sqlStr = "INSERT INTO game_sessions (room_id, user_id, room_price, status, " +
                " room_name, user_nick_name, payment_type) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlStr)) {
            ps.setLong(1, gameSession.getRoomId());
            ps.setLong(2, gameSession.getUserId());
            ps.setDouble(3, gameSession.getPrice());
            ps.setString(4, gameSession.getStatus());
            ps.setString(5, gameSession.getRoomName());
            ps.setString(6, gameSession.getUserNickname());
            ps.setString(7, gameSession.getPaymentType());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(">>>New user session inserted\n");
            }
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            log.info("Can't save the information\n");
            log.error("Can't save the information\n", e);
            return false;
        }
    }


    public List<GameSession> getAllSessions() throws ClassNotFoundException, SQLException {
        String sqlSelectAllUsers = "SELECT * FROM " + TABLE_NAME;
        List<GameSession> sessionsList = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllUsers);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                sessionsList.add(resultSetToUserObject(rs));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return sessionsList;
    }

    public GameSession resultSetToUserObject(ResultSet rs) throws SQLException {
        GameSession gameSession = new GameSession();
        gameSession.setId(rs.getLong("game_id"));
        gameSession.setRoomId(rs.getLong("room_id"));
        gameSession.setRoomName(rs.getString("room_name"));
        gameSession.setUserId(rs.getLong("user_id"));
        gameSession.setUserNickname(rs.getString("user_nick_name"));
        gameSession.setPrice(rs.getDouble("room_price"));
        gameSession.setPaymentType(rs.getString("payment_type"));
        gameSession.setStatus(rs.getString("status"));
        return gameSession;
    }

    public void printAllSessions() throws ClassNotFoundException, SQLException {
        System.out.println("\n    Session list............................");
        getAllSessions().forEach(GameSession::printBasicInfoValues);
    }

}
