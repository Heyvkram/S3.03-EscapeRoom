package daos;

import entities.Room;
import forms.RoomForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.EnumConstants;
import utils.EnumConstants.ROOM_THEME;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RoomDAO extends GenericDAO {
    private static final Logger log = LogManager.getLogger(RoomDAO.class);
    final String TABLE_NAME = "rooms";
    final String ID_FIELD_NAME = "room_id";

    @Override
    String getTableName() {
        return TABLE_NAME;
    }

    @Override
    String getIdFieldName() {
        return ID_FIELD_NAME;
    }

    public Optional<Room> getRoomBy(Object object, String fieldName, boolean isLikeSearch) throws SQLException, ClassNotFoundException {
        if (object == null) {
            return Optional.empty();
        }
        final String sqlSelectRoom = "SELECT * FROM rooms WHERE "+ fieldName + "=?";
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
    }
    public Optional<Room> getRoomById(Long roomId) throws SQLException, ClassNotFoundException {
        return getRoomBy(roomId, "room_id", false);
        }

    public Optional<Room> getRoomByName(String roomName) throws SQLException, ClassNotFoundException {
            return getRoomBy(roomName, "room_name", false);
        }

    public List<Room> getRoomByTheme(ROOM_THEME theme) throws ClassNotFoundException, SQLException {
        String sqlSelectRoomsByTheme = "SELECT * FROM " + TABLE_NAME + " WHERE room_theme = ?";
        List<Room> roomsListByTheme = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectRoomsByTheme)) {
            ps.setString(1, theme.toString());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                roomsListByTheme.add(resultSetToRoomObject(rs));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return roomsListByTheme;
    }

    public List<Room> getRoomByThemeTerror(ROOM_THEME TERROR) throws ClassNotFoundException, SQLException {
        String sqlSelectRoomsByThemeTerror = "SELECT * FROM " + TABLE_NAME + " WHERE room_theme = Terror";
        List<Room> roomsListByThemeTerror = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectRoomsByThemeTerror);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                roomsListByThemeTerror.add(resultSetToRoomObject(rs));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return roomsListByThemeTerror;
    }

    public List<Room> getRoomByThemeFiction (ROOM_THEME FICTION) throws ClassNotFoundException, SQLException {
        String sqlSelectRoomsByThemeFiction = "SELECT * FROM " + TABLE_NAME + " WHERE room_theme = Fiction";
        List<Room> roomsListByThemeFiction = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectRoomsByThemeFiction);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                roomsListByThemeFiction.add(resultSetToRoomObject(rs));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return roomsListByThemeFiction;
    }

    public List<Room> getRoomByThemeFantasy(ROOM_THEME FANTASY) throws ClassNotFoundException, SQLException {
        String sqlSelectRoomsByThemeFantasy = "SELECT * FROM " + TABLE_NAME + " WHERE room_theme = Fantasy";
        List<Room> roomsListByThemeFantasy = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectRoomsByThemeFantasy);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                roomsListByThemeFantasy.add(resultSetToRoomObject(rs));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return roomsListByThemeFantasy;
    }

    public List<Room> getRoomByLevel() throws ClassNotFoundException, SQLException {
        String sqlSelectAllRoomsByLevel = "SELECT * FROM " + TABLE_NAME + " WHERE room_level = ?";
        List<Room> roomsListByLevel = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllRoomsByLevel);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                roomsListByLevel.add(resultSetToRoomObject(rs));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return roomsListByLevel;
    }


        public boolean saveOrUpdateRoom(Room room) {
            if (room == null) return false;

            String sqlStr = "UPDATE rooms SET room_name = ?, room_theme = ?, room_level = ?, room_status = ?, room_max_players = ?, room_date = ?";
            String resultMsg = "Room updated";
            if (room.getRoomId() == null) {
                sqlStr = "INSERT INTO rooms (room_name, room_theme, room_level, room_status, room_max_players, room_date" + "VALUES (?, ?, ?, ?, ?, ?)";
                resultMsg = "Room inserted";
            }

            try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sqlStr)) {
                ps.setLong(1, room.getRoomId());
                ps.setString(2, room.getRoomName());
                ps.setString(3, String.valueOf(room.getRoomTheme()));
                ps.setString(4, String.valueOf(room.getRoomLevel()));
                ps.setString(5, String.valueOf(room.getRoomStatus()));
                ps.setInt(6, room.getRoomMaxPlayers());
                //ps.setDate(7, room.getRoomDate());       //ES UNA FECHA

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
            System.out.println("Room list ____________________");
            getAllRooms().forEach(Room::printBasicInfoValues);
        }

        public void printRoomsByThemeTerror() throws ClassNotFoundException, SQLException {
        System.out.println("Terror Room list ___________ ");
        getRoomByTheme(ROOM_THEME.TERROR).forEach(Room::printBasicInfoValues);
        }

        public void printRoomsByThemeFiction() throws ClassNotFoundException, SQLException {
        System.out.println("Fiction Room list ___________ ");
        getRoomByTheme(ROOM_THEME.FICTION).forEach(Room::printBasicInfoValues);
        }

        public void printRoomsByThemeFantasy() throws ClassNotFoundException, SQLException {
        System.out.println("Fantasy Room list ___________ ");
        getRoomByTheme(ROOM_THEME.FANTASY).forEach(Room::printBasicInfoValues);
        }

        public void printRoomsByLevel() throws ClassNotFoundException, SQLException {
            System.out.println("Room list by difficulty ________");
            getRoomByLevel().forEach(Room::printBasicInfoValues);
        }

        public Room resultSetToRoomObject (ResultSet rs) throws SQLException {
            Room room = new Room();
            room.setRoomId(rs.getLong("room_id"));
            room.setRoomName(rs.getString("room_name"));
            String themeValue = rs.getString("room_theme").toUpperCase();
            room.setRoomTheme(ROOM_THEME.valueOf(themeValue));
            String roomLevel = rs.getString("room_level").toUpperCase();
            room.setRoomLevel(EnumConstants.GAME_LEVEL.valueOf(roomLevel));
            String statusValue = rs.getString("room_status").replace(" ", "_").toUpperCase();
            room.setRoomStatus(EnumConstants.ROOM_STATUS.valueOf(statusValue));
            room.setRoomMaxPlayers(rs.getInt("room_max_players"));
            room.setRoomDate(rs.getTimestamp("room_date").toLocalDateTime());
            return room;
        }

    public boolean createRoom(Room room) {
        if (room == null) return false;

        String sqlStr = "INSERT INTO rooms (room_name, room_theme, room_level, room_status, room_max_players, room_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sqlStr)) {
            ps.setString(1, room.getRoomName());
            ps.setString(2, String.valueOf(room.getRoomTheme()));
            ps.setString(3, String.valueOf(room.getRoomLevel()));
            ps.setString(4, String.valueOf(room.getRoomStatus()));
            ps.setInt(5, room.getRoomMaxPlayers());
            ps.setTimestamp(6, Timestamp.valueOf(room.getRoomDate()));

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Room created");
            }
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            log.info("Can't create the room\n");
            log.error("Can't create the room\n", e);
        }
        return false;
    }

        public boolean deleteRoomById (Long roomId) throws SQLException, ClassNotFoundException {
            if (roomId == null || roomId <= 0) {
                throw new IllegalArgumentException("There is no room with this id");
            }
            final String sqlDelete = "DELETE FROM " + TABLE_NAME + " WHERE room_id=?";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sqlDelete)) {
                ps.setLong(1, roomId);
                ps.execute();
                return true;
            }
        }

}