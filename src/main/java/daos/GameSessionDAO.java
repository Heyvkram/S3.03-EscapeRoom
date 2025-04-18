package daos;

import entities.*;
import escapeRoomExceptions.PrintableException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.UtilsEscape;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            log.info("!!!! Can't save the information\n");
            log.error("!!!! Can't save the information\n", e);
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
                sessionsList.add(resultSetToGameSessionObject(rs));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return sessionsList;
    }

    public List<GameSession> getRoomAllSessions(long roomId) throws ClassNotFoundException, SQLException {
        String sqlSelectAllUsers = "SELECT * FROM " + TABLE_NAME + " WHERE room_id ="  + roomId;
        List<GameSession> sessionsList = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllUsers);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                sessionsList.add(resultSetToGameSessionObject(rs));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return sessionsList;
    }

    public boolean updateGameSessionStatus(long gsId, String status) {
        String sqlUpdate = "UPDATE game_sessions SET status = ?  WHERE game_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlUpdate);
        ) {
            ps.setString(1, status);
            ps.setLong(2, gsId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(">>> Game session updated\n");
            }
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("!!!! Can't save the information\n");
            log.error("!!!! Can't save the information\n", e);
        }
        return false;
    }

    public Optional<GameSession> getGameSessionBy(Object object, String fieldName) throws SQLException, ClassNotFoundException {
        if (object == null) {
            return Optional.empty();
        }
        final String sqlSelectUser = "SELECT * FROM game_sessions WHERE " + fieldName + "=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectUser)) {

            if (object instanceof Integer) {
                ps.setInt(1, (Integer) object);
            } else if (object instanceof Long) {
                ps.setLong(1, (Long) object);
            } else if (object instanceof String) {
                ps.setString(1, (String) object);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(resultSetToGameSessionObject(rs));
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    public Optional<GameSession> getGameSessionById(Long id){
        Optional<GameSession> gameSession = Optional.empty();;
        try{
            gameSession = getGameSessionBy(id, ID_FIELD_NAME);
        }catch (SQLException | ClassNotFoundException e) {
            System.out.println("\n!!!! No gameSession with this id was found.");
        }
        return gameSession;
    }

    public GameSession resultSetToGameSessionObject(ResultSet rs) throws SQLException {
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

    public void printRoomGameSessionPrices(Room room, List<GameSession> allItems) {
        System.out.println("\nGame Session of room information: ____________________");
        System.out.println("Room id : " + room.getRoomId() + "   Name : " + room.getRoomName());
        System.out.println("            Room Cost Value : " + room.getTotalCostValue());
        if (allItems.isEmpty()) {
            System.out.println("            No Game Session items found.");
            return;
        }
        List<CalculablePriceInterface> priceInterfaceItems = new ArrayList<>(allItems);
        double totalPriceSum = UtilsEscape.sumAllPrices(priceInterfaceItems);
        System.out.println("            Game sessions sold: " + allItems.size());
        System.out.println("            Average cost of session: $" + String.format("%.2f", totalPriceSum/allItems.size()));
        System.out.println("            Game session result: $" + String.format("%.2f", totalPriceSum-room.getTotalCostValue()));
        System.out.println("\n");
    }

    public void printAllSessions() throws ClassNotFoundException, SQLException {
        System.out.println("\n    Sessions list............................");
        getAllSessions().forEach(GameSession::printBasicInfoValues);
    }

}
