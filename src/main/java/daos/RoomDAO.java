package daos;

import entities.Room;
import entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Optional;

public class RoomDAO extends GenericDAO {
    private static final Logger log = LogManager.getLogger(RoomDAO.class);
    final String TABLE_NAME= "rooms";

    public Optional<Room> getRoomBy(Object object, String fieldName, boolean isLikeSearch) throws SQLException, ClassNotFoundException {
        if (object == null) {
            return Optional.empty();
        }
        final String sqlSelectUser = "SELECT * FROM rooms WHERE "+ fieldName + "=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectRoom)) {

            if(object instanceof Integer){
                ps.setInt(1, (Integer) object);
            }else if(object instanceof String){
                ps.setString(1, (String) object);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(resultSetToRoomObject(rs));
                } else {
                    return Optional.empty();
                }
            }
        }

        public Optional<Room> getRoomByName(String name) throws SQLException, ClassNotFoundException {
            return getRoomByName(name, "room_name", false);
        }

        public Optional<Room> getRoomById(Long id) throws SQLException, ClassNotFoundException {
            return getRoomById(id, "room_id", false);
        }

        public boolean saveOrUpdateRoom(Room room) {
            if (room == null) return false;
            String resultMsg = "Room updated";
            String sqlStr = "UPDATE rooms SET room_name = ?, room_theme = ?, room_level = ?, room_status = ?, room_max_players = ?, room_date = ?";
            if (room.getId() == null) {
                sqlStr = "INSERT INTO rooms (room_name, room_theme, room_level, room_status, room_max_players, room_date" + "VALUES (?, ?, ?, ?, ?, ?)";
                resultMsg = "\nRoom inserted";
            }

            try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sqlStr)) {
                ps.setLong(1, room.getId());
                ps.setString(2, room.getName());
                // ps.setString(3, room.getTheme());    //ES UN ENUM
                // ps.setString(4, room.getLevel());    //ES UN ENUM
                // ps.setString(5, room.getStatus());   //ES UN ENUM
                ps.setInt(6, room.getMaxPlayers());
                // ps.setDate(5, room.getDate());       //ES UNA FECHA

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println(resultMsg);
                }
                return rowsAffected > 0;

            } catch (SQLException | ClassNotFoundException e) {
                log.info("Can't save the information\n");
                log.error("Can't save the information\n", e);
            }
            return false;
        }

        public List<Room> getAllRooms() throws ClassNotFoundException, SQLException {
            String sqlSelectAllRooms = "SELECT * FROM " + TABLE_NAME;
            List<Room> roomsList = new ArrayList<>();
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sqlSelectAllRooms);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    roomsList.add(resultSetToRoomObject(rs));
                }
            } catch (SQLException e) {
                log.error(e);
            }
            return roomsList;
        }

        public void printAllRooms() throws ClassNotFoundException, SQLException {
            System.out.println("Rooms list ____________________");
            getAllRooms().forEach(Room::printBasicInfoValues);
        }

        public Room resultSetToRoomObject (ResultSet rs) throws SQLException {
            Room room = new Room();
            room.setId(rs.getLong("room_id"));
            room.setName(rs.getString("room_name"));
            // room.setTheme(rs.get("room_theme"));              //ES UN ENUM
            // room.setLevel(rs.getInt("room_level"));           //ES UN ENUM
            // room.setStatus(rs.getInt("room_status"));         //ES UN ENUM
            room.setMaxPlayers(rs.getInt("room_max_players"));
            // room.setDate(rs.getDate("room_date"));            //ES UNA FECHA
            return room;
        }

        public boolean deleteRoomById(Long id) throws SQLException, ClassNotFoundException {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("There is no room with this id");
            }
            final String sqlDelete = "DELETE FROM " + TABLE_NAME + " WHERE room_id=?";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sqlDelete)) {
                ps.setLong(1, id);
                ps.execute();
                return true;
            }
        }
    }
}
